package com.school.manager.pojo.dto.resp;

import lombok.Data;

import java.io.Serializable;

/**
 * @author RoninLee
 * @description
 */
@Data
public class FileResp implements Serializable {
    private static final long serialVersionUID = -2666376210493147617L;
    private String fileName;
    private String fileUrl;
}
