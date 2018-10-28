package com.shmilyou.entity;

/**
 * Created with 岂止是一丝涟漪
 * Date: 2018/8/16
 */

import lombok.Data;

/**
 * 每个机构的[著名讲师]
 */
@Data
public class Lecturer extends BaseEntity {

    private String id;

    private String name;

    /**
     * 自拍照
     */
    private String selfie;

    /** 简历 */
    private String resume;

    /** 教龄，资历 */
    private Integer seniority;

    /** 技能，如：舞蹈|钢琴 */
    private String skill;

    /** 作品集(json) */
    private String showreel;

    private String organizationId;

    //----------------- 非DB字段 -----------------

    private Organization organization;
}
