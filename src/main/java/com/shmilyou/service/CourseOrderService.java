package com.shmilyou.service;

import com.shmilyou.entity.CourseOrder;

import java.util.List;

/**
 * Created with 岂止是一丝涟漪     530060499@qq.com    2018年10月24日 17:10:20
 */
public interface CourseOrderService extends BaseService<CourseOrder> {

    /** 获取交易额 */
    int getAllMoney(String organizationId);

    /** 更新订单状态 */
    void updateStatus(String id, int status);

    /** 加载最新订单 */
    List<CourseOrder> loadNewestByUserId(String userId, int pageIndex, int pageSize);
}
