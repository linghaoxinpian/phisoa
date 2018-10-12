package com.shmilyou.service.impl;

import com.shmilyou.entity.Category;
import com.shmilyou.repository.CategoryRepository;
import com.shmilyou.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with 岂止是一丝涟漪     530060499@qq.com    2018/8/18
 */
@Service
public class CategoryServiceImpl extends BaseServiceImpl<Category> implements CategoryService {

    @Autowired
    CategoryServiceImpl(CategoryRepository baseRepository) {
        super(baseRepository);
    }

    //-------------------方法区-------------------
    @Override
    public List<Category> queryByParentId(String parentId) {
        return queryByColumn("parentId", parentId);
    }

    @Override
    public List<Category> queryByLevel(int level) {
        return queryByColumn("level", String.valueOf(level));
    }
}
