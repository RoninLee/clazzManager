package com.school.manager.controller;

import com.school.manager.pojo.dto.common.Result;
import com.school.manager.pojo.dto.req.CommonSelOrDelReq;
import com.school.manager.pojo.dto.req.GradeSaveReq;
import com.school.manager.pojo.dto.req.GradeUpdateReq;
import com.school.manager.pojo.dto.resp.GradeResp;
import com.school.manager.service.GradeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @author RoninLee
 * @description 年级管理
 */
@Api(tags = "年级管理模块")
@RestController
@CrossOrigin
@RequestMapping("/grade")
public class GradeController {
    @Autowired
    private GradeService gradeService;

    @ApiOperation("年级列表")
    @PostMapping("/list")
    public Result<List<GradeResp>> list() {
        try {
            return Result.success(gradeService.list());
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @ApiOperation("年级信息")
    @PostMapping("/info")
    public Result<GradeResp> info(@RequestBody @Valid CommonSelOrDelReq<String> request) {
        try {
            return Result.success(gradeService.info(request.getId()));
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @ApiOperation("新增年级")
    @PostMapping("/save")
    public Result<GradeResp> save(@RequestBody @Valid GradeSaveReq request) {
        try {
            return Result.success(gradeService.save(request));
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @ApiOperation("更新年级")
    @PostMapping("/update")
    public Result<GradeResp> update(@RequestBody @Valid GradeUpdateReq request) {
        try {
            return Result.success(gradeService.update(request));
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @ApiOperation("删除年级")
    @PostMapping("/remove")
    public Result<Object> remove(@RequestBody @Valid CommonSelOrDelReq<String> request) {
        try {
            gradeService.remove(request.getId());
            return Result.success();
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
}
