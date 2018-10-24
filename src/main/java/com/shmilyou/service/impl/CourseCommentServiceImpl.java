package com.shmilyou.service.impl;

import com.shmilyou.entity.CourseOrder;
import com.shmilyou.repository.CourseOrderRepository;
import com.shmilyou.service.CourseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with 岂止是一丝涟漪     530060499@qq.com    2018年10月24日 17:14:06
 */
@Service
public class CourseCommentServiceImpl extends BaseServiceImpl<CourseOrder> implements CourseOrderService {

    @Autowired
    CourseCommentServiceImpl(CourseOrderRepository baseRepository) {
        super(baseRepository);
    }
}
