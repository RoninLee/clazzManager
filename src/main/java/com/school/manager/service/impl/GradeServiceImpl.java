package com.school.manager.service.impl;

import com.google.common.collect.Lists;
import com.school.manager.common.constant.LongConstant;
import com.school.manager.enums.StatusCode;
import com.school.manager.exception.SysServiceException;
import com.school.manager.pojo.dao.GradeDao;
import com.school.manager.pojo.dto.common.BaseDTO;
import com.school.manager.pojo.dto.req.GradeSaveReq;
import com.school.manager.pojo.dto.req.GradeUpdateReq;
import com.school.manager.pojo.dto.resp.GradeResp;
import com.school.manager.pojo.entity.Grade;
import com.school.manager.service.GradeService;
import com.school.manager.utils.BeanMapper;
import com.school.manager.utils.IdWorker;
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
 * @description 年级管理
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
public class GradeServiceImpl implements GradeService {
    @Resource
    private GradeDao gradeDao;
    @Autowired
    private IdWorker idWorker;

    /**
     * 通过id获取年级信息
     *
     * @param id 年级id
     * @return 年级信息
     */
    @Override
    public GradeResp info(String id) {
        return BeanMapper.def().map(Optional.ofNullable(gradeDao.info(id)).orElseThrow(() -> new SysServiceException(StatusCode.DATA_NOT_EXIST.getDesc())), GradeResp.class);
    }

    /**
     * 查询全部年级
     *
     * @return 年级列表
     */
    @Override
    public List<GradeResp> list() {
        return Optional.ofNullable(gradeDao.list()).orElse(Lists.newArrayList());
    }

    /**
     * 新增或更新年级
     *
     * @param request 请求对象
     * @return 年级id
     */
    @Override
    public String save(GradeSaveReq request) {
        Grade grade = BeanMapper.def().map(request, Grade.class);
        grade.setId(String.valueOf(idWorker.nextId()));
        grade.setVersion(LongConstant.ONE);
        gradeDao.save(grade);
        return grade.getId();
    }

    /**
     * 删除年级
     *
     * @param id 年级id
     */
    @Override
    public void delete(String id) {
        gradeDao.delete(id);
        // TODO: 2019/12/15 删除年级相关关联关系
    }

    /**
     * 年级下拉列表
     *
     * @return 年级列表
     */
    @Override
    public List<BaseDTO<String>> dropdownList() {
        return Optional.ofNullable(gradeDao.dropdownList()).orElse(Lists.newArrayList());
    }

    /**
     * 更新
     *
     * @param request 年级信息
     * @return 年级id
     */
    @Override
    public String update(GradeUpdateReq request) {
        Grade source = Optional.ofNullable(gradeDao.info(request.getId())).orElseThrow(() -> new SysServiceException(StatusCode.DATA_NOT_EXIST.getDesc()));
        // 乐观锁
        if (!Objects.equals(source.getVersion(), request.getVersion())) {
            throw new SysServiceException(StatusCode.DATA_CHANGED.getDesc());
        }
        Grade grade = BeanMapper.def().map(request, Grade.class);
        // 版本号加一
        grade.setVersion(request.getVersion() + LongConstant.ONE);
        gradeDao.update(grade);
        return grade.getId();
    }

}
