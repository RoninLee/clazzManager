package com.school.manager.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author RoninLee
 * @description 文件服务用配置
 */
@Component
@ConfigurationProperties(prefix = "system.file")
@Data
public class FileConfigConstant {
    public String fileUrl;
    public String filePath;
}
