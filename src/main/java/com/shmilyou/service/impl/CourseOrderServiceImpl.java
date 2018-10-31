package com.shmilyou.service.impl;

import com.shmilyou.entity.CourseOrder;
import com.shmilyou.repository.CourseOrderRepository;
import com.shmilyou.service.CourseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with 岂止是一丝涟漪     530060499@qq.com    2018年10月24日 17:14:00
 */
@Service
public class CourseOrderServiceImpl extends BaseServiceImpl<CourseOrder> implements CourseOrderService {

    @Autowired
    CourseOrderServiceImpl(CourseOrderRepository baseRepository) {
        super(baseRepository);
    }

    @Autowired
    private CourseOrderRepository courseOrderRepository;

    @Override
    public int getAllMoney(String organizationId) {
        return courseOrderRepository.getAllMoney(organizationId);
    }

    @Override
    public void updateStatus(String id, int status) {
        courseOrderRepository.updateStatus(id, status);
    }

    @Override
    public List<CourseOrder> loadNewestByUserId(String userId, int pageIndex, int pageSize) {
        return courseOrderRepository.queryNewestByUserId(userId, pageIndex, pageSize);
    }
}
