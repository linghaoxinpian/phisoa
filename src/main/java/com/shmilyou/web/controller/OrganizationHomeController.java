package com.shmilyou.web.controller;

/** Created with 岂止是一丝涟漪     530060499@qq.com    2018/10/29 */

import com.shmilyou.entity.Course;
import com.shmilyou.entity.Organization;
import com.shmilyou.entity.OrganizationOverview;
import com.shmilyou.service.CourseOrderService;
import com.shmilyou.service.CourseService;
import com.shmilyou.service.OrganizationService;
import com.shmilyou.utils.Constant;
import com.shmilyou.utils.WebUtils;
import com.shmilyou.web.resolver.LoginOrganization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/** 机构的管理，非公开可访问的 */
@Controller
@RequestMapping("/phisoa/manager/organization")
public class OrganizationHomeController extends BaseController {

    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private CourseOrderService courseOrderService;
    @Autowired
    private CourseService courseService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(LoginOrganization loginOrganization, ModelMap modelMap) {
        if (loginOrganization == null) {
            return "error";
        }
        //获取机构
        Organization organization = organizationService.queryById(loginOrganization.getId());
        //获取机构评分
        OrganizationOverview overview = organization.getOverview();
        //获取交易额
        int sumMoney = courseOrderService.getAllMoney(organization.getId());
        //成交量
        int count = courseOrderService.count();

        //
        modelMap.addAttribute("o", organization);
        modelMap.addAttribute("overview", overview);
        modelMap.addAttribute("sumMoney", sumMoney);
        modelMap.addAttribute("count", count);
        return "home_organization";
    }

    /** 基础信息管理 */
    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String edit(LoginOrganization loginOrganization, ModelMap modelMap) {
        if (loginOrganization == null) {
            return "error";
        }
        //获取机构
        Organization organization = organizationService.queryById(loginOrganization.getId());


        //
        modelMap.addAttribute("o", organization);
        return "edit_organization";
    }

    /** 课程管理 */
    @RequestMapping(value = "show/courses", method = RequestMethod.GET)
    public String editCourse(LoginOrganization loginOrganization, ModelMap modelMap) {
        if (loginOrganization == null) {
            return "error";
        }
        //获取机构
        Organization organization = organizationService.queryById(loginOrganization.getId());
        //获取所有课程
        List<Course> courses = courseService.queryByOrganizationId(organization.getId());


        //
        modelMap.addAttribute("o", organization);
        modelMap.addAttribute("courses", courses);

        modelMap.addAttribute("cPath", Constant.PIC_COURSE_PATH);
        //modelMap.addAttribute("oPhotoPath", Constant.PIC_ORGANIZATION_PHOTO_PATH);
        //modelMap.addAttribute("oLogoPath", Constant.PIC_ORGANIZATION_PHOTO_PATH);
        //modelMap.addAttribute("oCommentsPath", Constant.PIC_ORGANIZATION_COMMENTS_PATH);
        //modelMap.addAttribute("lPath", Constant.PIC_LECTURER_PATH);
        //modelMap.addAttribute("uPath", Constant.PIC_USER_HEAD_PATH);
        //modelMap.addAttribute("ccPath", Constant.PIC_COURSE_COMMENT_PATH);
        return "show_course";
    }

    /** 课程编辑 */
    @RequestMapping(value = "edit/course/{id}", method = RequestMethod.GET)
    public String editCourse(LoginOrganization loginOrganization, @PathVariable("id") String courseId, ModelMap modelMap) {
        if (loginOrganization == null) {
            return "error";
        }
        //获取需要修改的课程
        Course course = courseService.queryById(courseId);


        //
        modelMap.addAttribute("course", course);
        modelMap.addAttribute("cPath", Constant.PIC_COURSE_PATH);

        return "edit_course";
    }

    /** 删除课程 */
    @RequestMapping(value = "rm/course", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> removeCourse(LoginOrganization loginOrganization, @RequestBody String courseId) {
        int row = courseService.delete(courseId);
        if (row > 0) {
            return WebUtils.ok();
        }
        return WebUtils.error("删除失败");
    }
}
