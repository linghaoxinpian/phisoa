package com.shmilyou.web.controller;

/* Created with 岂止是一丝涟漪     530060499@qq.com    2018/10/29 */

import com.shmilyou.entity.Category;
import com.shmilyou.entity.Course;
import com.shmilyou.entity.Lecturer;
import com.shmilyou.entity.Organization;
import com.shmilyou.entity.OrganizationOverview;
import com.shmilyou.service.AreaService;
import com.shmilyou.service.CategoryService;
import com.shmilyou.service.CourseOrderService;
import com.shmilyou.service.CourseService;
import com.shmilyou.service.LecturerService;
import com.shmilyou.service.OrganizationService;
import com.shmilyou.service.bo.AreaCode;
import com.shmilyou.utils.Constant;
import com.shmilyou.utils.Utils;
import com.shmilyou.utils.WebUtils;
import com.shmilyou.web.controller.vo.CourseVO;
import com.shmilyou.web.controller.vo.LecturerVO;
import com.shmilyou.web.controller.vo.OrganizationVO;
import com.shmilyou.web.resolver.LoginOrganization;
import org.springframework.beans.BeanUtils;
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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
    @Autowired
    private AreaService areaService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(LoginOrganization loginOrganization, ModelMap modelMap) {
        if (loginOrganization == null) {
            return "error";
        }
        //获取机构
        Organization organization = organizationService.queryById(loginOrganization.getId());
        organization.setLogo(Constant.PIC_ORGANIZATION_LOGO_PATH + organization.getId() + "/" + organization.getLogo());
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
    public String showOrganizationInfo(LoginOrganization loginOrganization, ModelMap modelMap) {
        if (loginOrganization == null) {
            return "error";
        }
        //获取机构
        Organization organization = organizationService.queryById(loginOrganization.getId());


        //
        modelMap.addAttribute("o", organization);
        return "edit_organization";
    }

    /** 更新基础信息 */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String updateOrganizationInfo(LoginOrganization loginOrganization, OrganizationVO organizationVO, AreaCode areaCode, ModelMap modelMap, HttpSession session) {
        if (loginOrganization == null) {
            return "error";
        }
        //1.获取机构
        Organization organization = organizationService.queryById(loginOrganization.getId());
        //2.更新logo
        if (organizationVO.getLogo() != null) {
            String path = session.getServletContext().getRealPath("/") + Constant.PIC_ORGANIZATION_LOGO_PATH + organization.getId() + "/";
            String fileName = WebUtils.uploadPicture(organizationVO.getLogo(), path, Utils.generateDateNum());
            organization.setLogo(fileName);
        }
        //3.更新地区
        String code = areaService.loadSelectAreaCode(areaCode);
        organization.setAreaCode(code.length() > 0 ? code : organization.getAreaCode());
        //4.更新其它文本数据
        organization.setName(organizationVO.getName());
        organization.setPlace(organizationVO.getPlace());
        organization.setPhone(organizationVO.getPhone());
        //5.同步至数据库
        organizationService.update(organization);

        //
        modelMap.addAttribute("o", organization);
        return "home_organization";
    }

    /** 新增课程GET */
    @RequestMapping(value = "/add/course", method = RequestMethod.GET)
    public String addCourse(ModelMap modelMap, LoginOrganization loginOrganization) {
        if (loginOrganization == null) {
            //return "非机构禁止访问";
            return "error";
        }

        //
        List<Lecturer> lecturers = lecturerService.queryByOrganizationId(loginOrganization.getId());
        List<Category> tagLevel_1 = categoryService.queryByLevel(Category.Level1);

        //
        modelMap.addAttribute("o", loginOrganization);
        modelMap.addAttribute("lecturers", lecturers);
        modelMap.addAttribute("tagLevel_1", tagLevel_1);
        return "add_course";
    }

    /** 新增课程POST */
    @RequestMapping(value = "/add/course", method = RequestMethod.POST)
    public String addCourse(LoginOrganization loginOrganization, ModelMap modelMap, CourseVO courseVO, HttpSession session) {
        if (loginOrganization == null) {
            return "error";
        }
        //1.
        Course course = new Course();
        BeanUtils.copyProperties(courseVO, course);
        //2.处理封面图片(新上传覆盖旧的)
        if (courseVO.getPicUrl() != null) {
            String path = session.getServletContext().getRealPath("/") + Constant.PIC_COURSE_PATH + course.getId() + "/";
            String fileName = WebUtils.uploadPicture(courseVO.getPicUrl(), path, Utils.generateDateNum());
            course.setPicUrl(fileName.length() > 0 ? fileName : course.getPicUrl());
        }
        //3.处理多张宣传图片(新上传覆盖旧的)
        if (courseVO.getPictures() != null) {
            List pictures = new ArrayList();
            String path = session.getServletContext().getRealPath("/") + Constant.PIC_COURSE_PATH + course.getId() + "/";
            for (MultipartFile pic : courseVO.getPictures()) {
                String fileName = WebUtils.uploadPicture(pic, path, UUID.randomUUID().toString());
                if (!StringUtils.isEmpty(fileName)) {
                    pictures.add(fileName);
                }
            }
            if (pictures.size() > 0) {
                course.setPictures(Utils.generateJson(pictures));
            }
        }

        //
        courseService.insert(course);
        return "home_organization";
    }

    /** 课程管理 */
    @RequestMapping(value = "/show/courses", method = RequestMethod.GET)
    public String editCourse(LoginOrganization loginOrganization, ModelMap modelMap) {
        if (loginOrganization == null) {
            return "error";
        }

        //获取所有课程
        List<Course> courses = courseService.queryByOrganizationId(loginOrganization.getId());
        //处理图片地址
        courses.forEach(c -> {
            c.setPicUrl(Constant.PIC_COURSE_PATH + c.getId() + "/" + c.getPicUrl());
        });

        //
        modelMap.addAttribute("o", loginOrganization);
        modelMap.addAttribute("courses", courses);

        return "show_course";
    }

    /** 课程编辑 */
    @RequestMapping(value = "/edit/course/{id}", method = RequestMethod.GET)
    public String editCourse(LoginOrganization loginOrganization, @PathVariable("id") String courseId, ModelMap modelMap) {
        if (loginOrganization == null) {
            return "error";
        }
        //1.获取需要修改的课程
        Course course = courseService.loadByOrganizationIdAndCourseId(loginOrganization.getId(), courseId);
        if (course == null) {
            //该课程非当前登录机构的课程
            return "error";
        }
        course.setPicUrl(Constant.PIC_COURSE_PATH + course.getId() + course.getPicUrl());
        course.setParsedPictures(Utils.parseJsonArr(course.getPictures()));
        //2.获取机构讲师
        List<Lecturer> lecturers = lecturerService.queryByOrganizationId(loginOrganization.getId());
        //3.获取一级标签分类
        List<Category> tagLevel_1 = categoryService.queryByLevel(Category.Level1);
        //
        modelMap.addAttribute("c", course);
        modelMap.addAttribute("lecturers", lecturers);
        modelMap.addAttribute("tagLevel_1", tagLevel_1);
        return "edit_course";
    }

    /** 更新课程 */
    @RequestMapping(value = "/edit/course", method = RequestMethod.POST)
    public String editCourse(LoginOrganization loginOrganization, CourseVO courseVO, ModelMap modelMap, HttpSession session) {
        if (loginOrganization == null) {
            return "error";
        }
        //1.获取待更新课程
        Course course = courseService.loadByOrganizationIdAndCourseId(loginOrganization.getId(), courseVO.getId());
        if (course == null) {
            //该课程非当前登录机构的课程
            return "error";
        }
        //2.处理封面图片(新上传覆盖旧的)
        if (courseVO.getPicUrl() != null) {
            String path = session.getServletContext().getRealPath("/") + Constant.PIC_COURSE_PATH + course.getId() + "/";
            String fileName = WebUtils.uploadPicture(courseVO.getPicUrl(), path, Utils.generateDateNum());
            course.setPicUrl(fileName.length() > 0 ? fileName : course.getPicUrl());
        }
        //3.处理多张宣传图片(新上传覆盖旧的)
        if (courseVO.getPictures() != null) {
            List pictures = new ArrayList();
            String path = session.getServletContext().getRealPath("/") + Constant.PIC_COURSE_PATH + course.getId() + "/";
            for (MultipartFile pic : courseVO.getPictures()) {
                String fileName = WebUtils.uploadPicture(pic, path, UUID.randomUUID().toString());
                if (!StringUtils.isEmpty(fileName)) {
                    pictures.add(fileName);
                }
            }
            if (pictures.size() > 0) {
                course.setPictures(Utils.generateJson(pictures));
            }
        }
        //4.处理基本数据
        course.setName(courseVO.getName());
        course.setDescription(courseVO.getDescription());
        course.setPrice(courseVO.getPrice());
        course.setOriginalPrice(courseVO.getOriginalPrice());
        course.setLevel(courseVO.getLevel());
        course.setSuitable(courseVO.getSuitable());
        course.setTrainingModel(courseVO.getTrainingModel());
        course.setDuration(courseVO.getDuration());
        course.setEnvironment(courseVO.getEnvironment());
        course.setFeature(courseVO.getFeature());
        course.setTarget(courseVO.getTarget());
        course.setStartTime(courseVO.getStartTime());
        course.setOwnerId(loginOrganization.getId());
        course.setCategoryId(courseVO.getCategoryId());
        course.setLecturerId(courseVO.getLecturerId());
        //5.更新
        courseService.update(course);

        return "redirect:/phisoa/manager/organization/";
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

    /** 添加讲师GET */
    @RequestMapping(value = "/add/lecturer", method = RequestMethod.GET)
    public String addLecturer(LoginOrganization loginOrganization) {
        if (loginOrganization == null) {
            return "非机构禁止访问";
        }
        return "add_lecturer";
    }

    /** 添加讲师POST */
    @RequestMapping(value = "/add/lecturer", method = RequestMethod.POST)
    public String addLecturer(LoginOrganization loginOrganization, LecturerVO lecturerVO, HttpSession session) {
        if (StringUtils.isEmpty(lecturerVO.getName())) {
            return "name is null";
        }
        Lecturer lecturer = new Lecturer();
        BeanUtils.copyProperties(lecturerVO, lecturer);
        lecturer.setOrganizationId(loginOrganization.getId());
        lecturer.setId(UUID.randomUUID().toString());
        //指定图片路径
        String path = session.getServletContext().getRealPath("/") + Constant.PIC_LECTURER_PATH + lecturer.getId() + "/";
        //保存图片
        String fileName = WebUtils.uploadPicture(lecturerVO.getSelfie(), path, "selfie");
        lecturer.setSelfie(fileName);

        //新增讲师
        int raw = lecturerService.insert(lecturer);
        if (raw <= 0) {
            return "添加失败";
        }
        return "ok";

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
