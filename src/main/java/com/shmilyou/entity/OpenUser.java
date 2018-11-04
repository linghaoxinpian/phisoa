package com.shmilyou.entity;

/* Created with 岂止是一丝涟漪     530060499@qq.com    2018/11/4 */

import lombok.Data;

import java.util.Date;

/** 第三方登录表 */
@Data
public class OpenUser extends BaseEntity {

    private String id;

    private String openType;

    private String openId;

    private String accessToken;

    private Date expiredTime;

    private String nickname;

    /** 头像url,如:http://qzapp.qlogo.cn/qzapp/101515171/4DEDB4FC73D8CF4F855BFC6375647E80/30 */
    private String avatar;

    private String userId;

}
