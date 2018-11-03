package com.shmilyou.web.controller;

import com.shmilyou.entity.Category;
import com.shmilyou.entity.Course;
import com.shmilyou.entity.Lecturer;
import com.shmilyou.entity.Organization;
import com.shmilyou.entity.OrganizationComment;
import com.shmilyou.entity.OrganizationOverview;
import com.shmilyou.service.CourseService;
import com.shmilyou.service.LecturerService;
import com.shmilyou.service.OrganizationCommentService;
import com.shmilyou.service.OrganizationService;
import com.shmilyou.utils.Constant;
import com.shmilyou.utils.Utils;
import com.shmilyou.web.resolver.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.stream.Collectors;

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
    public String index(String organizationId, ModelMap modelMap) {
        //获取机构
        Organization organization = organizationService.queryById(organizationId);

        //处理机构海报
        if (!StringUtils.isEmpty(organization.getPosters())) {
            organization.setParsedPosters(Utils.parseJsonArr(organization.getPosters()));
        }
        //处理机构相册
        if (!StringUtils.isEmpty(organization.getPhotoAlbum())) {
            organization.setParsedPhotoAlbums(Utils.parseJsonArr(organization.getPhotoAlbum()));
        }
        //获取机构评分
        OrganizationOverview overview = organization.getOverview();

        //解析主要培训
        List<String> tagNames = organization.getCategoryList().stream().map(Category::getName).collect(Collectors.toList());

        //该机构其它课程
        List<Course> otherCourses = courseService.queryByOrganizationId(organization.getId(), 0, 6);

        //获取讲师
        List<Lecturer> lecturers = lecturerService.queryByOrganizationId(organization.getId(), 0, 5);

        //获取用户评价
        List<OrganizationComment> comments = organizationCommentService.loadComments(organizationId, 0, 20);


        modelMap.addAttribute("o", organization);
        modelMap.addAttribute("overview", overview);
        modelMap.addAttribute("tagNames", tagNames);
        modelMap.addAttribute("otherCourses", otherCourses);
        modelMap.addAttribute("lecturers", lecturers);
        modelMap.addAttribute("comments", comments);

        modelMap.addAttribute("cPath", Constant.PIC_COURSE_PATH);
        modelMap.addAttribute("oPhotoPath", Constant.PIC_ORGANIZATION_PHOTO_PATH);
        modelMap.addAttribute("oLogoPath", Constant.PIC_ORGANIZATION_PHOTO_PATH);
        modelMap.addAttribute("oCommentsPath", Constant.PIC_ORGANIZATION_COMMENTS_PATH);
        modelMap.addAttribute("lPath", Constant.PIC_LECTURER_PATH);
        modelMap.addAttribute("uPath", Constant.PIC_USER_HEAD_PATH);
        modelMap.addAttribute("ccPath", Constant.PIC_COURSE_COMMENT_PATH);
        return "index_organization";
    }

    /** 评论机构 */
    @RequestMapping(value = "/comment/add", method = RequestMethod.POST)
    public String addComment(LoginUser loginUser, OrganizationComment comment) {
        if (StringUtils.isEmpty(comment.getComment())) {
            return "comment is null";
        }
        comment.setUserId(loginUser.getId());
        int raw = organizationCommentService.add(comment);
        if (raw <= 0) {
            return "评论失败";
        }
        return "ok";
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
