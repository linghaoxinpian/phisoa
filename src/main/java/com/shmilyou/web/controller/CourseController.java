package com.shmilyou.web.controller;

import com.shmilyou.entity.Category;
import com.shmilyou.entity.Course;
import com.shmilyou.service.CategoryService;
import com.shmilyou.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created with 岂止是一丝涟漪     530060499@qq.com    2018/10/12
 */
@RequestMapping("/phisoa/course")
@Controller
public class CourseController extends BaseController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CourseService courseService;

    //--------------------- GET ---------------------
    //机构新增课程
    @RequestMapping(value = "/add_course_organization", method = RequestMethod.GET)
    public String addCourse1(ModelMap modelMap) {
        List<Category> level1 = categoryService.queryByLevel(Category.Level1);
        modelMap.addAttribute("level1", level1);
        return "add_course";
    }

    //爱好者新增课程
    @RequestMapping(value = "/add_course_amateur", method = RequestMethod.GET)
    public String addCourse2(ModelMap modelMap) {
        List<Category> level1 = categoryService.queryByLevel(Category.Level1);
        modelMap.addAttribute("level1", level1);
        return "add_course";
    }

    //搜索课程by name
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String searchCourse(@RequestParam(value = "name") String name, @RequestParam("pageIndex") Integer pageIndex, @RequestParam("pageSize") Integer pageSize) {
        if (StringUtils.isEmpty(name)) {
            //搜索条件为空，随意加载课程
            return "异步与否？";
        }
        //查询总数量，生成分页
        int count = courseService.count();

        List<Course> courses = courseService.queryByName(name, pageIndex - 1, pageSize);
        return "search_course_result";
    }
}
