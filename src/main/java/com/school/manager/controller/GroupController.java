package com.school.manager.controller;

import com.school.manager.pojo.dto.common.*;
import com.school.manager.pojo.dto.req.GroupAddReq;
import com.school.manager.pojo.dto.req.GroupMemberListReq;
import com.school.manager.pojo.dto.req.GroupUpdateReq;
import com.school.manager.pojo.dto.resp.GroupLessonListResp;
import com.school.manager.pojo.dto.resp.GroupListInfoResp;
import com.school.manager.service.GroupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @author lizelong
 * @description 组模块管理
 */
@Api(tags = "组模块管理")
@RestController
@RequestMapping("/group/")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @ApiOperation("查询组长列表")
    @PostMapping("leaderList")
    public Result<List<BaseDTO<String>>> leaderList() {
        return Result.success(groupService.leaderList());
    }

    @ApiOperation("查询课程列表")
    @PostMapping("lessonList")
    public Result<List<GroupLessonListResp>> lessonList(@RequestBody @Valid CommonIdReq<String> request) {
        return Result.success(groupService.lessonList(request.getId()));
    }

    @ApiOperation("查询组员列表")
    @PostMapping("memberList")
    public Result<List<BaseDTO<String>>> memberList(@RequestBody @Valid GroupMemberListReq request) {
        return Result.success(groupService.memberList(request.getGradeId(), request.getSubjectId()));
    }

    @ApiOperation("组列表")
    @PostMapping("list")
    public Result<List<GroupListInfoResp>> list(@RequestBody @Valid CommonFuzzySelReq request) {
        return Result.success(groupService.list(request.getName(), request.getPageIndex(), request.getPageSize()));
    }

    @ApiOperation("组信息")
    @PostMapping("info")
    public Result<GroupListInfoResp> info(@RequestBody @Valid CommonIdReq<String> request) {
        return Result.success(groupService.info(request.getId()));
    }

    @ApiOperation("添加组")
    @PostMapping("add")
    public Result<Void> add(@RequestBody @Valid GroupAddReq request) {
        groupService.add(request);
        return Result.success();
    }

    @ApiOperation("更新组")
    @PostMapping("update")
    public Result<Void> update(@RequestBody @Valid GroupUpdateReq request) {
        groupService.update(request);
        return Result.success();

    }

    @ApiOperation("删除组")
    @PostMapping("delete")
    public Result<Void> delete(@RequestBody @Valid CommonDelReq<String> request) {
        groupService.delete(request.getId(), request.getVersion());
        return Result.success();

    }
}
