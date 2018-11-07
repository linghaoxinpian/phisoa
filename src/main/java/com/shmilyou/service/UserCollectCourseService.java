package com.shmilyou.service;
/* Created with 岂止是一丝涟漪     530060499@qq.com    2018/11/7 */

import com.shmilyou.entity.UserCollectCourse;

import java.util.List;

public interface UserCollectCourseService extends BaseService<UserCollectCourse> {

    /** 查询求学者的所有收藏 */
    List<UserCollectCourse> queryByUserId(String userId);

    /** 移除某人的收藏课程 */
    UserCollectCourse removeCollectCourseById(String userId, String collectId);
}
