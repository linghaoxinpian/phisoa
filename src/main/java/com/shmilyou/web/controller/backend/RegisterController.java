package com.shmilyou.web.controller.backend;

import com.shmilyou.entity.Category;
import com.shmilyou.service.CategoryService;
import com.shmilyou.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created with 岂止是一丝涟漪     530060499@qq.com    2018/10/5
 */
@Controller("backendRegisterController")
@RequestMapping("/phisoa/backend/register")
public class RegisterController extends BaseController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "tag", method = RequestMethod.POST)
    @ResponseBody
    public String registerTag(Category category) {
        if (StringUtils.isEmpty(category.getName())) {
            return "名称为空";
        }
        category.setLevel(Category.Level1);
        if (!StringUtils.isEmpty(category.getParentId())) {
            Category parentTag = categoryService.queryById(category.getParentId());
            if (parentTag == null) {
                return "父级不存在";
            } else {
                category.setLevel(parentTag.getLevel() + 1);
            }
        }
        //插入
        categoryService.insert(category);
        return "ok";
    }

    //--------------------- GET ---------------------
    @RequestMapping(value = "tag", method = RequestMethod.GET)
    public String registerTag(ModelMap modelMap) {
        //加载一级分类
        List<Category> level1 = categoryService.queryByLevel(Category.Level1);
        modelMap.addAttribute("level1", level1);
        return "backend/register_tag";
    }
}
