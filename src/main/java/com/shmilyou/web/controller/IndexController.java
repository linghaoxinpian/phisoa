package com.shmilyou.web.controller;

import com.shmilyou.entity.Amateur;
import com.shmilyou.entity.Category;
import com.shmilyou.entity.Course;
import com.shmilyou.entity.OpenCourse;
import com.shmilyou.entity.Organization;
import com.shmilyou.service.AmateurService;
import com.shmilyou.service.CategoryService;
import com.shmilyou.service.CourseService;
import com.shmilyou.service.OpenCourseService;
import com.shmilyou.service.OrganizationService;
import com.shmilyou.service.UserService;
import com.shmilyou.web.resolver.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

/**
 * Created with 岂止是一丝涟漪
 * Date: 2018/8/16
 */
@Controller
@RequestMapping("/phisoa")
public class IndexController extends BaseController {
    @Autowired
    private Optional<UserService> userService;
    @Autowired
    private AmateurService amateurService;
    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private OpenCourseService openCourseService;

    @RequestMapping(value = {"/index", "/", ""})
    public String index(LoginUser loginUser, ModelMap modelMap) {
        //1.加载1级分类
        List<Category> level1Tag = categoryService.queryByLevel(Category.Level1);
        //2.加载【热门培训】
        List<Course> courses_hot = courseService.queryByTagId("7078ac33-befe-11e8-94b3-a353b48ca080", 1, 6);
        //3.加载【优秀公开课】
        List<OpenCourse> openCourses_hot = openCourseService.queryByTagId("7078ac33-befe-11e8-94b3-a353b48ca080", 1, 6);
        //4.加载【优秀爱好者】
        List<Amateur> amateurs_hot = amateurService.queryByTagId("7078ac33-befe-11e8-94b3-a353b48ca080", 0, 3);
        //5.加载【优秀机构】
        List<Organization> organizations_hot = organizationService.queryByTagId("7078ac33-befe-11e8-94b3-a353b48ca080", 0, 3);
        //6.排行【机构】【爱好者】
        List<Amateur> top1 = amateurService.indexRecommendTop();
        List<Organization> top2 = organizationService.indexRecommendTop();

        //
        modelMap.addAttribute("u", loginUser);
        modelMap.addAttribute("level1Tag", level1Tag);
        modelMap.addAttribute("courses_hot", courses_hot);
        modelMap.addAttribute("organizations_hot", organizations_hot);
        modelMap.addAttribute("organization_rank", top2);
        return "index";
    }

    @RequestMapping(value = "/news", method = RequestMethod.GET)
    @ResponseBody
    public List<Object> news() {
        //待定：行业资讯：展示文化动态机构前沿，多文化培训的重要性以及优秀机构展示
        return null;
    }

    @RequestMapping(value = "/aside", method = RequestMethod.POST)
    @ResponseBody
    public List<Object> aside() {
        //待定：机构栏目【参考腾讯网、】：该地区所有机构情况【若首推机构或个人饱和，此处依次往后展示】
        return null;
    }

    @RequestMapping(value = "/place_amateur_list", method = RequestMethod.POST)
    @ResponseBody
    public List<Object> amateurNews() {
        //待定：个人栏目：该地区所有个人情况
        return null;
    }

    @RequestMapping(value = " to_denounce", method = RequestMethod.GET)
    public String toDenounce() {
        return "to_denounce";
    }

    /**
     * 举报提交
     *
     * @param profile     举报人姓名
     * @param isAnonymity 是否匿名
     * @param content     举报内容
     */
    @RequestMapping(value = "to_denounce", method = RequestMethod.POST)
    public String toDenounce(String profile, Optional<String> email, String phone, String isAnonymity, String content) {
        return "to_denounce";
    }
}
