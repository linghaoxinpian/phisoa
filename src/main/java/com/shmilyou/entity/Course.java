package com.shmilyou.entity;

import lombok.Data;

/**
 * Created with 530060499@qq.com
 * Date: 2018/8/16
 */

/**
 * 课程
 */
@Data
public class Course extends BaseEntity {

    private String id;

    private String name;

    private String description;

    /**
     * 缩略图
     */
    private String thumbnail;

    /**
     * 优化查询，0为机构，1,为个人
     */
    private Integer ownerCategory;

    /**
     * 所属机构(或个人)Id
     */
    private String ownerId;

    /**
     * 课程所属分类Id
     */
    private String categoryId;

}
