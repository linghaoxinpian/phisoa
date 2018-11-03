package com.shmilyou.web.controller;

import com.shmilyou.entity.Course;
import com.shmilyou.entity.CourseComment;
import com.shmilyou.entity.CourseOrder;
import com.shmilyou.entity.User;
import com.shmilyou.service.CourseCommentService;
import com.shmilyou.service.CourseOrderService;
import com.shmilyou.service.UserService;
import com.shmilyou.utils.Constant;
import com.shmilyou.utils.Utils;
import com.shmilyou.utils.WebUtils;
import com.shmilyou.web.controller.vo.UserVO;
import com.shmilyou.web.resolver.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/** Created with 岂止是一丝涟漪     530060499@qq.com    2018/10/31 */
@Controller
@RequestMapping("/phisoa/manager/user")
public class UserHomeController extends BaseController {

    @Autowired
    private UserService userService;
    @Autowired
    private CourseCommentService courseCommentService;
    @Autowired
    private CourseOrderService courseOrderService;

    /** 主页 */
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

    /** 基础信息管理 */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String showUserInfo(LoginUser loginUser, ModelMap modelMap) {
        if (loginUser == null) {
            return "error";
        }
        //获取用户
        User user = userService.queryById(loginUser.getId());

        //
        modelMap.addAttribute("o", user);
        return "edit_user";
    }

    /** 更新基础信息 */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String updateUserInfo(LoginUser loginUser, UserVO userVO, ModelMap modelMap, HttpSession session) {
        if (loginUser == null) {
            return "error";
        }
        //获取用户
        User user = userService.queryById(loginUser.getId());
        //指定图片路径
        String path = session.getServletContext().getRealPath("/") + Constant.PIC_USER_HEAD_PATH + user.getId() + "/";
        //保存图片
        String fileName = WebUtils.uploadPicture(userVO.getHeadImg(), path, Utils.generateDateNum());
        user.setHeadImg(fileName);

        //
        modelMap.addAttribute("o", user);
        return "home_user";
    }

    /** 订单管理 */
    @RequestMapping(value = "/show/orders", method = RequestMethod.GET)
    public String showOrders(LoginUser loginUser, ModelMap modelMap) {
        if (loginUser == null) {
            return "error";
        }
        List<CourseOrder> orders = courseOrderService.loadNewestByUserId(loginUser.getId(), 0, 10);
        //处理订单中的图片地址
        orders.forEach(o -> {
            Course course = o.getCourse();
            if (course != null) {
                course.setPicUrl(Constant.PIC_COURSE_PATH + course.getId() + course.getPicUrl());
            } else {
                course.setPicUrl(Constant.PIC_COURSE_PATH + "default.jpg");
            }
        });

        modelMap.addAttribute("orders", orders);
        return "show_user_orders";
    }

    /** 删除订单 */
    @RequestMapping(value = "/rm/order", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity removeOrder(LoginUser loginUser, @RequestBody String orderId) {
        if (loginUser == null) {
            return WebUtils.error("请先登录");
        }
        //删除
        int row = courseOrderService.delete(orderId);
        if (row > 0) {
            return WebUtils.ok();
        }
        return WebUtils.error("删除失败");
    }

    /** 相册管理 */
    @RequestMapping(value = "show/photos", method = RequestMethod.GET)
    public String showPhotos(LoginUser loginUser, ModelMap modelMap) {
        if (loginUser == null) {
            return "error";
        }
        //获取用户
        User user = userService.queryById(loginUser.getId());
        //解析相册
        if (!StringUtils.isEmpty(user.getPhotoAlbum())) {
            user.setParsedPhotoAlbums(Utils.parseJsonArr(user.getPhotoAlbum()));
        }


        modelMap.addAttribute("u", user);

        return "show_user_photos";
    }
}
