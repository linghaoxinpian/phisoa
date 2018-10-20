package com.shmilyou.web.controller;

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

    //机构个人主页
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index() {
        //获取用户评价
        return "";
    }

    /**
     * 获取用户评价信息
     */
    public String getComments(String organizationId) {
        return null;
    }

    /**
     * 解析
     */
    private List<String> getPhotoAlbum(String jsonArray) {
        return null;
    }
}
