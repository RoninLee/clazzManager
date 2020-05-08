package com.school.manager.controller;

import com.school.manager.pojo.dto.common.Result;
import com.school.manager.pojo.dto.resp.LessonStatisticsResp;
import com.school.manager.service.StatisticsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lizelong
 */
@Api(tags = "统计模块")
@RestController
@RequestMapping("/statistics/")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @ApiOperation("备课统计")
    @PostMapping("lesson")
    public Result<LessonStatisticsResp> lesson() {
        return Result.success(statisticsService.lesson());
    }

}
