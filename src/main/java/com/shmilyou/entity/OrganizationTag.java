package com.shmilyou.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created with 岂止是一丝涟漪     530060499@qq.com    2018/8/19
 */

/**
 * 机构所属标签（该机构是哪些方面的教育培训）
 */
@Data
@AllArgsConstructor
public class OrganizationTag extends BaseEntity {

    private String id;

    /**
     * 机构id
     */
    private String organizationId;

    /**
     * 标签id
     */
    private String categoryId;
}
