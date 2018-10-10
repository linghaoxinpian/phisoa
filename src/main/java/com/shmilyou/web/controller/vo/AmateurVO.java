package com.shmilyou.web.controller.vo;

import lombok.Data;

import java.util.List;

/**
 * Created with 岂止是一丝涟漪     530060499@qq.com    2018/10/10
 */
@Data
public class AmateurVO {

    private String id;

    private String name;

    /**
     * 登录账户名，可用手机号或邮箱登录
     */
    private String loginName;

    private String password;

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

    //----------------- 非DB字段 -----------------

    /**
     * 所选标签的字符串集合
     */
    private List<String> tagIds;

    /**
     * 地区全称
     */
    private String fullAreaName;
}
