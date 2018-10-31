package com.shmilyou.web.controller;

import com.shmilyou.entity.CourseComment;
import com.shmilyou.entity.User;
import com.shmilyou.service.CourseCommentService;
import com.shmilyou.service.UserService;
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

/** Created with 岂止是一丝涟漪     530060499@qq.com    2018/10/31 */
@Controller
@RequestMapping("/phisoa/manager/user")
public class UserHomeController extends BaseController {

    @Autowired
    private UserService userService;
    @Autowired
    private CourseCommentService courseCommentService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(LoginUser loginUser, ModelMap modelMap) {
        if (loginUser == null) {
            return "error";
        }
        //获取用户
        User user = userService.queryById(loginUser.getId());
        //获取用户最新的课程评论
        List<CourseComment> newestCourseComment = courseCommentService.loadNewestCommentsByUserId(user.getId(), 0, 1);
        //解析相册
        if (!StringUtils.isEmpty(user.getPhotoAlbum())) {
            user.setParsedPhotoAlbums(Utils.parseJsonArr(user.getPhotoAlbum()));
        }

        modelMap.addAttribute("u", user);
        modelMap.addAttribute("newestCourseComment", newestCourseComment);


        //
        //modelMap.addAttribute("cPath", Constant.PIC_COURSE_PATH);
        //modelMap.addAttribute("oPhotoPath", Constant.PIC_ORGANIZATION_PHOTO_PATH);
        //modelMap.addAttribute("oLogoPath", Constant.PIC_ORGANIZATION_PHOTO_PATH);
        //modelMap.addAttribute("oCommentsPath", Constant.PIC_ORGANIZATION_COMMENTS_PATH);
        //modelMap.addAttribute("lPath", Constant.PIC_LECTURER_PATH);
        modelMap.addAttribute("uPath", Constant.PIC_USER_HEAD_PATH);
        //modelMap.addAttribute("ccPath", Constant.PIC_COURSE_COMMENT_PATH);

        return "home_user";
    }
}
