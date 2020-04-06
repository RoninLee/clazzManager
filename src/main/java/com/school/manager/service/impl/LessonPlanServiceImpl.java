package com.school.manager.service.impl;

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
import com.school.manager.utils.IdWorker;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DocumentEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
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
     */
    @Override
    public void export(String id) {
        LessonPlan lessonPlan = Optional.ofNullable(lessonPlanDao.info(id)).orElseThrow(() -> new SysServiceException(StatusCode.DATA_NOT_EXIST.getDesc()));

        try {
            String text = lessonPlan.getLessonPlanText();
            //word内容
            String content = "<html><body>" + text + "</body></html>";
            //这里是必须要设置编码的，不然导出中文就会乱码。
            byte[] b = content.getBytes("GBK");
            //将字节数组包装到流中
            ByteArrayInputStream bais = new ByteArrayInputStream(b);
            //关键地方 生成word格式
            POIFSFileSystem poifs = new POIFSFileSystem();
            DirectoryEntry directory = poifs.getRoot();
            DocumentEntry documentEntry = directory.createDocument("WordDocument", bais);
            // 项目对接人要求 教案+时间戳 命名
            String fileName = "教案" + System.currentTimeMillis() + ".doc";
            String newFileName = encodingFileName(fileName);
            //输出文件 导出word格式
            response.setContentType("application/msword");
            response.addHeader("Content-Disposition", "attachment;filename= " + newFileName);
            ServletOutputStream ostream = response.getOutputStream();
            poifs.writeFilesystem(ostream);
            bais.close();
            ostream.close();
            poifs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 附件下载
     *
     * @param lessonId 教案id
     * @param fileType 附件类型
     */
    @Override
    public void download(String lessonId, Integer fileType) {
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
                // 设置强制下载不打开
                response.setContentType("application/force-download");
                // 文件名
                String newFileName = encodingFileName(fileName);
                response.addHeader("Content-Disposition", "attachment;fileName=" + newFileName);
                byte[] buffer = new byte[(int) file.length()];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    private String encodingFileName(String fileName) {
        String userAgent = request.getHeader("USER-AGENT");
        String newFileName = null;
        if (userAgent.contains("Firefox")) {
            //是火狐浏览器，使用BASE64编码
            try {
                newFileName = "=?utf-8?b?" + new BASE64Encoder().encode(fileName.getBytes("utf-8")) + "?=";
            } catch (UnsupportedEncodingException e) {
                log.error("fileName格式BASE64转换异常：{}", e.getMessage());
            }
        } else {
            //给文件名进行URL编码
            //URLEncoder.encode()需要两个参数，第一个参数时要编码的字符串，第二个是编码所采用的字符集
            try {
                newFileName = URLEncoder.encode(fileName, "utf-8");
            } catch (UnsupportedEncodingException e) {
                log.error("fileName格式UTF-8转换异常：{}", e.getMessage());
            }
        }
        return newFileName;
    }
}
