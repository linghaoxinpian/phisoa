package com.shmilyou.web.controller;

import com.shmilyou.entity.Course;
import com.shmilyou.entity.Lecturer;
import com.shmilyou.entity.Organization;
import com.shmilyou.entity.OrganizationComment;
import com.shmilyou.entity.OrganizationOverview;
import com.shmilyou.service.CourseService;
import com.shmilyou.service.LecturerService;
import com.shmilyou.service.OrganizationCommentService;
import com.shmilyou.service.OrganizationService;
import com.shmilyou.web.resolver.LoginOrganization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with 岂止是一丝涟漪     530060499@qq.com    2018/10/19
 */
@Controller
@RequestMapping("/phisoa/organization")
public class OrganizationIndexController extends BaseController {

    @Autowired
    private LecturerService lecturerService;
    @Autowired
    private OrganizationCommentService organizationCommentService;
    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private CourseService courseService;

    //机构个人主页
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(LoginOrganization loginOrganization, ModelMap modelMap) {
        //1.获取讲师
        List<Lecturer> lecturers = getLecturers(loginOrganization.getId());
        //2.获取用户评价
        List<OrganizationComment> comments = getComments(loginOrganization.getId());
        //3.获取机构【总体情况】
        Organization organization = organizationService.queryById(loginOrganization.getId());
        OrganizationOverview overview = organization.getOverview();
        //4.课程
        List<Course> courses = courseService.loadHomeCourse(loginOrganization.getId(), 0, 6);
        Map<String, Object> map = new HashMap<>();
        map.put("lecturer", lecturers);
        map.put("comments", comments);
        map.put("overview", overview);
        map.put("courses", courses);
        return "index_organization";
    }

    /**
     * 获取讲师
     */
    private List<Lecturer> getLecturers(String organizationId) {
        return lecturerService.queryByOrganizationId(organizationId, 0, 20);
    }

    /**
     * 获取用户评价信息
     */
    public List<OrganizationComment> getComments(String organizationId) {
        List<OrganizationComment> comments = organizationCommentService.loadComments(organizationId, 0, 20);
        return comments;
    }

    /**
     * 解析
     */
    private List<String> getPhotoAlbum(String jsonArray) {
        return null;
    }
}
