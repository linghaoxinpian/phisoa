package com.shmilyou.service.impl;

import com.shmilyou.entity.CourseComment;
import com.shmilyou.repository.CourseCommentRepository;
import com.shmilyou.service.CourseCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with 岂止是一丝涟漪     530060499@qq.com    2018年10月24日 17:14:06
 */
@Service
public class CourseCommentServiceImpl extends BaseServiceImpl<CourseComment> implements CourseCommentService {

    @Autowired
    CourseCommentServiceImpl(CourseCommentRepository baseRepository) {
        super(baseRepository);
    }

    @Autowired
    private CourseCommentRepository courseCommentRepository;

    //-------------------方法区-------------------

    @Override
    public List<CourseComment> loadNewestCommentsByCourseId(String courseId, int pageIndex, int pageSize) {
        return courseCommentRepository.queryNewestCommentsByCourseId(courseId, pageIndex, pageSize);
    }

    @Override
    public List<CourseComment> loadNewestCommentsByUserId(String userId, int pageIndex, int pageSize) {
        return courseCommentRepository.queryNewestCommentsByUserId(userId, pageIndex, pageSize);
    }
}
