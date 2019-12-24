package com.school.manager.service;

import com.school.manager.common.Constant;
import com.school.manager.dto.resp.FileResp;
import com.school.manager.entity.FileConfigConstant;
import com.school.manager.enums.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


/**
 * @author RoninLee
 * @description 教案管理
 */
@Slf4j
@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
public class LessonPlanService {


    @Autowired
    private FileConfigConstant fileConfigConstant;

    /**
     * 上传文件
     *
     * @param file      文件
     * @param type      类型
     * @param userId    用户id
     * @param chapterId 章节id
     * @return 文件信息
     */
    public FileResp upload(MultipartFile file, String type, String userId, String chapterId) {
        if (file.isEmpty() || StringUtils.isBlank(type) || StringUtils.isBlank(userId) || StringUtils.isBlank(chapterId)) {
            throw new RuntimeException(StatusCode.REQUEST_IS_NULL.getDesc());
        }
        String filename = file.getOriginalFilename();
        if (filename != null && !filename.contains(Constant.POINT)) {
            throw new RuntimeException(StatusCode.FILE_NO_SUFFIX.getDesc());
        }
        // 拼保存后的文件名
        String newFileName = fileConfigConstant.filePath + Constant.FORWARD_SLASH + type.toUpperCase() + Constant.FORWARD_SLASH + userId + Constant.DASH + chapterId + Constant.DASH + filename;
        log.info("新文件名：{}", newFileName);
        File newFile = new File(newFileName);
        if (!newFile.getParentFile().exists()) {
            newFile.getParentFile().mkdirs();
        }
        try {
            // 上传文件
            file.transferTo(newFile);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(StatusCode.FILE_UPLOAD_FAILURE.getDesc());
        }
        FileResp fileResp = new FileResp();
        fileResp.setFileName(filename);
        fileResp.setFileUrl(newFileName);
        return fileResp;
    }
}
