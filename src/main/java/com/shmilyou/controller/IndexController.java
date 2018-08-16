package com.shmilyou.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with 岂止是一丝涟漪
 * Date: 2018/8/16
 */
@Controller
@RequestMapping("/phisoa")
public class IndexController extends BaseController {

    @RequestMapping(value = {"/index", "/", ""})
    public String index() {
        return "index";
    }
}
