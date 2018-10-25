package com.shmilyou.web.controller;

import com.shmilyou.entity.Lecturer;
import com.shmilyou.service.LecturerService;
import com.shmilyou.utils.Constant;
import com.shmilyou.utils.WebUtils;
import com.shmilyou.web.controller.vo.LecturerVO;
import com.shmilyou.web.resolver.LoginOrganization;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * Created with 岂止是一丝涟漪     530060499@qq.com    2018/10/24
 */
@Controller
@RequestMapping("/phisoa/lecturer")
public class LecturerController extends BaseController {

    @Autowired
    private LecturerService lecturerService;

    //添加讲师
    @RequestMapping(value = "/organization/add", method = RequestMethod.POST)
    public String addLecturer(LoginOrganization loginOrganization, LecturerVO lecturerVO, HttpSession session) {
        if (StringUtils.isEmpty(lecturerVO.getName())) {
            return "name is null";
        }
        Lecturer lecturer = new Lecturer();
        BeanUtils.copyProperties(lecturerVO, lecturer);
        lecturer.setOrganizationId(loginOrganization.getId());
        lecturer.setId(UUID.randomUUID().toString());
        //设置图片名
        String saveFileName = "selfie";
        //指定图片路径
        String path = session.getServletContext().getRealPath("/") + Constant.PIC_LECTURER_PATH + lecturer.getId() + "/";
        //保存图片
        WebUtils.uploadPicture(lecturerVO.getSelfie(), path, saveFileName);

        //新增讲师
        int raw = lecturerService.insert(lecturer);
        if (raw <= 0) {
            return "添加失败";
        }
        return "ok";

    }

    //--------------------- GET ---------------------

    @RequestMapping(value = "/organization/add", method = RequestMethod.GET)
    public String addLecturer(LoginOrganization loginOrganization) {
        if (loginOrganization == null) {
            return "非机构禁止访问";
        }
        return "add_lecturer";
    }
}
