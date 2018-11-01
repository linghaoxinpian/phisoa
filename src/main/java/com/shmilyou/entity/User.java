package com.shmilyou.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Created with 岂止是一丝涟漪     530060499@qq.com    2018/8/14
 */

/**
 * 求学者
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
     * 相册,一个json串，如["1.jpg","2.jpg","3.jpg"]
     */
    private String photoAlbum;

    /**
     * 地区编码
     */
    private String areaCode;

    /**
     * 出生日期
     */
    private Date birthDay;

    private int gender;

    //----------------- 非DB字段 -----------------

    private List<Category> categoryList;

    /**
     * 擅长的文化
     */
    @Deprecated
    private List<UserTag> strongList;

    /**
     * 爱好的文化,用于论坛
     */
    @Deprecated
    private List<UserTag> interestList;

    //----------------- 附加属性 -----------------

    /** 解析后的海报名称 */
    private List<String> parsedPhotoAlbums;
}
