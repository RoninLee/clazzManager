package com.school.manager.service;

import com.school.manager.pojo.dto.resp.LessonStatisticsResp;

/**
 * @author lizelong
 */
public interface StatisticsService {

    /**
     * 课程统计模块
     *
     * @return 统计数据
     */
    LessonStatisticsResp lesson();
}
