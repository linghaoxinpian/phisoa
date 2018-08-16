package com.shmilyou.entity;

/**
 * Created with 岂止是一丝涟漪
 * Date: 2018/8/16
 */

import lombok.Data;

/**
 * 每个机构的[著名讲师]
 */
@Data
public class Lecturer {

    private String id;

    /**
     * 描述(姓名、图片、学历、个人介绍、水平认证程度) 存富文本
     */
    private String description;

    /**
     * 扩展：个人资料地址(如百度百科地址https://baike.baidu.com/item/姓名)
     */
    private String baiduUrl;
}
