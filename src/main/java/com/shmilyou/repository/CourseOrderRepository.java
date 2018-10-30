package com.shmilyou.repository;

import com.shmilyou.entity.CourseOrder;
import org.apache.ibatis.annotations.Param;

/**
 * Created with 岂止是一丝涟漪     530060499@qq.com    2018年10月24日 17:08:43
 */
public interface CourseOrderRepository extends BaseRepository<CourseOrder> {

    /** 获取交易额 */
    int getAllMoney(String organizationId);

    /** 更新订单状态 */
    void updateStatus(@Param("id") String id, @Param("status") int status);
}
