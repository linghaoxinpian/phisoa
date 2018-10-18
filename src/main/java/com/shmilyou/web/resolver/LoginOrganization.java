package com.shmilyou.web.resolver;

import lombok.Data;

/**
 * Created with 岂止是一丝涟漪     530060499@qq.com    2018/10/18
 */

/**
 * 机构登录信息
 */
@Data
public class LoginOrganization {

    private String id;

    /**
     * 登录账户名
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

//    private OrganizationLevel level;
//
//    private List<Lecturer> lecturerList;
//
//    /**
//     * 该教育机构课程列表
//     */
//    private List<Course> courseList;

    public static LoginOrganization getDefault() {
        LoginOrganization loginOrganization = new LoginOrganization();
        loginOrganization.setId("2c2dae92-a05c-11e8-be4c-c60adc336b7d");
        loginOrganization.setName("admin-organization");
        return loginOrganization;
    }
}
