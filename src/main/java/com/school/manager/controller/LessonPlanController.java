package com.school.manager.controller;

import com.deepoove.poi.XWPFTemplate;
import com.school.manager.enums.StatusCode;
import com.school.manager.exception.SysServiceException;
import com.school.manager.pojo.dto.common.*;
import com.school.manager.pojo.dto.req.AttachmentDownloadReq;
import com.school.manager.pojo.dto.req.LessonPlanListReq;
import com.school.manager.pojo.dto.req.LessonPlanSaveReq;
import com.school.manager.pojo.dto.req.LessonPlanUpdateReq;
import com.school.manager.pojo.dto.resp.LessonPlanInfoResp;
import com.school.manager.pojo.dto.resp.LessonPlanListResp;
import com.school.manager.service.LessonPlanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author RoninLee
 * @description 教案管理
 */
@Api(tags = "教案管理")
@RestController
@CrossOrigin
@RequestMapping("/lessonPlan/")
public class LessonPlanController {

    @Autowired
    private LessonPlanService lessonPlanService;

    @ApiOperation("文件上传")
    @PostMapping("upload")
    public Result<FileInfo> upload(@RequestParam(value = "file") MultipartFile file) {
        return Result.success(lessonPlanService.upload(file));
    }

    @ApiOperation("当前用户所绑定的年级学科列表")
    @PostMapping("gradeSubList")
    public Result<List<BaseDTO<String>>> gradeSubList() {
        return Result.success(lessonPlanService.gradeSubList());
    }

    @ApiOperation("增加教案")
    @PostMapping("save")
    public Result<String> save(@RequestBody @Valid LessonPlanSaveReq request) {
        return Result.success(lessonPlanService.save(request));
    }

    @ApiOperation("修改教案")
    @PostMapping("update")
    public Result<String> update(@RequestBody @Valid LessonPlanUpdateReq request) {
        return Result.success(lessonPlanService.update(request));
    }

    @ApiOperation("教案详情")
    @PostMapping("info")
    public Result<LessonPlanInfoResp> info(@RequestBody @Valid CommonIdReq<String> request) {
        return Result.success(lessonPlanService.info(request.getId()));
    }

    @ApiOperation("教案列表")
    @PostMapping("list")
    public Result<List<LessonPlanListResp>> list(@RequestBody @Valid LessonPlanListReq request) {
        return lessonPlanService.list(request);
    }

    @ApiOperation("删除教案")
    @PostMapping("delete")
    public Result<Void> delete(@RequestBody @Valid CommonDelReq<String> request) {
        lessonPlanService.delete(request.getId(), request.getVersion());
        return Result.success();
    }

    @ApiOperation("导出教案")
    @PostMapping("export")
    public ResponseEntity<byte[]> export(@RequestBody @Valid CommonIdReq<String> request) {
        XWPFTemplate template = lessonPlanService.export(request.getId());
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            template.write(bos);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentDispositionFormData("attachment", System.currentTimeMillis() + ".docx");
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            return new ResponseEntity<>(bos.toByteArray(), headers, HttpStatus.OK);
        } catch (IOException e) {
            throw new SysServiceException(StatusCode.ERROR.getCode(), e.getMessage());
        } finally {
            try {
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (template != null) {
                try {
                    template.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @ApiOperation("下载附件")
    @PostMapping("download")
    public ResponseEntity<Object> download(@RequestBody @Valid AttachmentDownloadReq request) {
        return lessonPlanService.download(request.getId(), request.getFileType());
    }
}
