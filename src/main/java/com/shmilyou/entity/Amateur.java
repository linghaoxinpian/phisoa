package com.shmilyou.entity;

import lombok.Data;

/**
 * Created with 530060499@qq.com
 * Date: 2018/8/16
 */

/**
 * 个人机构，爱好者
 */
@Data
public class Amateur extends BaseEntity {

    private String id;

    private String name;

    private String headImg;

    /**
     * 个人简历
     */
    private String resume;

    /**
     * 优势，特长
     */
    private String strong;

    /**
     * 作品
     */
    private String works;

    private String phone;

    /**
     * 现在所在地自描述
     */
    private String place;

    /**
     * 所在地Id
     */
    private Integer areaId;

    /**
     * 教龄，资历
     */
    private Integer seniority;

    /**
     * 爱好者等级
     */
    private Integer levelId;
}
