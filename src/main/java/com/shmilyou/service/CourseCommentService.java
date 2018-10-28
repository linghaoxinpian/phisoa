package com.shmilyou.service;

import com.shmilyou.entity.CourseComment;

import java.util.List;

/**
 * Created with 岂止是一丝涟漪     530060499@qq.com    2018年10月24日 17:10:20
 */
public interface CourseCommentService extends BaseService<CourseComment> {

    /**
     * 加载最新评论
     */
    List<CourseComment> loadNewestCommentsByCourseId(String courseId, int pageIndex, int pageSize);
}
