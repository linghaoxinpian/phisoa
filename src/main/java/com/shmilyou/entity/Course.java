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

    /**
     * 所属机构Id
     */
    private String ownerId;

}
