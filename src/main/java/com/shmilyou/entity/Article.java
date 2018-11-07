package com.shmilyou.entity;
/* Created with 岂止是一丝涟漪     530060499@qq.com    2018/11/6 */

import lombok.Data;

/** 平台向机构推送的文章 */
@Data
public class Article extends BaseEntity {

    private String id;

    /** 富文本 */
    private String content;

}
