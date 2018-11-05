package com.shmilyou.web.controller;

import com.shmilyou.entity.Category;
import com.shmilyou.entity.Course;
import com.shmilyou.entity.CourseComment;
import com.shmilyou.entity.CourseOrder;
import com.shmilyou.entity.Organization;
import com.shmilyou.entity.OrganizationOverview;
import com.shmilyou.service.CategoryService;
import com.shmilyou.service.CourseCommentService;
import com.shmilyou.service.CourseOrderService;
import com.shmilyou.service.CourseService;
import com.shmilyou.service.OrganizationService;
import com.shmilyou.utils.Constant;
import com.shmilyou.utils.Utils;
import com.shmilyou.utils.WebUtils;
import com.shmilyou.web.controller.vo.CourseCommentVO;
import com.shmilyou.web.controller.vo.CourseVO;
import com.shmilyou.web.resolver.LoginOrganization;
import com.shmilyou.web.resolver.LoginUser;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
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
    @Autowired
    private CourseOrderService courseOrderService;

    /** 课程详情页 */
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
        //otherCourses.forEach(oc -> oc.setPicUrl(Constant.PIC_COURSE_PATH + oc.getId() + "/" + oc.getPicUrl()));
        //课程评价
        List<CourseComment> comments = courseCommentService.loadNewestCommentsByCourseId(course.getId(), 0, 2);
        //处理课程评价的图片
        comments.forEach(c -> c.setParsedPictures(Utils.parseJsonArr(c.getPictures())));
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
        modelMap.addAttribute("oPath", Constant.PIC_ORGANIZATION_LOGO_PATH);
        modelMap.addAttribute("lPath", Constant.PIC_LECTURER_PATH);
        modelMap.addAttribute("uPath", Constant.PIC_USER_HEAD_PATH);
        modelMap.addAttribute("ccPath", Constant.PIC_COURSE_COMMENT_PATH);
        return "course_detail";
    }

    @RequestMapping(value = "/organization/add", method = RequestMethod.POST)
    public ResponseEntity addCourse(@ModelAttribute("courseVO") CourseVO courseVO, @RequestParam("pic") MultipartFile pic, HttpSession session, LoginOrganization loginOrganization) throws IOException {

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
    public String search() {
        return "search_course";
    }

    /** 评论课程 */
    @RequestMapping(value = "/comment/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> commentCourse(LoginUser loginUser, CourseCommentVO commentVO, HttpSession session) {
        //评论权限
        CourseOrder order = courseOrderService.loadByCourseIdAndUserId(commentVO.getCourseId(), loginUser.getId());
        if (order != null) {
            CourseComment comment = new CourseComment();
            BeanUtils.copyProperties(commentVO, comment);
            comment.setUserId(loginUser.getId());
            //处理图片
            List pictures = new ArrayList();
            if (commentVO.getPics() != null) {
                String path = session.getServletContext().getRealPath("/") + Constant.PIC_COURSE_COMMENT_PATH + commentVO.getCourseId() + "/";
                for (MultipartFile pic : commentVO.getPics()) {
                    String fileName = WebUtils.uploadPicture(pic, path, UUID.randomUUID().toString());
                    pictures.add(fileName);
                }
            }
            comment.setPictures(Utils.generateJson(pictures));

            //插入评论
            courseCommentService.insert(comment);
            //更新订单评论数
            courseOrderService.plusCommentsNum(order.getId());
            return WebUtils.ok();
        }
        return WebUtils.error("未购买不能评论");
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
    public String searchCourse(@RequestParam(value = "searchStr", required = false) String searchStr, @RequestParam(value = "pageIndex", required = false) Integer pageIndex, ModelMap modelMap) {
        if (StringUtils.isEmpty(searchStr)) {
            //搜索条件为空，打开搜索页
            return "search_course";
        }
        //处理分页，这里页面大小固定
        int pageSize = 20;
        if (pageIndex == null || pageIndex <= 0) {
            pageIndex = 1;
        }

        //查询总数量，生成分页
        int count = courseService.count();
        int totalPage = Math.floorMod(count, pageSize);

        List<Course> courses = courseService.queryByNameForLike(searchStr, pageIndex - 1, pageSize);
        courses.forEach(c -> {
            c.setPicUrl(Constant.PIC_COURSE_PATH + c.getId() + "/" + c.getPicUrl());
        });

        modelMap.addAttribute("condition", searchStr);
        modelMap.addAttribute("courses", courses);
        modelMap.addAttribute("totalPage", totalPage);
        modelMap.addAttribute("pageIndex", pageIndex);
        return "search_course";
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
