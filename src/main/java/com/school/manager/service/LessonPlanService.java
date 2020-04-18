package com.school.manager.service;

import com.deepoove.poi.XWPFTemplate;
import com.school.manager.pojo.dto.common.BaseDTO;
import com.school.manager.pojo.dto.common.Result;
import com.school.manager.pojo.dto.req.LessonPlanListReq;
import com.school.manager.pojo.dto.req.LessonPlanSaveReq;
import com.school.manager.pojo.dto.req.LessonPlanUpdateReq;
import com.school.manager.pojo.dto.common.FileInfo;
import com.school.manager.pojo.dto.resp.LessonPlanInfoResp;
import com.school.manager.pojo.dto.resp.LessonPlanListResp;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


/**
 * @author RoninLee
 * @description 教案管理
 */
public interface LessonPlanService {


    /**
     * 查询当前用户所绑定的年级和学科
     *
     * @return 年级学科列表
     */
    List<BaseDTO<String>> gradeSubList();

    /**
     * 上传文件
     *
     * @param file 文件
     * @return 文件信息
     */
    FileInfo upload(MultipartFile file);

    /**
     * 保存教案
     *
     * @param request 请求对象
     * @return 教案id
     */
    String save(LessonPlanSaveReq request);

    /**
     * 更新教案
     *
     * @param request 请求对象
     * @return 教案id
     */
    String update(LessonPlanUpdateReq request);

    /**
     * 教案详情
     *
     * @param id 教案id
     * @return 教案详情
     */
    LessonPlanInfoResp info(String id);

    /**
     * 教案列表
     *
     * @param request 教案列表请求对象
     * @return 教案列表
     */
    Result<List<LessonPlanListResp>> list(LessonPlanListReq request);

    /**
     * 删除教案
     *
     * @param id 教案id
     */
    void delete(String id);

    /**
     * 导出教案
     *
     * @param id 教案id
     * @return
     */
    XWPFTemplate export(String id);

    /**
     * 附件下载
     *  @param lessonId 教案id
     * @param fileType 附件类型
     * @return
     */
    ResponseEntity<Object> download(String lessonId, Integer fileType);
}
