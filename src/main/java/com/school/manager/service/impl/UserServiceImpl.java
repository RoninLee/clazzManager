package com.school.manager.service.impl;

import com.google.common.collect.Lists;
import com.school.manager.common.constant.LongConstant;
import com.school.manager.enums.RoleEnum;
import com.school.manager.enums.StatusCode;
import com.school.manager.exception.SysServiceException;
import com.school.manager.jwt.LoginUserInfo;
import com.school.manager.jwt.LoginUserUtil;
import com.school.manager.pojo.dao.UserDao;
import com.school.manager.pojo.dto.common.BaseDTO;
import com.school.manager.pojo.dto.common.FuzzyQueryReq;
import com.school.manager.pojo.dto.common.PageResult;
import com.school.manager.pojo.dto.req.LoginReq;
import com.school.manager.pojo.dto.req.UserSaveReq;
import com.school.manager.pojo.dto.req.UserUpdateReq;
import com.school.manager.pojo.dto.resp.UserResp;
import com.school.manager.pojo.entity.User;
import com.school.manager.pojo.entity.UserGradeSubject;
import com.school.manager.pojo.entity.UserPassword;
import com.school.manager.pojo.entity.UserRole;
import com.school.manager.service.UserGradeSubjectService;
import com.school.manager.service.UserPasswordService;
import com.school.manager.service.UserRoleService;
import com.school.manager.service.UserService;
import com.school.manager.utils.BeanMapper;
import com.school.manager.utils.IdWorker;
import com.school.manager.utils.Md5Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author RoninLee
 * @description 用户管理
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;
    @Autowired
    private UserPasswordService userPasswordService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private UserGradeSubjectService userGradeSubjectService;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private PasswordEncoder passwordEncoder;


    /**
     * 登录
     *
     * @param request 请求对象
     * @return 用户信息
     */
    @Override
    public LoginUserInfo login(LoginReq request) {
        LoginUserInfo loginUserInfo = this.loginUserInfo(request.getJobNumber());
        if (passwordEncoder.matches(request.getPassword(), loginUserInfo.getPassword())) {
            return loginUserInfo;
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
    public PageResult<List<BaseDTO<String>>> list(FuzzyQueryReq request) {
        int pageIndex = (request.getPageIndex() - 1) * request.getPageSize();
        List<BaseDTO<String>> userList = userDao.pageList(request.getName(), pageIndex, request.getPageSize());
        Long listCount = userDao.pageListCount(request.getName());
        return PageResult.success(userList, listCount);
    }

    /**
     * 通过id查人员
     *
     * @param id id
     * @return 人员信息
     */
    @Override
    public UserResp info(String id) {
        return BeanMapper.def().map(Optional.ofNullable(userDao.info(id)).orElseThrow(() -> new SysServiceException(StatusCode.DATA_NOT_EXIST.getDesc())), UserResp.class);
    }

    /**
     * 新增用户
     *
     * @param request 用户信息
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public String save(UserSaveReq request) {
        // 转换对象
        User user = BeanMapper.def().map(request, User.class);
        // 雪花算法生成id
        user.setId(String.valueOf(idWorker.nextId()));
        Boolean isGroupLeader = Optional.ofNullable(request.getGroupLeaderFlag()).orElse(Boolean.FALSE);
        user.setGroupLeaderFlag(isGroupLeader);
        user.setVersion(LongConstant.ONE);
        userDao.save(user);
        // 用户密码
        UserPassword userPassword = new UserPassword();
        userPassword.setId(user.getId());
        // 默认密码
        String defaultPassword = "Sxzx2019";
        // 采用Spring Security二次加密
        userPassword.setPassword(passwordEncoder.encode(Md5Util.md5(defaultPassword)));
        userPasswordService.saveOrUpdate(userPassword);
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
    public String update(UserUpdateReq request) {
        User source = Optional.ofNullable(userDao.info(request.getId())).orElseThrow(() -> new SysServiceException(StatusCode.DATA_NOT_EXIST.getDesc()));
        // 乐观锁
        if (!Objects.equals(source.getVersion(), request.getVersion())) {
            throw new SysServiceException(StatusCode.DATA_CHANGED.getDesc());
        }
        // 检验账号是否已经存在
        User userByJobNumber = userDao.findUserByJobNumber(request.getJobNumber());
        if (Objects.nonNull(userByJobNumber) && !StringUtils.equals(userByJobNumber.getId(), request.getId())) {
            throw new SysServiceException(StatusCode.JOB_NUMBER_EXIST.getDesc());
        }
        User user = BeanMapper.def().map(request, User.class);
        user.setVersion(user.getVersion() + LongConstant.ONE);
        // 判断是否组长角色
        Boolean groupLeaderFlag = Optional.ofNullable(request.getGroupLeaderFlag()).orElse(Boolean.FALSE);
        if (groupLeaderFlag) {
            user.setGroupLeaderFlag(groupLeaderFlag);
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
        userDao.save(user);
        return request.getId();
    }

    /**
     * 删除用户
     *
     * @param id 用户id
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public void delete(String id) {
        userDao.delete(id);
        userPasswordService.delete(id);
        //删除用户角色关联关系
        userRoleService.deleteUserRoleBuUserId(id);
        // 删除用户和年级学科关系
        userGradeSubjectService.deleteAllByUserId(id);
        // TODO: 2019/12/22 要不要删除教案相关记录
    }

    /**
     * 查询用户详情
     *
     * @param jobNumber 工号
     * @return 用户详细信息
     */
    @Override
    public LoginUserInfo loginUserInfo(String jobNumber) {
        // 根据工号查询用户信息
        User user = userDao.findUserByJobNumber(jobNumber);
        Optional.ofNullable(user).orElseThrow(() -> new SysServiceException(StatusCode.DATA_NOT_EXIST.getDesc()));
        LoginUserInfo loginUserInfo = new LoginUserInfo();
        BeanUtils.copyProperties(user, loginUserInfo);
        // 用户密码
        String password = userPasswordService.findById(user.getId());
        loginUserInfo.setPassword(password);
        // 是否管理员
        loginUserInfo.setAdminFlag(StringUtils.equals(user.getJobNumber(), RoleEnum.ADMIN.getDesc()) ? Boolean.TRUE : Boolean.FALSE);
        List<UserGradeSubject> userGradeSubjects = userGradeSubjectService.listByUserId(user.getId());
        // 用户、年级、学科绑定关系
        loginUserInfo.setUserGradeSubjects(userGradeSubjects);
        // 年级
        loginUserInfo.setGrades(userGradeSubjects.stream().map(UserGradeSubject::getGradeId).distinct().collect(Collectors.toList()));
        // 学科
        loginUserInfo.setSubjects(userGradeSubjects.stream().map(UserGradeSubject::getSubjectId).distinct().collect(Collectors.toList()));
        return loginUserInfo;
    }

    /**
     * 修改密码
     *
     * @param oldPassword 原始密码
     * @param newPassword 新密码
     */
    @Override
    public void updatePassword(String oldPassword, String newPassword) {
        LoginUserInfo loginUserInfo = Optional.ofNullable(LoginUserUtil.getLoginUserInfo()).orElseThrow(() -> new SysServiceException(StatusCode.NO_LOGIN_INFO.getDesc()));
        String password = userPasswordService.findById(loginUserInfo.getId());
        if (!passwordEncoder.matches(oldPassword, password)) {
            throw new SysServiceException(StatusCode.WRONG_PASSWORD.getDesc());
        }
        UserPassword userPassword = new UserPassword();
        userPassword.setId(loginUserInfo.getId());
        userPassword.setPassword(passwordEncoder.encode(newPassword));
        userPasswordService.saveOrUpdate(userPassword);
    }

    /**
     * 用户下拉列表
     *
     * @param name 模糊查询
     * @return 用户列表
     */
    @Override
    public List<BaseDTO<String>> userList(String name) {
        return Optional.ofNullable(userDao.dropdownList(name)).orElse(Lists.newArrayList());
    }
}
