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
        List<CourseComment> comments = courseCommentRepository.queryNewestCommentsByCourseId(courseId, pageIndex, pageSize);
        //CourseCommentBO commentBO = new CourseCommentBO();
        //commentBO.setComments(comments);
        //
        //Map<Integer, Long> temp = comments.stream().collect(Collectors.groupingBy(CourseComment::getStar,
        //        Collectors.counting()));
        ////1-3星为star1,3为中，4-5为高
        //commentBO.setStar1(Math.toIntExact(temp.get(1)));
        //commentBO.setStar2(Math.toIntExact(temp.get(1)));
        //commentBO.setStar3(Math.toIntExact(temp.get(1)));
        return comments;
    }

    @Override
    public List<CourseComment> loadNewestCommentsByUserId(String userId, int pageIndex, int pageSize) {
        return courseCommentRepository.queryNewestCommentsByUserId(userId, pageIndex, pageSize);
    }
}
