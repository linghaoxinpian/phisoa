package com.shmilyou.entity;

import lombok.Data;

/**
 * Created with 岂止是一丝涟漪     530060499@qq.com    2018/8/19
 */

@Data
public class AmateurTag extends BaseEntity {

    private String id;

    /**
     * 爱好者id
     */
    private String amateurId;

    /**
     * 标签Id
     */
    private String categoryId;
}
