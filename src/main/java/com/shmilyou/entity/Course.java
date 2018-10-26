package com.shmilyou.entity;

import lombok.Data;

import java.util.Date;

/**
 * Created with 530060499@qq.com
 * Date: 2018/8/16
 */

/**
 * 机构的课程
 */
@Data
public class Course extends BaseEntity {

    private String id;

    private String name;

    private String description;

    private String picUrl;

    private int originalPrice;

    private int price;

    private String ownerId;

    /**
     * 课程所属分类Id
     */
    private String categoryId;

    private Date addTime;

}
