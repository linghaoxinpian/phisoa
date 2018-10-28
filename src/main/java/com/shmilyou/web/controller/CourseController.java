package com.shmilyou.web.controller;

import com.shmilyou.entity.Category;
import com.shmilyou.entity.Course;
import com.shmilyou.entity.CourseComment;
import com.shmilyou.entity.Organization;
import com.shmilyou.entity.OrganizationOverview;
import com.shmilyou.service.CategoryService;
import com.shmilyou.service.CourseCommentService;
import com.shmilyou.service.CourseService;
import com.shmilyou.service.OrganizationService;
import com.shmilyou.utils.Constant;
import com.shmilyou.utils.Utils;
import com.shmilyou.utils.WebUtils;
import com.shmilyou.web.controller.vo.CourseVO;
import com.shmilyou.web.resolver.LoginOrganization;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private CourseCommentService courseCommentService;

    //课程详情页
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String index(@PathVariable("id") String courseId, ModelMap modelMap) {
        Course course = courseService.queryById(courseId);
        Organization organization = organizationService.queryById(course.getOwnerId());
        List<String> tagNames = Collections.EMPTY_LIST;
        OrganizationOverview overview = null;
        if (organization != null) {
            //解析主要培训
            tagNames = organization.getCategoryList().stream().map(Category::getName).collect(Collectors.toList());
            //获取机构评分
            overview = organization.getOverview();
        }
        //解析讲师的作品图片
        List<String> showreels = Collections.emptyList();
        if (course.getLecturer() != null) {
            showreels = Utils.parseJsonArr(course.getLecturer().getShowreel());
        }
        //该机构其它课程
        List<Course> otherCourses = courseService.loadByOrganizationAndTagAndChildTag(course.getOwnerId(), course.getCategoryId());
        //课程评价
        List<CourseComment> comments = courseCommentService.loadNewestCommentsByCourseId(course.getId(), 0, 2);
        //综合评价的星级数量

        modelMap.addAttribute("course", course);
        modelMap.addAttribute("o", course.getOrganization());
        modelMap.addAttribute("overview", overview);
        modelMap.addAttribute("tagNames", tagNames);
        modelMap.addAttribute("lecturer", course.getLecturer());
        modelMap.addAttribute("showreels", showreels);
        modelMap.addAttribute("otherCourses", otherCourses);
        modelMap.addAttribute("comments", comments);
        modelMap.addAttribute("cPath", Constant.PIC_COURSE_PATH);
        modelMap.addAttribute("oPath", Constant.PIC_ORGANIZATION_PATH);
        modelMap.addAttribute("lPath", Constant.PIC_LECTURER_PATH);
        modelMap.addAttribute("uPath", Constant.PIC_USER_HEAD_PATH);
        return "course_detail";
    }

    @RequestMapping(value = "/organization/add", method = RequestMethod.POST)
    public ResponseEntity addCourse(@ModelAttribute("courseVO") CourseVO courseVO,
                                    @RequestParam("pic") MultipartFile pic,
                                    HttpSession session, LoginOrganization loginOrganization) throws IOException {

        if (loginOrganization == null) {
            return WebUtils.error("非机构禁止访问");
        }
        if (StringUtils.isEmpty(courseVO.getName()) || StringUtils.isEmpty(courseVO.getCategoryId())) {
            return WebUtils.error("非机构禁止访问");
        }
        Course course = new Course();
        BeanUtils.copyProperties(courseVO, course);
        course.setOwnerId(loginOrganization.getId());
        course.setId(UUID.randomUUID().toString());
        //指定图片路径
        String path = session.getServletContext().getRealPath("/") + Constant.PIC_COURSE_PATH + course.getId() + "/";
        //保存图片
        String fileName = WebUtils.uploadPicture(pic, path, "course");
        course.setPicUrl(fileName);
        //插入课程
        courseService.insert(course);
        return WebUtils.ok();
    }

    //搜索课程
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(String searchStr, Integer pageIndex, Integer pageSize, ModelMap modelMap) {
        pageIndex = 0;
        pageSize = 20;
        List<Course> courses = courseService.queryByName(searchStr, pageIndex, pageSize);
        modelMap.addAttribute("courses", courses);
        logger.info(courses.size() + "测试");
        modelMap.addAttribute("path", Constant.PIC_COURSE_PATH);
        return "search_course";
    }


    //--------------------- GET ---------------------

    //机构新增课程
    @RequestMapping(value = "/organization/add", method = RequestMethod.GET)
    public String addCourse1(ModelMap modelMap, LoginOrganization loginOrganization) {
        if (loginOrganization == null) {
            return "非机构禁止访问";
        }
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
    @RequestMapping(value = "/search/name", method = RequestMethod.GET)
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

    //搜索课程by tagId
    @RequestMapping(value = "/search/tag", method = RequestMethod.GET)
    public String loadCourseByTag(@RequestParam("tagId") String tagId) {
        if (StringUtils.isEmpty(tagId)) {
            return "查询条件为空";
        }
        List<Course> courses = courseService.loadByTagAndChildTag(tagId, 0, 20);
        return "result";
    }
}
