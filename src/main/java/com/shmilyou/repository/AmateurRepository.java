package com.shmilyou.repository;

import com.shmilyou.entity.Amateur;

import java.util.List;

/**
 * Created with 岂止是一丝涟漪     530060499@qq.com    2018/8/18
 */
public interface AmateurRepository extends BaseRepository<Amateur> {

    /**
     * like模糊查询
     *
     * @param place
     * @return
     */
    List<Amateur> queryByPlace(String place);
}
