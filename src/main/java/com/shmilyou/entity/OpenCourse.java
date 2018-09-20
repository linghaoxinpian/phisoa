package com.shmilyou.entity;

import lombok.Data;

/**
 * Created with 岂止是一丝涟漪
 * Date: 2018/8/14
 */

/**
 * 公开课视频
 */
@Data
public class OpenCourse extends BaseEntity {

    private String id;

    private String name;

    /**
     * 播放量
     */
    private Integer playAmount;

    private String videoUrl;

    /**
     * 优化查询，0为机构，1,为个人
     */
    private Integer ownerCategory;

    /**
     * 上传视频的机构Id
     */
    private String ownerId;

    /**
     * 所属分类Id
     */
    private String categoryId;
}
