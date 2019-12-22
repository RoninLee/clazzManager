package com.school.manager.dto.req;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author RoninLee
 * @description 教案请求对象
 */
@Data
public class LessonPlanReq implements Serializable {
    private static final long serialVersionUID = 8359904526770297586L;
    @Id
    private Long id;
    /**
     * 章节id
     */
    private Long chapterId;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 在线文档内容
     */
    private String lessonPlanText;
    /**
     * ppt文件名
     */
    private String pptName;
    /**
     * ppt附件地址
     */
    private String pptUrl;
    /**
     * ppt存储的文件名
     */
    private String pptFileName;
    /**
     * 练习题文件名
     */
    private String exercisesName;
    /**
     * 练习题教案地址
     */
    private String exercisesUrl;
    /**
     * 练习题存储的文件名字
     */
    private String exercisesFileName;

    private MultipartFile file;
}
