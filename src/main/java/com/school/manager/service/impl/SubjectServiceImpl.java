package com.school.manager.service.impl;

import com.school.manager.common.Constant;
import com.school.manager.dao.SubjectDao;
import com.school.manager.dto.req.SubjectReq;
import com.school.manager.dto.resp.SubjectResp;
import com.school.manager.enums.StatusCode;
import com.school.manager.pojo.Subject;
import com.school.manager.service.SubjectService;
import com.school.manager.utils.BeanMapper;
import com.school.manager.utils.IdWorker;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author RoninLee
 * @description 学科管理
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
public class SubjectServiceImpl implements SubjectService {
    private final SubjectDao subjectDao;
    private IdWorker idWorker;

    @Autowired
    public SubjectServiceImpl(SubjectDao subjectDao, IdWorker idWorker) {
        this.subjectDao = subjectDao;
        this.idWorker = idWorker;
    }

    /**
     * 通过学科id查询学科信息
     *
     * @param id 学科id
     * @return 学科信息
     */
    @Override
    public SubjectResp info(String id) {
        return BeanMapper.def().map(subjectDao.findById(id).orElseThrow(() -> new RuntimeException(StatusCode.DATA_NOT_EXIST.getDesc())), SubjectResp.class);
    }

    /**
     * 查询学科列表
     *
     * @param request 请求对象
     * @return 学科列表
     */
    @Override
    public List<SubjectResp> list(SubjectReq request) {
        Integer pageSize = Optional.ofNullable(request.getPageSize()).orElse(Constant.PAGE_SIZE);
        Integer pageIndex = Optional.ofNullable(request.getPageIndex()).map(index -> (index - 1) * pageSize).orElse(Constant.PAGE_INDEX);
        return BeanMapper.def().mapList(subjectDao.list(pageIndex, pageSize), Subject.class, SubjectResp.class);
    }

    /**
     * 新增或保存学科
     *
     * @param request 请求对象
     * @return 学科信息
     */
    @Override
    public SubjectResp saveOrUpdate(SubjectReq request) {
        Subject subject = BeanMapper.def().map(request, Subject.class);
        if (StringUtils.isBlank(subject.getId())) {
            subject.setId(String.valueOf(idWorker.nextId()));
        }
        subjectDao.save(subject);
        return this.info(subject.getId());
    }

    /**
     * 删除学科
     *
     * @param id 学科id
     */
    @Override
    public void remove(String id) {
        Subject subject = subjectDao.findById(id).orElseThrow(() -> new RuntimeException(StatusCode.DATA_NOT_EXIST.getDesc()));
        subjectDao.delete(subject);
        // TODO: 2019/12/22 删除人员年级学科关联关系
    }
}
