package com.shmilyou.web.controller;

import com.shmilyou.entity.Amateur;
import com.shmilyou.entity.AmateurTag;
import com.shmilyou.entity.Area;
import com.shmilyou.entity.Category;
import com.shmilyou.entity.Organization;
import com.shmilyou.entity.OrganizationTag;
import com.shmilyou.entity.User;
import com.shmilyou.entity.UserTag;
import com.shmilyou.service.AmateurService;
import com.shmilyou.service.AreaService;
import com.shmilyou.service.CategoryService;
import com.shmilyou.service.OrganizationService;
import com.shmilyou.service.UserService;
import com.shmilyou.service.bo.AreaCode;
import com.shmilyou.utils.ConfigUtils;
import com.shmilyou.utils.WebUtils;
import com.shmilyou.web.controller.vo.AmateurVO;
import com.shmilyou.web.controller.vo.OrganizationVO;
import com.shmilyou.web.controller.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
    @Autowired
    private CategoryService categoryService;

    /** 发送邮件验证码 */
    @RequestMapping(value = "/verificationCode")
    @ResponseBody
    public ResponseEntity verificationCode(User user) {
        boolean isSend = false;
        String code = String.valueOf((int) (Math.random() * 100000));
        if (user != null && user.getEmail() != null) {
            isSend = WebUtils.sendVerificationCode(user.getEmail(), code);
        }
        return isSend ? WebUtils.ok(code) : WebUtils.error("发送失败");
    }

    @RequestMapping(value = "/organization", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity registerOrganization(OrganizationVO organizationVO) {
        //校验
        if (StringUtils.isEmpty(organizationVO.getName())) {
            return WebUtils.error("名称为空");
        }
        Organization organization = new Organization();
        BeanUtils.copyProperties(organizationVO, organization);
        // 【地区】处理
        //Area area = areaService.queryByFullName(organizationVO.getFullAreaName());
        //organization.setAreaId(area == null ? 0 : area.getAreaId());

        //注册机构
        int raw = organizationService.register(organization);

        //【标签】处理
        List<OrganizationTag> tags = new ArrayList<>();
        organizationVO.getTagIds().forEach(i -> tags.add(new OrganizationTag(null, organization.getId(), i)));
        organizationService.addOrganizationTag(tags);

        return WebUtils.ok("ok");
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
    public String registerUser(UserVO userVO, AreaCode areaCode, ModelMap modelMap) {
        //校验
        if (StringUtils.isEmpty(userVO.getEmail())) {
            //return WebUtils.error("邮箱为空");
            modelMap.addAttribute("u", userVO);
            return "register_user";
        }
        boolean existEmail = userService.existEmail(userVO.getEmail());
        if (ConfigUtils.IS_DEBUG) {
            existEmail = false;
        }
        if (existEmail) {
            //return WebUtils.error("该邮箱已注册");
            modelMap.addAttribute("u", userVO);
            return "register_user";
        }

        User user = new User();
        BeanUtils.copyProperties(userVO, user);
        user.setId(UUID.randomUUID().toString());
        // 【地区】处理
        String code = areaService.loadSelectAreaCode(areaCode);
        user.setAreaCode(code);
        //设置默认头像
        user.setHeadImg("default.jpg");

        //注册
        userService.register(user);

        //【标签】处理
        List<UserTag> tags = new ArrayList<>();
        if (userVO.getTagIds() != null) {
            userVO.getTagIds().forEach(i -> tags.add(new UserTag(null, user.getId(), i, UserTag.STRONG_TAG)));
        }
        userService.addUserTag(tags);

        //return WebUtils.ok("ok");
        return "index";
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
    public String registerUser(ModelMap modelMap) {
        List<Category> tags = categoryService.queryByLevel(Category.Level1);
        modelMap.addAttribute("tags", tags);
        return "register_user";
    }

}
