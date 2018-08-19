package com.shmilyou.entity;

import lombok.Data;

/**
 * Created with 岂止是一丝涟漪
 * Date: 2018/8/14
 */

/**
 * 分类(别名：Tag标签)
 */
@Data
public class Category extends BaseEntity {

    private String id;

    private String name;

    /**
     * ?级分类,默认为1级
     */
    private Integer level;
}
