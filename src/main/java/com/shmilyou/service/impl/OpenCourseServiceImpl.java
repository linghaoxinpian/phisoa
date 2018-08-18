package com.shmilyou.service.impl;

import com.shmilyou.entity.OpenCourse;
import com.shmilyou.repository.OpenCourseRepository;
import com.shmilyou.service.OpenCourseService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with 岂止是一丝涟漪     530060499@qq.com    2018/8/18
 */
public class OpenCourseServiceImpl extends BaseServiceImpl<OpenCourse> implements OpenCourseService {

    @Autowired
    OpenCourseServiceImpl(OpenCourseRepository baseRepository) {
        super(baseRepository);
    }
}
