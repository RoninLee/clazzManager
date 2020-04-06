package com.school.manager.controller;

import com.school.manager.pojo.dto.common.BaseDTO;
import com.school.manager.pojo.dto.common.CommonSelOrDelReq;
import com.school.manager.pojo.dto.common.FileInfo;
import com.school.manager.pojo.dto.common.Result;
import com.school.manager.pojo.dto.req.LessonPlanListReq;
import com.school.manager.pojo.dto.req.LessonPlanSaveReq;
import com.school.manager.pojo.dto.req.LessonPlanUpdateReq;
import com.school.manager.pojo.dto.resp.LessonPlanInfoResp;
import com.school.manager.pojo.dto.resp.LessonPlanListResp;
import com.school.manager.service.LessonPlanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

/**
 * @author RoninLee
 * @description 教案管理
 */
@Api(tags = "教案管理")
@RestController
@CrossOrigin
@RequestMapping("/lessonPlan/")
public class LessonPlanController {

    @Autowired
    private LessonPlanService lessonPlanService;

    @ApiOperation("文件上传")
    @PostMapping("upload")
    public Result<FileInfo> upload(@RequestParam(value = "file") MultipartFile file) {
        return Result.success(lessonPlanService.upload(file));
    }

    @ApiOperation("当前用户所绑定的年级学科列表")
    @PostMapping("gradeSubList")
    public Result<List<BaseDTO<String>>> gradeSubList() {
        return Result.success(lessonPlanService.gradeSubList());
    }

    @ApiOperation("增加教案")
    @PostMapping("save")
    public Result<String> save(@RequestBody @Valid LessonPlanSaveReq request) {
        return Result.success(lessonPlanService.save(request));
    }

    @ApiOperation("修改教案")
    @PostMapping("update")
    public Result<String> update(@RequestBody @Valid LessonPlanUpdateReq request) {
        return Result.success(lessonPlanService.update(request));
    }

    @ApiOperation("教案详情")
    @PostMapping("info")
    public Result<LessonPlanInfoResp> info(@RequestBody @Valid CommonSelOrDelReq<String> request) {
        return Result.success(lessonPlanService.info(request.getId()));
    }

    @ApiOperation("教案列表")
    @PostMapping("list")
    public Result<List<LessonPlanListResp>> list(@RequestBody @Valid LessonPlanListReq request) {
        return lessonPlanService.list(request);
    }

    @ApiOperation("删除教案")
    @PostMapping("delete")
    public Result<Void> delete(@RequestBody @Valid CommonSelOrDelReq<String> request) {
        lessonPlanService.delete(request.getId());
        return Result.success();
    }

    @ApiOperation("导出教案")
    @GetMapping("export/{id}")
    public void export(@ApiParam(name = "教案ID", required = true) @PathVariable String id) {
        lessonPlanService.export(id);
    }

    @ApiOperation("下载附件")
    @GetMapping("download/{lessonId}/{fileType}")
    public void download(@ApiParam(name = "教案ID", required = true) @PathVariable String lessonId,
                         @ApiParam(name = "附件类型", value = "ppt:1 exercises:2", required = true) @PathVariable Integer fileType) {
        lessonPlanService.download(lessonId, fileType);
    }
}
