package com.shmilyou.repository;

import com.shmilyou.entity.CourseComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created with 岂止是一丝涟漪     530060499@qq.com    2018年10月24日 17:08:43
 */
public interface CourseCommentRepository extends BaseRepository<CourseComment> {

    /** 加载课程的最新评论 */
    List<CourseComment> queryNewestCommentsByCourseId(@Param("courseId") String courseId, @Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize);

    /** 加载求学者的最新评论 */
    List<CourseComment> queryNewestCommentsByUserId(@Param("userId") String userId, @Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize);

}
