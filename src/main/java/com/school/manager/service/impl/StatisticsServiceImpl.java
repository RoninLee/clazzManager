package com.school.manager.service.impl;

import com.school.manager.enums.StatusCode;
import com.school.manager.exception.SysServiceException;
import com.school.manager.jwt.LoginUserInfo;
import com.school.manager.jwt.LoginUserUtil;
import com.school.manager.pojo.dao.GroupDao;
import com.school.manager.pojo.dao.LessonPlanDao;
import com.school.manager.pojo.dto.resp.LessonStatisticsInfoResp;
import com.school.manager.pojo.dto.resp.LessonStatisticsResp;
import com.school.manager.pojo.entity.Group;
import com.school.manager.service.StatisticsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author lizelong
 */
@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Resource
    private GroupDao groupDao;

    @Resource
    private LessonPlanDao lessonPlanDao;


    /**
     * 课程统计模块
     *
     * @return 统计数据
     */
    @Override
    public LessonStatisticsResp lesson() {
        String userId = Optional.ofNullable(LoginUserUtil.getLoginUserInfo()).map(LoginUserInfo::getId).orElseThrow(() -> new SysServiceException(StatusCode.NO_LOGIN_INFO.getDesc()));
        Group group = Optional.ofNullable(groupDao.getByUserId(userId)).orElseThrow(() -> new SysServiceException(StatusCode.NO_BINDING_GROUP.getDesc()));
        Set<String> userIds = groupDao.getByLeaderId(userId);
        LessonStatisticsResp result = new LessonStatisticsResp();
        result.setGroupName(group.getName());
        if (userIds.isEmpty()) {
            return result;
        }
        List<LessonStatisticsInfoResp> lessons = lessonPlanDao.statistics(userIds);
        result.setStatisticsData(lessons);
        return result;
    }
}
