package com.school.manager.service.impl;

import com.google.common.collect.Lists;
import com.school.manager.common.Constant;
import com.school.manager.dao.UserDao;
import com.school.manager.dto.req.LoginUserReq;
import com.school.manager.dto.req.UserReq;
import com.school.manager.dto.resp.UserResp;
import com.school.manager.entity.LoginUserInfo;
import com.school.manager.enums.RoleEnum;
import com.school.manager.enums.StateEnum;
import com.school.manager.enums.StatusCode;
import com.school.manager.pojo.User;
import com.school.manager.pojo.UserGradeSubject;
import com.school.manager.pojo.UserPwd;
import com.school.manager.pojo.UserRole;
import com.school.manager.service.UserGradeSubjectService;
import com.school.manager.service.UserPwdService;
import com.school.manager.service.UserRoleService;
import com.school.manager.service.UserService;
import com.school.manager.utils.BeanMapper;
import com.school.manager.utils.IdWorker;
import com.school.manager.utils.Md5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author RoninLee
 * @description 用户管理
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;
    @Autowired
    private UserPwdService userPwdService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private UserGradeSubjectService userGradeSubjectService;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private LoginServiceImpl loginServiceImpl;


    /**
     * 登录
     *
     * @param request 请求对象
     * @return 用户信息
     */
    @Override
    public UserResp login(LoginUserReq request) {
        LoginUserInfo loginUserInfo = this.info(request.getJobNumber());
        if (passwordEncoder.matches(request.getPwd(), loginUserInfo.getPassword())) {
            UserResp userResp = new UserResp();
            userResp.setUsername(loginUserInfo.getUsername());
            userResp.setJobNumber(loginUserInfo.getJobNumber());
            userResp.setToken(UUID.randomUUID().toString());
            return userResp;
        }
        return null;
    }

    /**
     * 分页查询用户列表
     *
     * @param request 请求对象
     * @return 人员列表
     */
    @Override
    public Page<User> findValidList(UserReq request) {
        // 分页查询页码
        Integer pageIndex = Optional.ofNullable(request.getPageIndex()).map(index -> {
            if (index <= 0) {
                return Constant.PAGE_INDEX;
            }
            // 框架第一页默认0开始
            return index - 1;
        }).orElse(Constant.PAGE_INDEX);
        // 分页查询每页数量
        Integer pageSize = Optional.ofNullable(request.getPageSize()).map(size -> {
            if (size < 0) {
                return Constant.PAGE_SIZE;
            }
            return size;
        }).orElse(Constant.PAGE_SIZE);
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        return userDao.findAll(new Specification<User>() {
            private static final long serialVersionUID = 8554879066378122342L;

            /**
             *
             * @param root 根对象，也就是要把条件封装到那个对象中，where 类名=user.getId()
             * @param criteriaQuery 封装的都是查询关键字，比如group by order by等
             * @param criteriaBuilder 用来封装条件对象的，如果直接返回null，表示不需要任何条件
             * @return Predicate
             */
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = Lists.newArrayList();
                if (StringUtils.isNotBlank(request.getUsername())) {
                    Predicate predicate = criteriaBuilder.like(root.get("name").as(String.class), "%" + request.getUsername() + "%");
                    list.add(predicate);
                }
                if (StringUtils.isNotBlank(request.getJobNumber())) {
                    Predicate predicate = criteriaBuilder.like(root.get("jobNumber").as(String.class), "%" + request.getJobNumber() + "%");
                    list.add(predicate);
                }
                Predicate state = criteriaBuilder.equal(root.get("state").as(Integer.class), StateEnum.valid.getCode());
                list.add(state);
                // 管理员id
                String adminId = "-1";
                Predicate admin = criteriaBuilder.notEqual(root.get("id").as(String.class), adminId);
                list.add(admin);
                Predicate[] par = new Predicate[list.size()];
                list.toArray(par);
                return criteriaBuilder.and(par);
            }
        }, pageable);
    }

    /**
     * 通过id查人员
     *
     * @param id id
     * @return 人员信息
     */
    @Override
    public UserResp findById(String id) {
        return BeanMapper.def().map(userDao.findById(id).orElseThrow(() -> new RuntimeException(StatusCode.DATA_NOT_EXIST.getDesc())), UserResp.class);
    }

    /**
     * 新增用户
     *
     * @param request 用户信息
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public String save(UserReq request) {
        // 转换对象
        User user = BeanMapper.def().map(request, User.class);
        // 雪花算法生成id
        user.setId(String.valueOf(idWorker.nextId()));
        user.setState(StateEnum.valid.getCode());
        Boolean isGroupLeader = Optional.ofNullable(request.getGroupLeaderFlag()).orElse(Boolean.FALSE);
        user.setGroupLeaderFlag(isGroupLeader);
        userDao.save(user);
        // 用户密码
        UserPwd userPwd = new UserPwd();
        userPwd.setId(user.getId());
        // 默认密码
        String defaultPwd = "Sxzx2019";
        // 采用Spring Security二次加密
        userPwd.setPassword(passwordEncoder.encode(Md5Util.md5(defaultPwd)));
        userPwdService.saveOrUpdate(userPwd);
        // 用户角色
        List<UserRole> userRoleList = Lists.newArrayList();
        UserRole userRole = new UserRole();
        userRole.setId(String.valueOf(idWorker.nextId()));
        userRole.setUserId(user.getId());
        userRole.setRoleId(RoleEnum.TEACHER.getCode());
        userRoleList.add(userRole);
        if (isGroupLeader) {
            UserRole userRoleLeader = new UserRole();
            userRoleLeader.setId(String.valueOf(idWorker.nextId()));
            userRoleLeader.setUserId(user.getId());
            userRoleLeader.setRoleId(RoleEnum.GROUP_LEADER.getCode());
            userRoleList.add(userRoleLeader);
        }
        userRoleService.batchSave(userRoleList);
        return user.getId();
    }

    /**
     * 更新用户
     *
     * @param request 用户信息
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public String update(UserReq request) {
        User userByJobNumber = userDao.findUserByJobNumber(request.getJobNumber());
        if (Objects.nonNull(userByJobNumber) && !StringUtils.equals(userByJobNumber.getId(), request.getId())) {
            throw new RuntimeException("工号已存在！");
        }
        User user = BeanMapper.def().map(request, User.class);
        user.setState(StateEnum.valid.getCode());
        userDao.save(user);
        if (StringUtils.isNotBlank(request.getPassword())) {
            UserPwd userPwd = BeanMapper.def().map(request, UserPwd.class);
            userPwd.setPassword(passwordEncoder.encode(request.getPassword()));
            userPwdService.saveOrUpdate(userPwd);
        }
        // 判断是否组长角色
        if (request.getGroupLeaderFlag()) {
            List<UserRole> userRolesByUserId = userRoleService.findUserRolesByUserId(user.getId());
            List<String> roleIds = userRolesByUserId.stream().map(UserRole::getRoleId).collect(Collectors.toList());
            if (!roleIds.contains(RoleEnum.GROUP_LEADER.getCode())) {
                UserRole userRole = new UserRole();
                userRole.setUserId(user.getId());
                userRole.setRoleId(RoleEnum.GROUP_LEADER.getCode());
                userRole.setId(String.valueOf(idWorker.nextId()));
                userRoleService.save(userRole);
            }
        }
        return request.getId();
    }

    /**
     * 删除用户
     *
     * @param id 用户id
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public void removeById(String id) {
        User user = userDao.findById(id).orElseThrow(() -> new RuntimeException(StatusCode.DATA_NOT_EXIST.getDesc()));
        userDao.deleteById(id);
        userPwdService.delete(id);
        //删除用户角色关联关系
        userRoleService.deleteUserRoleBuUserId(user.getId());
        // 删除用户和年级学科关系
        userGradeSubjectService.deleteAllByUserId(user.getId());
        // TODO: 2019/12/22 要不要删除教案相关记录
    }

    /**
     * 查询用户详情
     *
     * @param jobNumber 工号
     * @return 用户详细信息
     */
    @Override
    public LoginUserInfo info(String jobNumber) {
        // 根据工号查询用户信息
        User user = userDao.findUserByJobNumber(jobNumber);
        Optional.ofNullable(user).orElseThrow(() -> new RuntimeException(StatusCode.DATA_NOT_EXIST.getDesc()));
        LoginUserInfo loginUserInfo = new LoginUserInfo();
        BeanUtils.copyProperties(user, loginUserInfo);
        // 用户密码
        String password = userPwdService.findById(user.getId());
        loginUserInfo.setPassword(password);
        // 是否管理员
        loginUserInfo.setAdminFlag(StringUtils.equals(user.getJobNumber(), RoleEnum.ADMIN.getDesc()) ? Boolean.TRUE : Boolean.FALSE);
        List<UserGradeSubject> userGradeSubjects = userGradeSubjectService.findByUserId(user.getId());
        // 用户、年级、学科绑定关系
        loginUserInfo.setUserGradeSubjects(userGradeSubjects);
        // 年级
        loginUserInfo.setGrades(userGradeSubjects.stream().map(UserGradeSubject::getGradeId).distinct().collect(Collectors.toList()));
        // 学科
        loginUserInfo.setSubjects(userGradeSubjects.stream().map(UserGradeSubject::getSubjectId).distinct().collect(Collectors.toList()));
        return loginUserInfo;
    }


}
