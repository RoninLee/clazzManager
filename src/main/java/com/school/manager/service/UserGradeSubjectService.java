package com.school.manager.service;

import com.school.manager.pojo.dto.common.PageResult;
import com.school.manager.pojo.dto.req.UserGradeSubjectSaveReq;
import com.school.manager.pojo.dto.req.UserGradeSubjectUpdateSaveReq;
import com.school.manager.pojo.dto.resp.UserGradeSubjectResp;
import com.school.manager.pojo.entity.UserGradeSubject;

import java.util.List;

/**
 * @author RoninLee
 * @description 人员年级学科关联关系
 */
public interface UserGradeSubjectService {
    /**
     * 保存关联关系
     *
     * @param request 关联关系
     */
    String save(UserGradeSubjectSaveReq request);

    /**
     * 删除关联关系
     *
     * @param id id
     */
    void delete(String id);

    /**
     * 删除关联关系
     *
     * @param userId 用户id
     */
    void deleteAllByUserId(String userId);

    /**
     * 模糊查询用户关系列表
     *
     * @param fuzzyName 查询内容
     * @return 关系列表
     */
    PageResult<List<UserGradeSubjectResp>> fuzzyQueryList(String fuzzyName, Integer pageIndex, Integer pageSize);

    /**
     * 根据关系id获取关系详情
     *
     * @param id 关系列表id
     * @return 关系详情
     */
    UserGradeSubjectResp findById(String id);

    /**
     * 根据用户id获取年级学科绑定关系
     *
     * @param userId 用户id
     * @return 年级学科绑定关系
     */
    List<UserGradeSubject> listByUserId(String userId);

    /**
     * 更新人员年级学科关系
     *
     * @param request 年级学科信息
     * @return id
     */
    String update(UserGradeSubjectUpdateSaveReq request);
}
