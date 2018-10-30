package com.shmilyou.web.controller;

import com.shmilyou.entity.Area;
import com.shmilyou.service.AreaService;
import com.shmilyou.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/** Created with 岂止是一丝涟漪     530060499@qq.com    2018/10/30 */

@Controller
@RequestMapping("/phisoa/area")
public class AreaController extends BaseController {

    @Autowired
    private AreaService areaService;

    /** 根据父级地区获取下级地区的接口 */
    @RequestMapping(value = "/getChildAreas", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity getChildAreas(@RequestBody Integer parentId) {
        if (parentId == null) {
            return WebUtils.error("上级地区不能为空");
        }
        List<Area> areas = areaService.queryByParentId(parentId);
        return WebUtils.ok(areas);
    }
}
