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

    /**
     * 新增留言
     *
     * @param comment
     * @return 影响行数
     */
    int add(OrganizationComment comment);

    /** 加载所有分数的总和 */
    List<OrganizationComment> loadScores();
}
