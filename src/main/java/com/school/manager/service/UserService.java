package com.school.manager.service;

import com.google.common.collect.Lists;
import com.school.manager.dao.UserDao;
import com.school.manager.dto.req.UserReq;
import com.school.manager.dto.resp.UserResp;
import com.school.manager.common.Constant;
import com.school.manager.enums.StateEnum;
import com.school.manager.enums.StatusCode;
import com.school.manager.pojo.User;
import com.school.manager.pojo.UserPwd;
import com.school.manager.utils.BeanMapper;
import com.school.manager.utils.IdWorker;
import com.school.manager.utils.Md5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

/**
 * @author RoninLee
 * @description 用户管理
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
public class UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserPwdService userPwdService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private IdWorker idWorker;

    /**
     * 分页查询用户列表
     *
     * @param request 请求对象
     * @return 人员列表
     */
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
                if (StringUtils.isNotBlank(request.getName())) {
                    Predicate predicate = criteriaBuilder.like(root.get("name").as(String.class), "%" + request.getName() + "%");
                    list.add(predicate);
                }
                if (StringUtils.isNotBlank(request.getJobNumber())) {
                    Predicate predicate = criteriaBuilder.like(root.get("jobNumber").as(String.class), "%" + request.getJobNumber() + "%");
                    list.add(predicate);
                }
                Predicate state = criteriaBuilder.equal(root.get("state").as(Integer.class), StateEnum.valid.getCode());
                list.add(state);
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
    public UserResp findById(Long id) {
        return BeanMapper.def().map(userDao.findById(id).orElseThrow(() -> new RuntimeException(StatusCode.DATA_NOT_EXIST.getDesc())), UserResp.class);
    }

    /**
     * 新增用户
     *
     * @param request 用户信息
     */
    public Long save(UserReq request) {
        // 转换对象
        User user = BeanMapper.def().map(request, User.class);
        // 雪花算法生成id
        user.setId(idWorker.nextId());
        user.setState(StateEnum.valid.getCode());
        userDao.save(user);
        // 用户密码
        UserPwd userPwd = new UserPwd();
        userPwd.setId(user.getId());
        // 默认密码
        String defaultPwd = "Sxzx2019";
        userPwd.setPwd(Md5Util.md5(defaultPwd));
        userPwdService.saveOrUpdate(userPwd);
        return user.getId();
    }

    /**
     * 更新用户
     *
     * @param request 用户信息
     */
    public Long update(UserReq request) {
        User user = BeanMapper.def().map(request, User.class);
        user.setState(StateEnum.valid.getCode());
        userDao.save(user);
        UserPwd userPwd = BeanMapper.def().map(request, UserPwd.class);
        userPwdService.saveOrUpdate(userPwd);
        return request.getId();
    }

    /**
     * 逻辑删除用户
     *
     * @param id 用户id
     */
    public void removeById(Long id) {
        User user = userDao.findById(id).orElseThrow(() -> new RuntimeException(StatusCode.DATA_NOT_EXIST.getDesc()));
        user.setState(StateEnum.invalid.getCode());
        userDao.save(user);
        // 删除用户角色关联关系
        //userRoleService.deleteUserRoleBuUserId(user.getId());
        // TODO: 2019/12/15 还要删除用户的其他关联关系
    }
}
