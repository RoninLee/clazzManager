package com.school.manager.service;

import com.google.common.collect.Lists;
import com.school.manager.dao.GradeDao;
import com.school.manager.dto.req.GradeReq;
import com.school.manager.dto.resp.GradeResp;
import com.school.manager.enums.StatusCode;
import com.school.manager.pojo.Grade;
import com.school.manager.utils.BeanMapper;
import com.school.manager.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author RoninLee
 * @description 年级管理
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
public class GradeService {
    @Autowired
    private GradeDao gradeDao;
    @Autowired
    private IdWorker idWorker;

    /**
     * 通过id获取年级信息
     *
     * @param id 年级id
     * @return 年级信息
     */
    public GradeResp info(Long id) {
        return BeanMapper.def().map(gradeDao.findById(id).orElseThrow(() -> new RuntimeException(StatusCode.DATA_NOT_EXIST.getDesc())), GradeResp.class);
    }

    /**
     * 查询全部年级
     *
     * @return 年级列表
     */
    public List<GradeResp> list() {
        return BeanMapper.def().mapList(Optional.of(gradeDao.findAll()).orElse(Lists.newArrayList()), Grade.class, GradeResp.class);
    }

    /**
     * 新增或更新年级
     *
     * @param request 请求对象
     * @return 年级信息
     */
    public GradeResp saveOrUpdate(GradeReq request) {
        Grade grade = BeanMapper.def().map(request, Grade.class);
        if (Objects.isNull(grade.getId()) || grade.getId() == 0) {
            grade.setId(idWorker.nextId());
        }
        gradeDao.save(grade);
        return this.info(grade.getId());
    }

    /**
     * 删除年级
     *
     * @param id 年级id
     */
    public void remove(Long id) {
        gradeDao.deleteById(id);
        // TODO: 2019/12/15 删除年级相关关联关系
    }

}
