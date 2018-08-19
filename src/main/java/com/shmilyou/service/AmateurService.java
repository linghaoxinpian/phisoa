package com.shmilyou.service;

import com.shmilyou.entity.Amateur;

import java.util.List;

/**
 * Created with 岂止是一丝涟漪     530060499@qq.com    2018/8/18
 */
public interface AmateurService extends BaseService<Amateur> {

    /**
     * 根据爱好者名模糊查询
     * @param amateurName
     * @return
     */
    List<Amateur> searchByName(String amateurName);

    /**
     * 根据地区查询爱好者
     * @param place
     * @return
     */
    List<Amateur> searchByPlace(String place);

    /**
     * 根据【单个标签】查询爱好者
     * @param tagName
     * @param pageIndex 当前页页码
     * @return
     */
    List<Amateur> searchByTag(String tagName,String pageIndex,String pageSize);
}
