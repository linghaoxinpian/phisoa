package com.shmilyou.repository;

import com.shmilyou.entity.OrganizationLevel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created with 岂止是一丝涟漪     530060499@qq.com    2018/8/18
 */
public interface OrganizationLevelRepository extends BaseRepository<OrganizationLevel> {

    List<OrganizationLevel> queryByIds(@Param("ids") List<String> ids);
}
