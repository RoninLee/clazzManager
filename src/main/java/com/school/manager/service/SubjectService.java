package com.school.manager.service;

import com.school.manager.dto.req.SubjectReq;
import com.school.manager.dto.resp.SubjectResp;

import java.util.List;

/**
 * @author RoninLee
 * @description 学科管理
 */
public interface SubjectService {

    /**
     * 通过学科id查询学科信息
     *
     * @param id 学科id
     * @return 学科信息
     */
    SubjectResp info(String id);

    /**
     * 查询学科列表
     *
     * @param request 请求对象
     * @return 学科列表
     */
    List<SubjectResp> list(SubjectReq request);

    /**
     * 新增或保存学科
     *
     * @param request 请求对象
     * @return 学科信息
     */
    SubjectResp saveOrUpdate(SubjectReq request);

    /**
     * 删除学科
     *
     * @param id 学科id
     */
    void remove(String id);
}
