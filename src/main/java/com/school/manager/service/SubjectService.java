package com.school.manager.service;

import com.school.manager.pojo.dto.common.BaseDTO;
import com.school.manager.pojo.dto.req.SubjectSaveReq;
import com.school.manager.pojo.dto.req.SubjectUpdateReq;
import com.school.manager.pojo.dto.resp.SubjectResp;

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
     * @return 学科列表
     */
    List<SubjectResp> list();

    /**
     * 新增或保存学科
     *
     * @param request 请求对象
     * @return 学科信息
     */
    SubjectResp save(SubjectSaveReq request);

    /**
     * 删除学科
     *
     * @param id 学科id
     */
    void delete(String id);

    /**
     * 学科下拉列表
     *
     * @param name 模糊查询名称
     * @return 学科下拉
     */
    List<BaseDTO<String>> subjectList(String name);

    /**
     * 更新学科
     *
     * @param request 学科
     * @return 学科id
     */
    String update(SubjectUpdateReq request);
}
