package com.shmilyou.web.controller.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Created with 岂止是一丝涟漪     530060499@qq.com    2018/10/10
 */
@Data
public class UserVO {

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
