package com.school.manager.service.impl;

import com.school.manager.common.constant.Constant;
import com.school.manager.pojo.dto.resp.FileResp;
import com.school.manager.common.FileConfigConstant;
import com.school.manager.jwt.LoginUserInfo;
import com.school.manager.enums.StatusCode;
import com.school.manager.exception.SysServiceException;
import com.school.manager.service.LessonPlanService;
import com.school.manager.jwt.LoginUserUtil;
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
import java.util.Optional;


/**
 * @author RoninLee
 * @description 教案管理
 */
@Slf4j
@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
public class LessonPlanServiceImpl implements LessonPlanService {


    @Autowired
    private FileConfigConstant fileConfigConstant;

    /**
     * 上传文件
     *
     * @param file      文件
     * @param type      类型
     * @param chapterId 章节id
     * @return 文件信息
     */
    @Override
    public FileResp upload(MultipartFile file, String type, String chapterId) {
        // 获取登录人信息
        LoginUserInfo loginUserInfo = Optional.ofNullable(LoginUserUtil.getLoginUserInfo()).orElseThrow(() -> new SysServiceException(StatusCode.NO_LOGIN_INFO.getCode(), StatusCode.NO_LOGIN_INFO.getDesc()));
        // 校验参数
        if (file.isEmpty() || StringUtils.isBlank(type) || StringUtils.isBlank(loginUserInfo.getId()) || StringUtils.isBlank(chapterId)) {
            throw new SysServiceException(StatusCode.REQUEST_IS_NULL.getDesc());
        }
        String filename = file.getOriginalFilename();
        if (filename != null && !filename.contains(Constant.POINT)) {
            throw new SysServiceException(StatusCode.FILE_NO_SUFFIX.getDesc());
        }
        // 拼保存后的文件名
        String newFileName = fileConfigConstant.filePath + Constant.FORWARD_SLASH + type.toUpperCase() + Constant.FORWARD_SLASH + loginUserInfo.getId() + Constant.DASH + chapterId + Constant.DASH + filename;
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
            throw new SysServiceException(StatusCode.FILE_UPLOAD_FAILURE.getDesc());
        }
        FileResp fileResp = new FileResp();
        fileResp.setFileName(filename);
        fileResp.setFileUrl(newFileName);
        return fileResp;
    }
}
