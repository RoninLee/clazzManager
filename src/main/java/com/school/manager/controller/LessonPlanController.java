package com.school.manager.controller;

import com.school.manager.common.Result;
import com.school.manager.service.LessonPlanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
    public Result<Object> upload(@RequestParam(value = "file", required = true) MultipartFile file,
                                 @RequestParam(value = "type", required = true) String type,
                                 @RequestParam(value = "userId", required = true) Long userId,
                                 @RequestParam(value = "chapterId", required = true) Long chapterId) {
        return Result.success(lessonPlanService.upload(file, type, userId, chapterId));
    }
}
