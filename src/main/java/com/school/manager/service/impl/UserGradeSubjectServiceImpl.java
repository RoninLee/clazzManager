package com.school.manager.service.impl;

import com.google.common.collect.Lists;
import com.school.manager.common.constant.LongConstant;
import com.school.manager.enums.StatusCode;
import com.school.manager.exception.SysServiceException;
import com.school.manager.pojo.dao.UserGradeSubjectDao;
import com.school.manager.pojo.dto.common.PageResult;
import com.school.manager.pojo.dto.req.UserGradeSubjectSaveReq;
import com.school.manager.pojo.dto.req.UserGradeSubjectUpdateSaveReq;
import com.school.manager.pojo.dto.resp.UserGradeSubjectResp;
import com.school.manager.pojo.entity.UserGradeSubject;
import com.school.manager.service.UserGradeSubjectService;
import com.school.manager.utils.BeanMapper;
import com.school.manager.utils.IdWorker;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
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
    public String save(UserGradeSubjectSaveReq request) {
        UserGradeSubject gradeSubject = BeanMapper.def().map(request, UserGradeSubject.class);
        if (StringUtils.isBlank(gradeSubject.getId())) {
            gradeSubject.setId(String.valueOf(idWorker.nextId()));
        }
        userGradeSubjectDao.save(gradeSubject);
        return gradeSubject.getId();
    }

    /**
     * 删除关联关系
     *
     * @param id id
     */
    @Override
    public void delete(String id) {
        userGradeSubjectDao.delete(id);
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
        // TODO: 2020/1/5 怎么展示列表 待商榷
        return PageResult.success(Lists.newArrayList(), 1L);
    }

    /**
     * 根据关系id获取关系详情
     *
     * @param id 关系列表id
     * @return 关系详情
     */
    @Override
    public UserGradeSubjectResp findById(String id) {
        return Optional.ofNullable(userGradeSubjectDao.info(id)).orElseThrow(() -> new SysServiceException(StatusCode.LOGIN_FAILURE.getDesc()));
    }

    /**
     * 根据用户id获取年级学科绑定关系
     *
     * @param userId 用户id
     * @return 年级学科绑定关系
     */
    @Override
    public List<UserGradeSubject> listByUserId(String userId) {
        return userGradeSubjectDao.listByUserId(userId);
    }

    /**
     * 更新人员年级学科关系
     *
     * @param request 年级学科信息
     * @return id
     */
    @Override
    public String update(UserGradeSubjectUpdateSaveReq request) {
        UserGradeSubject source = Optional.ofNullable(userGradeSubjectDao.getById(request.getId())).orElseThrow(() -> new SysServiceException(StatusCode.DATA_NOT_EXIST.getDesc()));
        if (!Objects.equals(source.getVersion(), request.getVersion())) {
            throw new SysServiceException(StatusCode.DATA_CHANGED.getDesc());
        }
        UserGradeSubject userGradeSubject = BeanMapper.def().map(request, UserGradeSubject.class);
        userGradeSubject.setVersion(userGradeSubject.getVersion() + LongConstant.ONE);
        userGradeSubjectDao.update(userGradeSubject);
        return userGradeSubject.getId();
    }
}
