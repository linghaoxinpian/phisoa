package com.shmilyou.repository;

import com.shmilyou.entity.OrganizationComment;

import java.util.List;

/**
 * Created with 岂止是一丝涟漪     530060499@qq.com    2018/10/20
 */
public interface OrganizationCommentRepository extends BaseRepository<OrganizationComment> {

    List<OrganizationComment> querySumScores();
}
