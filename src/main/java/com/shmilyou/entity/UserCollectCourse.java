package com.shmilyou.entity;
/* Created with 岂止是一丝涟漪     530060499@qq.com    2018/11/6 */

import lombok.Data;

import java.util.Date;

/** 收藏的课程 */

@Data
public class UserCollectCourse extends BaseEntity {

    private String id;

    private String courseId;

    private String courseName;

    private String organizationId;

    private String userId;

    private Date addTime;
}
