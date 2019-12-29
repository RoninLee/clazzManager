package com.school.manager.service;

import com.school.manager.dto.req.GradeReq;
import com.school.manager.dto.resp.GradeResp;

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
     * @return 年级信息
     */
    GradeResp saveOrUpdate(GradeReq request);

    /**
     * 删除年级
     *
     * @param id 年级id
     */
    void remove(String id);

}
