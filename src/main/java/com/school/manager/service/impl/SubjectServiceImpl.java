package com.school.manager.service.impl;

import com.google.common.collect.Lists;
import com.school.manager.common.constant.LongConstant;
import com.school.manager.enums.StatusCode;
import com.school.manager.exception.SysServiceException;
import com.school.manager.pojo.dao.GroupDao;
import com.school.manager.pojo.dao.SubjectDao;
import com.school.manager.pojo.dao.UserGradeSubjectDao;
import com.school.manager.pojo.dto.common.BaseDTO;
import com.school.manager.pojo.dto.req.SubjectSaveReq;
import com.school.manager.pojo.dto.req.SubjectUpdateReq;
import com.school.manager.pojo.dto.resp.SubjectResp;
import com.school.manager.pojo.entity.Subject;
import com.school.manager.service.SubjectService;
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
 * @description 学科管理
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
public class SubjectServiceImpl implements SubjectService {

    @Resource
    private SubjectDao subjectDao;
    @Autowired
    private IdWorker idWorker;

    @Resource
    private UserGradeSubjectDao userGradeSubjectDao;

    @Resource
    private GroupDao groupDao;

    /**
     * 通过学科id查询学科信息
     *
     * @param id 学科id
     * @return 学科信息
     */
    @Override
    public SubjectResp info(String id) {
        return BeanMapper.def().map(Optional.ofNullable(subjectDao.info(id)).orElseThrow(() -> new SysServiceException(StatusCode.DATA_NOT_EXIST.getDesc())), SubjectResp.class);
    }

    /**
     * 查询学科列表
     *
     * @return 学科列表
     */
    @Override
    public List<SubjectResp> list() {
        return Optional.ofNullable(subjectDao.list()).orElse(Lists.newArrayList());
    }

    /**
     * 新增或保存学科
     *
     * @param request 请求对象
     * @return 学科信息
     */
    @Override
    public SubjectResp save(SubjectSaveReq request) {
        Subject subject = BeanMapper.def().map(request, Subject.class);
        subject.setId(String.valueOf(idWorker.nextId()));
        subject.setVersion(LongConstant.ONE);
        subjectDao.save(subject);
        return this.info(subject.getId());
    }

    /**
     * 删除学科
     *
     * @param id      学科id
     * @param version
     */
    @Override
    public void delete(String id, Long version) {
        Subject subject = Optional.ofNullable(subjectDao.info(id)).orElseThrow(() -> new SysServiceException(StatusCode.DATA_NOT_EXIST.getDesc()));
        if (!Objects.equals(version, subject.getVersion())) {
            throw new SysServiceException(StatusCode.DATA_CHANGED.getDesc());
        }
        Integer count = userGradeSubjectDao.getBySubjectId(id);
        if (count > 0) {
            throw new SysServiceException(StatusCode.BINDING_USER.getDesc());
        }
        Integer subjectCount = groupDao.getBySubjectId(id);
        if (subjectCount > 0) {
            throw new SysServiceException(StatusCode.BINDING_GROUP.getDesc());
        }
        subjectDao.delete(id);
    }

    /**
     * 学科下拉列表
     *
     * @param name 模糊查询
     * @return 学科下拉
     */
    @Override
    public List<BaseDTO<String>> subjectList(String name) {
        return Optional.ofNullable(subjectDao.dropdownList(name)).orElse(Lists.newArrayList());
    }

    /**
     * 更新学科
     *
     * @param request 学科
     * @return 学科id
     */
    @Override
    public String update(SubjectUpdateReq request) {
        Subject source = Optional.ofNullable(subjectDao.info(request.getId())).orElseThrow(() -> new SysServiceException(StatusCode.DATA_NOT_EXIST.getDesc()));
        // 乐观锁
        if (!Objects.equals(request.getVersion(), source.getVersion())) {
            throw new SysServiceException(StatusCode.DATA_CHANGED.getDesc());
        }
        Subject subject = BeanMapper.def().map(request, Subject.class);
        subject.setVersion(subject.getVersion() + LongConstant.ONE);
        subjectDao.update(subject);
        return subject.getId();
    }
}
