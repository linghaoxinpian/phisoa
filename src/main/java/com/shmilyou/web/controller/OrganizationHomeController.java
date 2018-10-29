package com.shmilyou.web.controller;

/** Created with 岂止是一丝涟漪     530060499@qq.com    2018/10/29 */

import com.shmilyou.entity.Course;
import com.shmilyou.entity.Lecturer;
import com.shmilyou.entity.Organization;
import com.shmilyou.entity.OrganizationOverview;
import com.shmilyou.service.CategoryService;
import com.shmilyou.service.CourseOrderService;
import com.shmilyou.service.CourseService;
import com.shmilyou.service.LecturerService;
import com.shmilyou.service.OrganizationService;
import com.shmilyou.utils.Constant;
import com.shmilyou.utils.Utils;
import com.shmilyou.utils.WebUtils;
import com.shmilyou.web.resolver.LoginOrganization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
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
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private LecturerService lecturerService;

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
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String showCourses(LoginOrganization loginOrganization, ModelMap modelMap) {
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
    @RequestMapping(value = "/show/courses", method = RequestMethod.GET)
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
    @RequestMapping(value = "/edit/course/{id}", method = RequestMethod.GET)
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
    @RequestMapping(value = "/rm/course", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> removeCourse(LoginOrganization loginOrganization, @RequestBody String courseId) {
        int row = courseService.delete(courseId);
        if (row > 0) {
            return WebUtils.ok();
        }
        return WebUtils.error("删除失败");
    }

    /** 机构新增课程 */
    @RequestMapping(value = "/add/course", method = RequestMethod.GET)
    public String addCourse1(ModelMap modelMap, LoginOrganization loginOrganization) {
        if (loginOrganization == null) {
            return "非机构禁止访问";
        }

        //获取讲师
        List<Lecturer> lecturers = lecturerService.queryByOrganizationId(loginOrganization.getId());

        //
        modelMap.addAttribute("o", loginOrganization);
        modelMap.addAttribute("lecturers", lecturers);
        return "add_course";
    }

    /** 讲师管理 */
    @RequestMapping(value = "/show/lecturers", method = RequestMethod.GET)
    public String showLecturers(LoginOrganization loginOrganization, ModelMap modelMap) {
        if (loginOrganization == null) {
            return "error";
        }
        //获取机构
        Organization organization = organizationService.queryById(loginOrganization.getId());
        //获取所有讲师
        List<Lecturer> lecturers = lecturerService.queryByOrganizationId(organization.getId());


        //
        modelMap.addAttribute("o", organization);
        modelMap.addAttribute("lecturers", lecturers);

        //modelMap.addAttribute("cPath", Constant.PIC_COURSE_PATH);
        //modelMap.addAttribute("oPhotoPath", Constant.PIC_ORGANIZATION_PHOTO_PATH);
        //modelMap.addAttribute("oLogoPath", Constant.PIC_ORGANIZATION_PHOTO_PATH);
        //modelMap.addAttribute("oCommentsPath", Constant.PIC_ORGANIZATION_COMMENTS_PATH);
        modelMap.addAttribute("lPath", Constant.PIC_LECTURER_PATH);
        //modelMap.addAttribute("uPath", Constant.PIC_USER_HEAD_PATH);
        //modelMap.addAttribute("ccPath", Constant.PIC_COURSE_COMMENT_PATH);
        return "show_lecturer";
    }

    /** 讲师编辑 */
    @RequestMapping(value = "/edit/lecturer/", method = RequestMethod.POST)
    public String editLecturer(LoginOrganization loginOrganization, @RequestBody String lecturerId, ModelMap modelMap) {
        if (loginOrganization == null) {
            return "error";
        }
        //获取需要修改的
        Lecturer lecturer = lecturerService.queryById(lecturerId);


        //
        modelMap.addAttribute("l", lecturer);
        modelMap.addAttribute("lPath", Constant.PIC_LECTURER_PATH);

        return "edit_lecturer";
    }

    /** 删除讲师 */
    @RequestMapping(value = "/rm/lecturer", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> removeLecturer(LoginOrganization loginOrganization, @RequestBody String lecturerId) {
        int row = lecturerService.delete(lecturerId);
        if (row > 0) {
            return WebUtils.ok();
        }
        return WebUtils.error("删除失败");
    }

    /** 相册管理 */
    @RequestMapping(value = "show/photos", method = RequestMethod.GET)
    public String showPhotos(LoginOrganization loginOrganization, ModelMap modelMap) {
        if (loginOrganization == null) {
            return "error";
        }
        //获取机构
        Organization organization = organizationService.queryById(loginOrganization.getId());
        //解析
        if (!StringUtils.isEmpty(organization.getPosters())) {
            organization.setParsedPosters(Utils.parseJsonArr(organization.getPosters()));
        }

        //
        modelMap.addAttribute("o", organization);

        //modelMap.addAttribute("cPath", Constant.PIC_COURSE_PATH);
        //modelMap.addAttribute("oPhotoPath", Constant.PIC_ORGANIZATION_PHOTO_PATH);
        //modelMap.addAttribute("oLogoPath", Constant.PIC_ORGANIZATION_PHOTO_PATH);
        //modelMap.addAttribute("oCommentsPath", Constant.PIC_ORGANIZATION_COMMENTS_PATH);
        //modelMap.addAttribute("uPath", Constant.PIC_USER_HEAD_PATH);
        //modelMap.addAttribute("ccPath", Constant.PIC_COURSE_COMMENT_PATH);
        return "show_photos";
    }
}
