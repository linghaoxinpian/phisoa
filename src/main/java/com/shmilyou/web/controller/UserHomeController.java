package com.shmilyou.web.controller;

import com.shmilyou.entity.Course;
import com.shmilyou.entity.CourseComment;
import com.shmilyou.entity.CourseOrder;
import com.shmilyou.entity.User;
import com.shmilyou.entity.UserCollectCourse;
import com.shmilyou.service.AreaService;
import com.shmilyou.service.CourseCommentService;
import com.shmilyou.service.CourseOrderService;
import com.shmilyou.service.UserCollectCourseService;
import com.shmilyou.service.UserService;
import com.shmilyou.service.bo.AreaCode;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* Created with 岂止是一丝涟漪     530060499@qq.com    2018/10/31 */

@Controller
@RequestMapping("/phisoa/manager/user")
public class UserHomeController extends BaseController {

    @Autowired
    private UserService userService;
    @Autowired
    private CourseCommentService courseCommentService;
    @Autowired
    private CourseOrderService courseOrderService;
    @Autowired
    private AreaService areaService;
    @Autowired
    private UserCollectCourseService userCollectCourseService;

    /** 主页 */
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity index(LoginUser loginUser, ModelMap modelMap) {
        if (loginUser == null) {
            return WebUtils.error("未登录");
        }
        //获取用户
        User user = userService.queryById(loginUser.getId());
        user.setHeadImg(Constant.PIC_USER_HEAD_PATH + user.getId() + "/" + user.getHeadImg());
        //获取用户最新的课程评论
        List<CourseComment> newestCourseComment = courseCommentService.loadNewestCommentsByUserId(user.getId(), 0, 1);
        //解析相册
        if (!StringUtils.isEmpty(user.getPhotoAlbum())) {
            user.setParsedPhotoAlbums(Utils.parseJsonArr(user.getPhotoAlbum()));
        }

        modelMap.addAttribute("u", user);
        modelMap.addAttribute("newestCourseComment", newestCourseComment);

        Map<String, Object> map = new HashMap<>();
        map.put("u", user);
        map.put("newestCourseComment", newestCourseComment);

        return WebUtils.ok(map);
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
        modelMap.addAttribute("u", user);
        return "edit_user";
    }

    /** 更新基础信息 */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String updateUserInfo(LoginUser loginUser, UserVO userVO, AreaCode areaCode, ModelMap modelMap, HttpSession session) {
        if (loginUser == null) {
            return "error";
        }
        //1.获取
        User user = userService.queryById(loginUser.getId());
        //2.更新img
        if (userVO.getHeadImg() != null) {
            String path = session.getServletContext().getRealPath("/") + Constant.PIC_USER_HEAD_PATH + user.getId() + "/";
            String fileName = WebUtils.uploadPicture(userVO.getHeadImg(), path, Utils.generateDateNum());
            user.setHeadImg(fileName);
        }
        //3.更新地区
        String code = areaService.loadSelectAreaCode(areaCode);
        user.setAreaCode(code.length() > 0 ? code : user.getAreaCode());
        //4.更新其它文本数据
        user.setName(userVO.getName());
        user.setPhone(userVO.getPhone());
        //5.同步至数据库
        userService.update(user);

        return "redirect:/phisoa/manager/user/";
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
    public ResponseEntity removeOrder(LoginUser loginUser, String orderId) {
        if (loginUser == null) {
            return WebUtils.error("请先登录");
        }
        if (!StringUtils.isEmpty(orderId)) {
            //删除
            int row = courseOrderService.deleteByUserIdAndOrderId(loginUser.getId(), orderId);
            if (row > 0) {
                return WebUtils.ok();
            }
        }
        return WebUtils.error("删除失败");
    }

    /** 相册管理 */
    @RequestMapping(value = "/show/photos", method = RequestMethod.GET)
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

    /** 收藏管理 */
    @RequestMapping(value = "/collect/courses", method = RequestMethod.GET)
    public String collectCourses(LoginUser loginUser, ModelMap modelMap) {
        List<UserCollectCourse> collects = userCollectCourseService.queryByUserId(loginUser.getId());

        modelMap.addAttribute("collects", collects);

        return "collect_courses";
    }

    /** 删除收藏 */
    @RequestMapping(value = "/collect/rm", method = RequestMethod.GET)
    public String rmCollectCourse(LoginUser loginUser, String collectId) {
        UserCollectCourse collect = userCollectCourseService.removeCollectCourseById(loginUser.getId(), collectId);
        if (collect != null) {
            return "redirect:/phisoa/manager/user/collect/courses";
        }
        return "error";
    }
}
