package com.school.manager.service;

import com.school.manager.pojo.dto.common.BaseDTO;
import com.school.manager.pojo.dto.req.GroupAddReq;
import com.school.manager.pojo.dto.req.GroupUpdateReq;
import com.school.manager.pojo.dto.resp.GroupLessonListResp;
import com.school.manager.pojo.dto.resp.GroupListInfoResp;

import java.util.List;

/**
 * @author lizelong
 * @description 组模块接口
 */
public interface GroupService {

    /**
     * 查询组长列表
     *
     * @return 组长列表
     */
    List<BaseDTO<String>> leaderList();

    /**
     * 查询课程列表
     *
     * @param leaderId 组长ID
     * @return 课程列表
     */
    List<GroupLessonListResp> lessonList(String leaderId);

    /**
     * 查询组员列表
     *
     * @param gradeId   年级ID
     * @param subjectId 学科ID
     * @return 组员列表
     */
    List<BaseDTO<String>> memberList(String gradeId, String subjectId);

    /**
     * 组列表
     *
     * @param name 模糊查询 组员名字或工号
     * @param pageIndex
     * @param pageSize
     * @return 组列表
     */
    List<GroupListInfoResp> list(String name, Integer pageIndex, Integer pageSize);

    /**
     * 组详情信息
     *
     * @param id 组ID
     * @return 组信息
     */
    GroupListInfoResp info(String id);

    /**
     * 添加组信息
     *
     * @param request 请求对象
     */
    void add(GroupAddReq request);

    /**
     * 更新组信息
     *
     * @param request 请求对象
     */
    void update(GroupUpdateReq request);

    /**
     * 删除组
     *
     * @param id 组id
     */
    void delete(String id, Long version);
}
