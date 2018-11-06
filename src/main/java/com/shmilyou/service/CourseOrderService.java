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

    /** 分页加载求学者的订单按时间降序 */
    List<CourseOrder> loadNewestByUserId(String userId, int pageIndex, int pageSize);

    /** 加载求学者的全部订单按时间降序 */
    List<CourseOrder> loadNewestByUserId(String userId);

    /** 检查用户是否购买过此课程 */
    CourseOrder loadByCourseIdAndUserId(String courseId, String userId);

    /** 更新某一订单的评论次数 */
    int plusCommentsNum(String id);

    /** 删除某求学者的订单 */
    int deleteByUserIdAndOrderId(String userId, String orderId);

    /** 加载机构的全部订单 */
    List<CourseOrder> loadByOrganizationId(String organizationId);

    /** 加载机构的某一状态的订单(已支付，未支付) */
    List<CourseOrder> loadByOrganizationIdAndStatus(String organizationId, int status);

    /** 加载求学者的某一状态的订单(已支付，未支付) */
    List<CourseOrder> loadByUserIdAndStatus(String userId, int status);

    /** 加载求学者未评价的订单 */
    List<CourseOrder> loadNotCommentsByUserId(String userId);

    /** 【机构】加载一周订单信息 */
    List<CourseOrder> loadWeekByOrganizationId(String organizationId);

    /** 【求学者】加载一周订单信息 */
    List<CourseOrder> loadWeekByUserId(String organizationId);

}
