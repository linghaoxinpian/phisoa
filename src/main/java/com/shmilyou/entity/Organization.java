package com.shmilyou.entity;

import lombok.Data;

import java.util.List;

/**
 * Created with 岂止是一丝涟漪
 * Date: 2018/8/14
 */

/**
 * 机构
 */
@Data
public class Organization extends BaseEntity {

    private String id;

    /**
     * 登录账户名，可用手机号或邮箱登录
     */
    private String loginName;

    private String password;

    /** 登录手机号 */
    private String loginPhone;

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
     * 相册,一个json串，如["1.jpg","2.jpg","3.jpg"]
     */
    private String photoAlbum;

    /**
     * 招生简章（扩展：存的可能为富文本）
     */
    private String studentGuide;

    /** 前台资讯电话，可多个 */
    private String phone;

    /**
     * 位置(自描述，查询时用like直接匹配)
     */
    private String place;

    /**
     * 培训人数
     */
    private Integer student;

    /** 海报 */
    private String posters;

    /**
     * 机构官方主页
     */
    private String homeUrl;

    /**
     * 地区编码
     */
    private String areaCode;

    /**
     * 机构在我方的评级
     */
    private String levelId;

    //----------------- 非DB字段 -----------------

    private OrganizationOverview overview;

    private OrganizationLevel level;

    private List<Category> categoryList;

    private List<Lecturer> lecturerList;

    /**
     * 该教育机构课程列表
     */
    private List<Course> courseList;

    //----------------- 附加属性 -----------------

    /** 解析后的海报名称 */
    private List<String> parsedPosters;

    /** 解析后的海报名称 */
    private List<String> parsedPhotoAlbums;

}
