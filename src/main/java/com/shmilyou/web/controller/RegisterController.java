package com.shmilyou.web.controller;

import com.shmilyou.entity.Amateur;
import com.shmilyou.entity.Area;
import com.shmilyou.entity.Organization;
import com.shmilyou.entity.User;
import com.shmilyou.service.AmateurService;
import com.shmilyou.service.AreaService;
import com.shmilyou.service.OrganizationService;
import com.shmilyou.web.controller.vo.OrganizationVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with 岂止是一丝涟漪     530060499@qq.com    2018/10/4
 */
@Controller
@RequestMapping(value = "/phisoa/register")
public class RegisterController extends BaseController {

    @Autowired
    private AmateurService amateurService;
    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private AreaService areaService;

    @RequestMapping(value = "/organization", method = RequestMethod.POST)
    @ResponseBody
    public String registerOrganization(OrganizationVO organizationVO) {
        //校验
        if (StringUtils.isEmpty(organizationVO.getName())) {
            return "no";
        }
        Organization organization = new Organization();
        BeanUtils.copyProperties(organizationVO, organization);
        // 【标签】【地区】处理
        Area area = areaService.queryByFullName(organizationVO.getFullAreaName());
        organization.setAreaId(area == null ? 0 : area.getAreaId());

        //注册
        int raw = organizationService.register(organization);
        Organization organization1 = organizationService.queryById(organization.getId());

        return "{id:1,name:2}";
    }

    @RequestMapping(value = "/amateur", method = RequestMethod.POST)
    @ResponseBody
    public String registerAmateur(Amateur amateur) {
        return "{id:1,name:2}";
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    @ResponseBody
    public String registerUser(User user) {
        return "{id:1,name:2}";
    }

    @RequestMapping(value = "/organization", method = RequestMethod.GET)
    public String registerOrganization() {
        return "register_organization";
    }

    @RequestMapping(value = "/amateur", method = RequestMethod.GET)
    public String registerAmateur() {
        return "register_amateur";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String registerUser() {
        return "register_user";
    }
}
