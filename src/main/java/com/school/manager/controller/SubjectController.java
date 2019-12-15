package com.school.manager.controller;

import com.school.manager.common.Result;
import com.school.manager.dto.req.CommonSelOrDelReq;
import com.school.manager.dto.req.SubjectReq;
import com.school.manager.dto.resp.SubjectResp;
import com.school.manager.service.SubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author RoninLee
 * @description 学科管理
 */
@RestController
@Api(tags = "学科管理模块")
@RequestMapping("/subject")
@CrossOrigin
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    @ApiOperation("学科信息")
    @PostMapping("/info")
    public Result<SubjectResp> info(@RequestBody CommonSelOrDelReq<Long> request) {
        try {
            return Result.success(subjectService.info(request.getId()));
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @ApiOperation("学科列表")
    @PostMapping("/list")
    public Result<List<SubjectResp>> list(@RequestBody SubjectReq request) {
        try {
            return Result.success(subjectService.list(request));
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @ApiOperation("新增或更新学科")
    @PostMapping("/saveOrUpdate")
    public Result<SubjectResp> saveOrUpdate(@RequestBody SubjectReq request) {
        try {
            return Result.success(subjectService.saveOrUpdate(request));
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @ApiOperation("删除学科")
    @PostMapping("/remove")
    public Result<Object> remove(@RequestBody CommonSelOrDelReq<Long> request) {
        try {
            subjectService.remove(request.getId());
            return Result.success();
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
}
