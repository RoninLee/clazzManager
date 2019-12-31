package com.school.manager.service;

import com.school.manager.dto.resp.FileResp;
import org.springframework.web.multipart.MultipartFile;


/**
 * @author RoninLee
 * @description 教案管理
 */
public interface LessonPlanService {

    /**
     * 上传文件
     *
     * @param file      文件
     * @param type      类型
     * @param chapterId 章节id
     * @return 文件信息
     */
    FileResp upload(MultipartFile file, String type, String chapterId);
}
