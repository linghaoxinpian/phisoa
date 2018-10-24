package com.shmilyou.web.controller.vo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created with 岂止是一丝涟漪     530060499@qq.com    2018/10/24
 */
@Data
public class LecturerVO {

    private String id;

    private String name;

    /**
     * 自拍照
     */
    private MultipartFile selfie;

    /**
     * 简历
     */
    private String resume;

    /**
     * 教龄，资历
     */
    private Integer seniority;

    /**
     * 技能，如：舞蹈|钢琴
     */
    private String skill;

    private String organizationId;

}
