package com.shmilyou.web.controller.vo;

import lombok.Data;

import java.util.List;

/**
 * Created with 岂止是一丝涟漪     530060499@qq.com    2018/10/5
 */
@Data
public class OrganizationVO {

    /**
     * 登录账户名，可用手机号或邮箱登录
     */
    private String loginName;

    private String password;

    /**
     * 机构名
     */
    private String name;

    /**
     * logo
     */
    private String logo;

    /**
     * 简述
     */
    private String description;

    /**
     * 场地照片,一个json串，如["1.jpg","2.jpg","3.jpg"]
     */
    private String sitePhoto;

    /**
     * 招生简章（扩展：存的可能为富文本）
     */
    private String studentGuide;

    /**
     * 咨询电话
     */
    private String phone;

    /**
     * 位置(自描述，查询时用like直接匹配)
     */
    private String place;

    /**
     * 位置Id
     */
    private Integer areaId;

    /**
     * 机构官方主页
     */
    private String homeUrl;

    /**
     * 机构在我方的评级
     */
    //private String levelId;

    /**
     * 培训人数
     */
    private Integer trainers;

    //----------------- 非DB字段 -----------------

    /**
     * 地区全称
     */
    private String fullAreaName;

    /**
     * 所选标签的字符串集合
     */
    private List<String> tagIds;
}
