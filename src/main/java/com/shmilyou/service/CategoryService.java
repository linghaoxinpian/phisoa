package com.shmilyou.service;

import com.shmilyou.entity.Category;

import java.util.List;

/**
 * Created with 岂止是一丝涟漪     530060499@qq.com    2018/8/18
 */
public interface CategoryService extends BaseService<Category> {

    /**
     * 加载子分类标签
     *
     * @param parentId 父id
     * @return
     */
    List<Category> queryByParentId(String parentId);

    /**
     * 加载？级分类
     * @param level
     * @return
     */
    List<Category> queryByLevel(int level);
}
