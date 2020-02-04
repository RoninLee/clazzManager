package com.school.manager.controller;

import com.school.manager.pojo.dto.common.BaseDTO;
import com.school.manager.pojo.dto.common.CommonSelOrDelReq;
import com.school.manager.pojo.dto.common.Result;
import com.school.manager.pojo.dto.req.LessonPlanListReq;
import com.school.manager.pojo.dto.req.LessonPlanSaveReq;
import com.school.manager.pojo.dto.req.LessonPlanUpdateReq;
import com.school.manager.pojo.dto.resp.LessonPlanInfoResp;
import com.school.manager.pojo.dto.resp.LessonPlanListResp;
import com.school.manager.service.LessonPlanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
    public Result<Object> upload(@RequestParam(value = "file") MultipartFile file,
                                 @RequestParam(value = "type") String type,
                                 @RequestParam(value = "chapterId") String chapterId) {
        return Result.success(lessonPlanService.upload(file, type, chapterId));
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
}
