package com.shmilyou.service;

import com.shmilyou.entity.OrganizationComment;

import java.util.List;

/**
 * Created with 岂止是一丝涟漪     530060499@qq.com    2018/10/20
 */
public interface OrganizationCommentService extends BaseService<OrganizationComment> {

    /**
     * 加载机构的评论
     *
     * @return
     */
    List<OrganizationComment> loadComments(String organizationId, int pageIndex, int pageSize);

}
