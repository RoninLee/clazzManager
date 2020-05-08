package com.school.manager.service;

import com.school.manager.pojo.dto.common.BaseDTO;
import com.school.manager.pojo.dto.req.GradeSaveReq;
import com.school.manager.pojo.dto.req.GradeUpdateReq;
import com.school.manager.pojo.dto.resp.GradeResp;

import java.util.List;

/**
 * @author RoninLee
 * @description 年级管理
 */
public interface GradeService {

    /**
     * 通过id获取年级信息
     *
     * @param id 年级id
     * @return 年级信息
     */
    GradeResp info(String id);

    /**
     * 查询全部年级
     *
     * @return 年级列表
     */
    List<GradeResp> list();

    /**
     * 新增或更新年级
     *
     * @param request 请求对象
     * @return 年级id
     */
    String save(GradeSaveReq request);

    /**
     * 删除年级
     *
     * @param id 年级id
     * @param version
     */
    void delete(String id, Long version);

    /**
     * 年级下拉列表
     *
     * @return 年级列表
     */
    List<BaseDTO<String>> dropdownList();

    /**
     * 更新
     *
     * @param request 年级信息
     * @return 年级id
     */
    String update(GradeUpdateReq request);
}
