package com.shmilyou.web.controller;

import com.shmilyou.entity.Category;
import com.shmilyou.service.CategoryService;
import com.shmilyou.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/** Created with 岂止是一丝涟漪     530060499@qq.com    2018/10/30 */
@Controller
@RequestMapping("/phisoa/tag")
public class CategoryController extends BaseController {

    @Autowired
    private CategoryService categoryService;

    /** 根据父级标签获取下级标签的接口 */
    @RequestMapping(value = "/getChildTags", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity getChildTags(String tagId) {
        if (StringUtils.isEmpty(tagId)) {
            //return WebUtils.error("上级标签不能为空");
            List<Category> level0 = categoryService.queryByLevel(Category.Level1);
            return WebUtils.ok(level0);
        }
        List<Category> childTags = categoryService.queryByParentId(tagId);
        return WebUtils.ok(childTags);
    }

    @RequestMapping(value = "/x")
    public String x() {
        return "select";
    }
}
