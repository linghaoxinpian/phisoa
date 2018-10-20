package com.shmilyou.service;

import com.shmilyou.entity.OrganizationComment;
import com.shmilyou.service.bo.OrganizationCommentBO;

/**
 * Created with 岂止是一丝涟漪     530060499@qq.com    2018/10/20
 */
public interface OrganizationCommentService extends BaseService<OrganizationComment> {

    /**
     * 加载机构的评论及其【总体能力图】
     *
     * @return
     */
    OrganizationCommentBO loadCommentsAndOverallGraph(String organizationId, int pageIndex, int pageSize);

    /**
     * 加载机构【综合能力图】
     *
     * @return
     */
    OrganizationCommentBO loadOverallGraph(String organizationId);
}
