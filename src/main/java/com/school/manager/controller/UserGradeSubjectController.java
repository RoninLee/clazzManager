package com.school.manager.controller;

import com.school.manager.common.resp.Result;
import com.school.manager.dto.req.CommonFuzzySelReq;
import com.school.manager.dto.req.CommonSelOrDelReq;
import com.school.manager.dto.req.UserGradeSubjectReq;
import com.school.manager.service.UserGradeSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author RoninLee
 * @description 用户关联关系
 */
@Api(tags = "用户关联关系管理模块")
@RestController
@CrossOrigin
@RequestMapping("/userRelation")
public class UserGradeSubjectController {

    @Autowired
    private UserGradeSubjectService userGradeSubjectService;

    @ApiOperation("绑定或更新年级学科")
    @PostMapping("/saveOrUpdate")
    public Result save(@RequestBody @Valid UserGradeSubjectReq request) {
        userGradeSubjectService.saveOrUpdate(request);
        return Result.success();
    }

    @ApiOperation("列表")
    @PostMapping("/list")
    public Result fuzzyQueryList(@RequestBody CommonFuzzySelReq request) {
        return userGradeSubjectService.fuzzyQueryList(request.getName(), request.getPageIndex(), request.getPageSize());
    }

    @ApiOperation("删除关联关系")
    @PostMapping("delete")
    public Result delete(@RequestBody @Valid CommonSelOrDelReq<String> request) {
        userGradeSubjectService.delete(request.getId());
        return Result.success();
    }
}
