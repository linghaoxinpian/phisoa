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
     * 分类
     */
    private String categoryId;

    /**
     * 0：特长，1：爱好
     */
    private Integer classify;
}
