package com.school.manager.utils;

import com.school.manager.common.constant.Constant;
import com.school.manager.common.resp.Result;
import com.school.manager.entity.FileConfigConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author RoninLee
 * @description 文件工具
 */
@Slf4j
public class FileUtil {
    public String upload(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return "文件为空";
            }
            // 获取文件名
            String fileName = file.getOriginalFilename();
            log.info("上传的文件名为：" + fileName);
            // 获取文件的后缀名
            assert fileName != null;
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            log.info("文件的后缀名为：" + suffixName);
            // 设置文件存储路径
            String filePath = "D://Downloads/";
            String path = filePath + fileName;
            File dest = new File(path);
            // 检测是否存在目录
            if (!dest.getParentFile().exists()) {
                // 新建文件夹
                boolean mkdirs = dest.getParentFile().mkdirs();
            }
            // 文件写入
            file.transferTo(dest);
            return "上传成功";
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }
        return "上传失败";
    }

    public String downloadFile(HttpServletRequest request, HttpServletResponse response) {
        String fileName = "2019092715213111122.png";// 文件名
        if (fileName != null) {
            //设置文件路径
            File file = new File("D://Downloads/2019092715213111122.png");
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
                byte[] buffer = new byte[1024];
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
                    return "下载成功";
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
        return "下载失败";
    }


    @Autowired
    private FileConfigConstant fileConfigConstant;

    public Result save(@RequestParam("file") MultipartFile file, @RequestParam String checkNum, @RequestParam String facilityTypeNum) {
        String fileName = "";
        if (!file.isEmpty()) {
            if (!file.getOriginalFilename().contains(Constant.POINT)) {
                return Result.error("上传失败:文件没有后缀名");
            }
            //文件名=设备类型-时间戳.后缀
            fileName = facilityTypeNum.toUpperCase() + "-" + System.currentTimeMillis() + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));
            String saveFileName = fileConfigConstant.filePath + checkNum + File.separator + fileName;
            System.out.println(saveFileName);
            File dest = new File(saveFileName);
            if (!dest.getParentFile().exists()) {
                //判断文件父目录是否存在
                dest.getParentFile().mkdirs();
            }
            try {
                file.transferTo(dest);
                //保存文件
            } catch (Exception e) {
                e.printStackTrace();
                return Result.error("上传失败:" + e.getMessage());
            }
        } else {
            return Result.error("上传失败:文件不存在");
        }
        Map<String, Object> returnMap = new HashMap<>(4);
        //返回的时候把各种属性都返回即可
        returnMap.put("imageUrl", fileConfigConstant.getFileUrl() + checkNum + "/" + fileName);
        returnMap.put("fileName", "/" + checkNum + "/" + fileName);
        returnMap.put("checkNum", checkNum);
        returnMap.put("facilityTypeNum", facilityTypeNum);
        return Result.success(returnMap);
    }


}
