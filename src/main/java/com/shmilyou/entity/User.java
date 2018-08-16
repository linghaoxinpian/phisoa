package com.shmilyou.entity;

import lombok.Data;

import java.util.Date;

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

    /**
     * 擅长的文化
     */
    private String strong;

    /**
     * 爱好的文化
     */
    private String interest;
}
