package com.shmilyou.web.controller.vo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedList;

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

    /** 作品集,相册(json) */
    private LinkedList<MultipartFile> showreel;

    /** 经验 */
    private String experience;

    private String organizationId;

}
