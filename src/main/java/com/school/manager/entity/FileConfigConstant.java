package com.school.manager.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author RoninLee
 * @description
 */
@Component
@ConfigurationProperties(prefix = "system")
@Data
public class FileConfigConstant {
    public String fileUrl;
    public String filePath;
}
