package com.school.manager.controller;

import com.school.manager.pojo.dto.common.BaseDTO;
import com.school.manager.pojo.dto.common.FuzzyQueryReq;
import com.school.manager.pojo.dto.common.Result;
import com.school.manager.pojo.dto.common.CommonFuzzySelReq;
import com.school.manager.pojo.dto.common.CommonSelOrDelReq;
import com.school.manager.pojo.dto.req.UserGradeSubjectSaveReq;
import com.school.manager.pojo.dto.req.UserGradeSubjectUpdateSaveReq;
import com.school.manager.pojo.dto.resp.UserDropdownListResp;
import com.school.manager.pojo.dto.resp.UserGradeSubjectResp;
import com.school.manager.service.GradeService;
import com.school.manager.service.SubjectService;
import com.school.manager.service.UserGradeSubjectService;
import com.school.manager.service.UserService;
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
 * @description 用户关联关系
 */
@Api(tags = "用户关联关系管理模块")
@RestController
@CrossOrigin
@RequestMapping("/userRelation")
public class UserGradeSubjectController {

    @Autowired
    private UserGradeSubjectService userGradeSubjectService;

    @Autowired
    private UserService userService;

    @Autowired
    private GradeService gradeService;

    @Autowired
    private SubjectService subjectService;

    @ApiOperation("绑定年级学科")
    @PostMapping("/save")
    public Result<String> save(@RequestBody @Valid UserGradeSubjectSaveReq request) {
        return Result.success(userGradeSubjectService.save(request));
    }

    @ApiOperation("更新年级学科")
    @PostMapping("/update")
    public Result<String> update(@RequestBody @Valid UserGradeSubjectUpdateSaveReq request) {
        return Result.success(userGradeSubjectService.update(request));
    }

    @ApiOperation("列表")
    @PostMapping("/list")
    public Result<List<UserGradeSubjectResp>> fuzzyQueryList(@RequestBody CommonFuzzySelReq request) {
        return userGradeSubjectService.fuzzyQueryList(request.getName(), request.getPageIndex(), request.getPageSize());
    }

    @ApiOperation("删除关联关系")
    @PostMapping("delete")
    public Result<Void> delete(@RequestBody @Valid CommonSelOrDelReq<String> request) {
        userGradeSubjectService.delete(request.getId());
        return Result.success();
    }

    @ApiOperation("用户列表")
    @PostMapping("/userList")
    public Result<List<UserDropdownListResp>> userList(@RequestBody FuzzyQueryReq request) {
        return Result.success(userService.userList(request.getName()));
    }

    @ApiOperation("年级列表")
    @PostMapping("/gradeList")
    public Result<List<BaseDTO<String>>> gradeList() {
        return Result.success(gradeService.dropdownList());
    }

    @ApiOperation("学科列表")
    @PostMapping("/subjectList")
    public Result<List<BaseDTO<String>>> subjectList(@RequestBody FuzzyQueryReq request) {
        return Result.success(subjectService.subjectList(request.getName()));
    }
}
