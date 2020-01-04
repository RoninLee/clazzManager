package com.school.manager.controller;

import com.school.manager.pojo.dto.common.Result;
import com.school.manager.pojo.dto.req.CommonSelOrDelReq;
import com.school.manager.pojo.dto.req.SubjectSaveReq;
import com.school.manager.pojo.dto.req.SubjectUpdateReq;
import com.school.manager.pojo.dto.resp.SubjectResp;
import com.school.manager.service.SubjectService;
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
    public Result<SubjectResp> info(@RequestBody @Valid CommonSelOrDelReq<String> request) {
        try {
            return Result.success(subjectService.info(request.getId()));
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @ApiOperation("学科列表")
    @PostMapping("/list")
    public Result<List<SubjectResp>> list() {
        try {
            return Result.success(subjectService.list());
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @ApiOperation("新增学科")
    @PostMapping("/save")
    public Result<SubjectResp> save(@RequestBody @Valid SubjectSaveReq request) {
        try {
            return Result.success(subjectService.save(request));
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @ApiOperation("更新学科")
    @PostMapping("/update")
    public Result<SubjectResp> update(@RequestBody @Valid SubjectUpdateReq request) {
        try {
            return Result.success(subjectService.update(request));
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @ApiOperation("删除学科")
    @PostMapping("/delete")
    public Result<Object> delete(@RequestBody @Valid CommonSelOrDelReq<String> request) {
        try {
            subjectService.delete(request.getId());
            return Result.success();
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
}
