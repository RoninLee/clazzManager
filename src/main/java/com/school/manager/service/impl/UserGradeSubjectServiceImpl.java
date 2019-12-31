package com.school.manager.service.impl;

import com.school.manager.common.constant.Constant;
import com.school.manager.common.resp.PageResult;
import com.school.manager.dao.UserGradeSubjectDao;
import com.school.manager.dto.req.UserGradeSubjectReq;
import com.school.manager.dto.resp.UserGradeSubjectResp;
import com.school.manager.enums.StatusCode;
import com.school.manager.exception.SysServiceException;
import com.school.manager.pojo.UserGradeSubject;
import com.school.manager.service.UserGradeSubjectService;
import com.school.manager.utils.BeanMapper;
import com.school.manager.utils.IdWorker;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * @author RoninLee
 * @description 人员年级学科关联关系
 */
@Slf4j
@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
public class UserGradeSubjectServiceImpl implements UserGradeSubjectService {

    @Resource
    private UserGradeSubjectDao userGradeSubjectDao;
    @Autowired
    private IdWorker idWorker;

    /**
     * 保存关联关系
     *
     * @param request 关联关系
     */
    @Override
    public void saveOrUpdate(UserGradeSubjectReq request) {
        UserGradeSubject gradeSubject = BeanMapper.def().map(request, UserGradeSubject.class);
        if (StringUtils.isBlank(gradeSubject.getId())) {
            gradeSubject.setId(String.valueOf(idWorker.nextId()));
        }
        userGradeSubjectDao.save(gradeSubject);
    }

    /**
     * 删除关联关系
     *
     * @param id id
     */
    @Override
    public void delete(String id) {
        userGradeSubjectDao.deleteById(id);
    }

    /**
     * 删除关联关系
     *
     * @param userId 用户id
     */
    @Override
    public void deleteAllByUserId(String userId) {
        userGradeSubjectDao.deleteAllByUserId(userId);
    }

    /**
     * 模糊查询用户关系列表
     *
     * @param fuzzyName 查询内容
     * @return 关系列表
     */
    @Override
    public PageResult<List<UserGradeSubjectResp>> fuzzyQueryList(String fuzzyName, Integer pageIndex, Integer pageSize) {
        int size = Optional.ofNullable(pageSize).orElse(Constant.PAGE_SIZE);
        int index = Optional.ofNullable(pageIndex).map(i -> Math.abs((i - 1) * size)).orElse(Constant.PAGE_INDEX);
        Pageable pageable = PageRequest.of(index, size);
        Page<UserGradeSubjectResp> fuzzyQueryList = userGradeSubjectDao.fuzzyQueryList(fuzzyName, pageable);
        PageResult<List<UserGradeSubjectResp>> pageResult = new PageResult<>();
        pageResult.setTotal(fuzzyQueryList.getTotalElements());
        pageResult.setData(fuzzyQueryList.getContent());
        pageResult.setPages(fuzzyQueryList.getTotalPages());
        return pageResult;
    }

    /**
     * 根据关系id获取关系详情
     *
     * @param id 关系列表id
     * @return 关系详情
     */
    @Override
    public UserGradeSubjectResp findById(String id) {
        UserGradeSubject userGradeSubject = userGradeSubjectDao.findById(id).orElseThrow(() -> new SysServiceException(StatusCode.LOGIN_FAILURE.getDesc()));
        return BeanMapper.def().map(userGradeSubject, UserGradeSubjectResp.class);
    }

    /**
     * 根据用户id获取年级学科绑定关系
     *
     * @param userId 用户id
     * @return 年级学科绑定关系
     */
    @Override
    public List<UserGradeSubject> findByUserId(String userId) {
        return userGradeSubjectDao.findUserGradeSubjectsByUserId(userId);
    }
}
