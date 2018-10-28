package com.shmilyou.entity;

import lombok.Data;

/**
 * Created with 岂止是一丝涟漪     530060499@qq.com    2018/10/24
 */
/* 求学者对课程的评论 */
@Data
public class CourseComment extends BaseEntity {

    private String id;

    private String comment;

    /** 评论时所带的图片 */
    private String pictures;

    private String courseId;

    private String userId;

    //----------------- 非DB字段 -----------------

    private Course course;

    private User user;
}
