package com.shmilyou.web.resolver;

import lombok.Data;

import java.util.Date;

/**
 * Created with 岂止是一丝涟漪     530060499@qq.com    2018/10/18
 */

/**
 * 用户登录信息
 */
@Data
public class LoginUser {

    private String id;

    private String name;

    private String nickname;

    private String password;

    private String headImg;

    private String phone;

    private String email;

    /**
     * 居住地址
     */
    private String place;

    /**
     * 所在地Id
     */
    private Integer areaId;

    /**
     * 出生日期
     */
    private Date birthDay;

    private Integer gender;

    //----------------- 非DB字段 -----------------

//    /**
//     * 擅长的文化
//     */
//    private List<UserTag> strongList;
//
//    /**
//     * 爱好的文化,用于论坛
//     */
//    private List<UserTag> interestList;

    public static LoginUser getDefault() {
        LoginUser loginUser = new LoginUser();
        loginUser.setId("2c2dae92-a05c-11e8-be4c-c60adc336b7d");
        loginUser.setName("admin");
        return loginUser;
    }
}
