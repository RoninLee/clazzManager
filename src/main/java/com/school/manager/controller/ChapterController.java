package com.school.manager.controller;

import com.school.manager.pojo.dto.common.CommonIdReq;
import com.school.manager.pojo.dto.common.Result;
import com.school.manager.pojo.dto.req.ChapterSaveReq;
import com.school.manager.pojo.dto.req.ChapterUpdateReq;
import com.school.manager.pojo.dto.resp.ChapterInfoResp;
import com.school.manager.pojo.dto.resp.GradeSubjectResp;
import com.school.manager.service.ChapterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @author RoninLee
 * @description 章节管理
 */
@Api(tags = "章节管理")
@RestController
@RequestMapping("/chapter/")
public class ChapterController {
    @Resource
    private ChapterService chapterService;

    @ApiOperation("新增")
    @PostMapping("/save")
    public Result<String> save(@RequestBody @Valid ChapterSaveReq request) {
        return Result.success(chapterService.save(request));
    }

    @ApiOperation("删除")
    @PostMapping("/delete")
    public Result<Void> delete(@RequestBody @Valid CommonIdReq<String> request) {
        chapterService.delete(request.getId());
        return Result.success();
    }

    @ApiOperation("更新")
    @PostMapping("/update")
    public Result<String> update(@RequestBody @Valid ChapterUpdateReq request) {
        return Result.success(chapterService.update(request));
    }

    @ApiOperation("[查询] 根据主键 id 查询")
    @PostMapping("/info")
    public Result<ChapterInfoResp> info(@RequestBody @Valid CommonIdReq<String> request) {
        return Result.success(chapterService.info(request.getId()));
    }

    @ApiOperation("年级学科下拉列表")
    @PostMapping("/gradeSubjectDropdownList")
    public Result<List<GradeSubjectResp>> gradeSubjectDropdownList() {
        return Result.success(chapterService.gradeSubjectDropdownList());
    }
}
