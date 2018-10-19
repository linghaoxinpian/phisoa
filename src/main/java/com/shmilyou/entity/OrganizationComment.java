package com.shmilyou.entity;

import lombok.Data;

/**
 * Created with 岂止是一丝涟漪     530060499@qq.com    2018/10/18
 */

/**
 * 用户对机构的评论表
 */
@Data
public class OrganizationComment {

    private String id;

    private String comment;

    /**
     * 用户对机构的打星
     */
    private int star;

    private String organization_id;
}
