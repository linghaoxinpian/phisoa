package com.shmilyou.repository;

import com.shmilyou.entity.Area;

/**
 * Created with 岂止是一丝涟漪     530060499@qq.com    2018/8/22
 */
public interface AreaRepository extends BaseRepository<Area> {

    /**
     * 根据地区全称查询
     *
     * @param fullAreaName
     * @return
     */
    Area queryByFullName(String fullAreaName);
}
