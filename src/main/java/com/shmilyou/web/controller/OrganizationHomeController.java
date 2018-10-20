package com.shmilyou.web.controller;

import com.shmilyou.entity.Lecturer;
import com.shmilyou.service.LecturerService;
import com.shmilyou.service.OrganizationCommentService;
import com.shmilyou.service.bo.OrganizationCommentBO;
import com.shmilyou.web.resolver.LoginOrganization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created with 岂止是一丝涟漪     530060499@qq.com    2018/10/19
 */
@Controller
@RequestMapping("/phisoa/organization")
public class OrganizationHomeController extends BaseController {

    @Autowired
    private LecturerService lecturerService;
    @Autowired
    private OrganizationCommentService organizationCommentService;

    //机构个人主页
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(LoginOrganization loginOrganization) {
        //1.获取讲师
        List<Lecturer> lecturers = getLecturers(loginOrganization.getId());
        //2.获取用户评价
        OrganizationCommentBO commentBO = getComments(loginOrganization.getId());

        return "";
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
    public OrganizationCommentBO getComments(String organizationId) {
        OrganizationCommentBO organizationComments = organizationCommentService.loadCommentsAndOverallGraph(organizationId, 0, 20);
        return organizationComments;
    }

    /**
     * 解析
     */
    private List<String> getPhotoAlbum(String jsonArray) {
        return null;
    }
}
