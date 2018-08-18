package com.shmilyou.service.impl;

import com.shmilyou.entity.Category;
import com.shmilyou.repository.CategoryRepository;
import com.shmilyou.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with 岂止是一丝涟漪     530060499@qq.com    2018/8/18
 */
public class CategoryServiceImpl extends BaseServiceImpl<Category> implements CategoryService {

    @Autowired
    CategoryServiceImpl(CategoryRepository baseRepository) {
        super(baseRepository);
    }
}
