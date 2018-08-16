package com.shmilyou.entity;

import lombok.Data;

/**
 * Created with 岂止是一丝涟漪
 * Date: 2018/8/16
 */

/**
 * 机构等级
 */
@Data
public class OrganizationLevel extends BaseEntity {

    private String id;

    private String name;

    /**
     * 评级，默认为0，即国家合法教育机构。0以上为我方认证评级
     */
    private Integer level;
}
