package com.shmilyou.entity;

/**
 * Created with 530060499@qq.com
 * Date: 2018/8/16
 */

import lombok.Data;

/**
 * 用户感兴趣的标签
 */
@Data
public class UserTag extends BaseEntity {

    private String id;

    /**
     * 求学者id
     */
    private String userId;

    /**
     * 分类Id
     */
    private String categoryId;

    /**
     * STRONG_TAG：特长，INTEREST_TAG：爱好
     */
    private Integer classify;

    //非DB字段

    //特长标识符
    public static final Integer STRONG_TAG = 0;

    //爱好标识符
    public static final Integer INTEREST_TAG = 1;
}
