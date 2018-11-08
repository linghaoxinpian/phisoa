package com.shmilyou.repository;

import com.shmilyou.entity.Organization;
import com.shmilyou.entity.OrganizationOverview;
import com.shmilyou.entity.OrganizationTag;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created with 岂止是一丝涟漪     530060499@qq.com    2018/8/18
 */
public interface OrganizationRepository extends BaseRepository<Organization> {

    List<Organization> queryByTagId(@Param("tagId") String tagId, @Param("pageIndex") int pageIndex,
                                    @Param("pageSize") int pageSize);

    /**
     * 注册
     *
     * @param organization
     * @return
     */
    int register(Organization organization);

    /**
     * 给机构添加标签
     * <p>OrganizationTag.id请设为null</p>
     *
     * @param organizationTags
     * @return 批量执行成功条数
     */
    int insertOrganizationTag(List<OrganizationTag> organizationTags);

    /** 批量查询 */
    List<Organization> queryByIds(List<String> ids);

    OrganizationOverview queryOverviewById(String organizationId);

    void updateOverView(OrganizationOverview o);

    void insertOverView(OrganizationOverview o);
}
