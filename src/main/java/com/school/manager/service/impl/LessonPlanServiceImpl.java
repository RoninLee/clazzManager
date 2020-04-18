package com.school.manager.service.impl;

import com.deepoove.poi.XWPFTemplate;
import com.school.manager.common.FileConfigConstant;
import com.school.manager.common.constant.Constant;
import com.school.manager.common.constant.LongConstant;
import com.school.manager.enums.StatusCode;
import com.school.manager.exception.SysServiceException;
import com.school.manager.jwt.LoginUserInfo;
import com.school.manager.jwt.LoginUserUtil;
import com.school.manager.pojo.dao.LessonPlanDao;
import com.school.manager.pojo.dto.common.BaseDTO;
import com.school.manager.pojo.dto.common.FileInfo;
import com.school.manager.pojo.dto.common.PageResult;
import com.school.manager.pojo.dto.common.Result;
import com.school.manager.pojo.dto.req.LessonPlanListReq;
import com.school.manager.pojo.dto.req.LessonPlanSaveReq;
import com.school.manager.pojo.dto.req.LessonPlanUpdateReq;
import com.school.manager.pojo.dto.resp.LessonPlanInfoResp;
import com.school.manager.pojo.dto.resp.LessonPlanListResp;
import com.school.manager.pojo.entity.LessonPlan;
import com.school.manager.service.LessonPlanService;
import com.school.manager.utils.BeanMapper;
import com.school.manager.utils.ExportHtml2Word;
import com.school.manager.utils.IdWorker;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author RoninLee
 * @description 教案管理
 */
@Slf4j
@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
public class LessonPlanServiceImpl implements LessonPlanService {

    @Resource
    private LessonPlanDao lessonPlanDao;

    @Autowired
    private FileConfigConstant fileConfigConstant;

    @Resource
    private IdWorker idWorker;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private ExportHtml2Word exportHtml2Word;

    /**
     * 查询当前用户所绑定的年级和学科
     *
     * @return 年级学科列表
     */
    @Override
    public List<BaseDTO<String>> gradeSubList() {
        LoginUserInfo loginUserInfo = Optional.ofNullable(LoginUserUtil.getLoginUserInfo()).orElseThrow(() -> new SysServiceException(StatusCode.NO_LOGIN_INFO.getDesc()));
        return lessonPlanDao.gradeSubList(loginUserInfo.getId());

    }

    /**
     * 上传文件
     *
     * @param file 文件
     * @return 文件信息
     */
    @Override
    public FileInfo upload(MultipartFile file) {
        // 获取登录人信息
        LoginUserInfo loginUserInfo = Optional.ofNullable(LoginUserUtil.getLoginUserInfo()).orElseThrow(() -> new SysServiceException(StatusCode.NO_LOGIN_INFO.getCode(), StatusCode.NO_LOGIN_INFO.getDesc()));
        // 校验参数
        if (file.isEmpty() || StringUtils.isBlank(loginUserInfo.getId())) {
            throw new SysServiceException(StatusCode.REQUEST_IS_NULL.getDesc());
        }
        String filename = file.getOriginalFilename();
        if (filename != null && !filename.contains(Constant.POINT)) {
            throw new SysServiceException(StatusCode.FILE_NO_SUFFIX.getDesc());
        }
        // 拼保存后的文件名
        String newFileName = fileConfigConstant.filePath + Constant.FORWARD_SLASH + loginUserInfo.getId() + Constant.FORWARD_SLASH + filename;
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
        FileInfo fileInfo = new FileInfo();
        fileInfo.setFileName(filename);
        fileInfo.setFileUrl(newFileName);
        return fileInfo;
    }

    /**
     * 保存教案
     *
     * @param request 请求对象
     * @return 教案id
     */
    @Override
    public String save(LessonPlanSaveReq request) {
        LessonPlan lessonPlan = BeanMapper.def().map(request, LessonPlan.class);
        lessonPlan.setId(String.valueOf(idWorker.nextId()));
        LoginUserInfo loginUserInfo = LoginUserUtil.getLoginUserInfo();
        lessonPlan.setUserId(loginUserInfo.getId());
        lessonPlan.setCreateTime(new Date());
        lessonPlan.setVersion(LongConstant.ONE);
        lessonPlanDao.save(lessonPlan);
        return lessonPlan.getId();
    }

    /**
     * 更新教案
     *
     * @param request 请求对象
     * @return 教案id
     */
    @Override
    public String update(LessonPlanUpdateReq request) {
        LessonPlan lessonPlan = BeanMapper.def().map(request, LessonPlan.class);
        lessonPlan.setVersion(lessonPlan.getVersion() + LongConstant.ONE);
        lessonPlanDao.update(lessonPlan);
        return lessonPlan.getId();
    }

    /**
     * 教案详情
     *
     * @param id 教案id
     * @return 教案详情
     */
    @Override
    public LessonPlanInfoResp info(String id) {
        LessonPlan lessonPlan = lessonPlanDao.info(id);
        return BeanMapper.def().map(lessonPlan, LessonPlanInfoResp.class);
    }

    /**
     * 教案列表
     *
     * @param request 教案列表请求对象
     * @return 教案列表
     */
    @Override
    public Result<List<LessonPlanListResp>> list(LessonPlanListReq request) {
        Integer pageSize = request.getPageSize();
        int pageIndex = (request.getPageIndex() - 1) * pageSize;
        List<Date> createTime = request.getCreateTime();
        Date startDate = null, endDate = null;
        if (null != createTime && !createTime.isEmpty()) {
            startDate = createTime.get(0);
            endDate = createTime.get(1);
        }
        List<LessonPlanListResp> lessonPlanList = lessonPlanDao.pageList(request.getRelationId(), startDate, endDate, pageIndex, pageSize);
        Long pageListCount = lessonPlanDao.pageListCount(request.getRelationId(), startDate, endDate);
        PageResult<List<LessonPlanListResp>> pageResult = new PageResult<>();
        pageResult.setData(lessonPlanList);
        pageResult.setTotal(pageListCount);
        return pageResult;
    }


    /**
     * 删除教案
     *
     * @param id 教案id
     */
    @Override
    public void delete(String id) {
        lessonPlanDao.delete(id);
    }

    /**
     * 导出教案
     *
     * @param id 教案id
     * @return word流
     */
    @Override
    public XWPFTemplate export(String id) {
        LessonPlan lessonPlan = Optional.ofNullable(lessonPlanDao.info(id)).orElseThrow(() -> new SysServiceException(StatusCode.DATA_NOT_EXIST.getDesc()));
        return exportHtml2Word.export(lessonPlan.getLessonPlanText());
    }

    /**
     * 附件下载
     *
     * @param lessonId 教案id
     * @param fileType 附件类型
     * @return 文件流
     */
    @Override
    public ResponseEntity<Object> download(String lessonId, Integer fileType) {
        // 查询教案信息
        LessonPlan lessonPlan = Optional.ofNullable(lessonPlanDao.info(lessonId)).orElseThrow(() -> new SysServiceException(StatusCode.DATA_NOT_EXIST.getDesc()));
        String filePath = null;
        String fileName = null;
        // 根据附件下载的类型获取文件地址
        switch (fileType) {
            case 1:
                // ppt
                filePath = lessonPlan.getPptUrl();
                fileName = lessonPlan.getPptName();
                break;
            case 2:
                // exercise
                filePath = lessonPlan.getExercisesUrl();
                fileName = lessonPlan.getExercisesName();
                break;
            default:
                break;
        }
        // 文件地址不为空
        if (StringUtils.isNotBlank(filePath)) {
            // 文件对象
            File file = new File(filePath);
            if (file.exists()) {
                InputStreamResource resource = null;
                try {
                    resource = new InputStreamResource(new FileInputStream(file));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                HttpHeaders headers = new HttpHeaders();
                headers.add("Content-Disposition", "attachment;filename=" + fileName);
                headers.add("Cache-Control", "no-cache,no-store,must-revalidate");
                headers.add("Pragma", "no-cache");
                headers.add("Expires", "0");
                ResponseEntity<Object> responseEntity = ResponseEntity.ok()
                        .headers(headers)
                        .contentLength(file.length())
                        .contentType(MediaType.parseMediaType("application/octet-stream"))
                        .body(resource);

                return responseEntity;
            }

        }
        throw new SysServiceException(StatusCode.ERROR.getCode(), "文件不存在");
    }

}
