package com.shmilyou.service.impl;

import com.shmilyou.entity.Course;
import com.shmilyou.repository.CourseRepository;
import com.shmilyou.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with 岂止是一丝涟漪     530060499@qq.com    2018/8/18
 */
@Service
public class CourseServiceImpl extends BaseServiceImpl<Course> implements CourseService {

    @Autowired
    CourseServiceImpl(CourseRepository baseRepository) {
        super(baseRepository);
    }
}
