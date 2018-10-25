package com.shmilyou.web.controller;

import com.shmilyou.entity.Amateur;
import com.shmilyou.entity.AmateurTag;
import com.shmilyou.entity.Area;
import com.shmilyou.entity.Organization;
import com.shmilyou.entity.OrganizationTag;
import com.shmilyou.entity.User;
import com.shmilyou.entity.UserTag;
import com.shmilyou.service.AmateurService;
import com.shmilyou.service.AreaService;
import com.shmilyou.service.OrganizationService;
import com.shmilyou.service.UserService;
import com.shmilyou.utils.WebUtils;
import com.shmilyou.web.controller.vo.AmateurVO;
import com.shmilyou.web.controller.vo.OrganizationVO;
import com.shmilyou.web.controller.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

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
    private UserService userService;
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
        // 【地区】处理
        Area area = areaService.queryByFullName(organizationVO.getFullAreaName());
        organization.setAreaId(area == null ? 0 : area.getAreaId());

        //注册机构
        int raw = organizationService.register(organization);

        //【标签】处理
        List<OrganizationTag> tags = new ArrayList<>();
        organizationVO.getTagIds().forEach(i -> tags.add(new OrganizationTag(null, organization.getId(), i)));
        organizationService.addOrganizationTag(tags);

        return "{id:1,name:2}";
    }

    @RequestMapping(value = "/amateur", method = RequestMethod.POST)
    @ResponseBody
    public String registerAmateur(AmateurVO amateurVO) {
        //校验
        if (StringUtils.isEmpty(amateurVO.getName())) {
            return "no";
        }
        Amateur amateur = new Amateur();
        BeanUtils.copyProperties(amateurVO, amateur);
        // 【地区】处理
        Area area = areaService.queryByFullName(amateurVO.getFullAreaName());
        amateur.setAreaId(area == null ? 0 : area.getAreaId());

        //注册爱好者
        int raw = amateurService.register(amateur);

        //【标签】处理
        List<AmateurTag> tags = new ArrayList<>();
        amateurVO.getTagIds().forEach(i -> tags.add(new AmateurTag(null, amateur.getId(), i)));
        amateurService.addAmateurTag(tags);
        return "{id:1,name:2}";
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity registerUser(UserVO userVO) {
        //校验
        if (StringUtils.isEmpty(userVO.getName())) {
            return WebUtils.error("登录名为空");
        }
        User user = new User();
        BeanUtils.copyProperties(userVO, user);
        // 【地区】处理
        Area area = areaService.queryByFullName(userVO.getFullAreaName());
        user.setAreaId(area == null ? 0 : area.getAreaId());

        //注册
        userService.register(user);

        //【标签】处理
        List<UserTag> tags = new ArrayList<>();
        if (userVO.getTagIds() != null) {
            userVO.getTagIds().forEach(i -> tags.add(new UserTag(null, user.getId(), i, UserTag.STRONG_TAG)));
        }
        userService.addUserTag(tags);
        return WebUtils.ok("ok");
    }


    //--------------------- GET ---------------------
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
