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

    private String videoUrl;

    /**
     * 上传视频的机构Id
     */
    private String ownerId;

    /**
     * 所属分类Id
     */
    private String categoryId;
}
