package com.shmilyou.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Created with 岂止是一丝涟漪
 * Date: 2018/8/14
 */

/**
 * 用户
 */
@Data
public class User extends BaseEntity {

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
     * 出生日期
     */
    private Date birthDay;

    private Integer gender;

    //非DB字段

    /**
     * 擅长的文化
     */
    private List<UserTag> strongList;

    /**
     * 爱好的文化,用于论坛
     */
    private List<UserTag> interestList;
}
