package com.shmilyou.web.controller;

import com.shmilyou.entity.OrganizationComment;
import com.shmilyou.service.OrganizationCommentService;
import com.shmilyou.web.resolver.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created with 岂止是一丝涟漪     530060499@qq.com    2018/10/24
 */

@Controller
@RequestMapping("/phisoa/organization/comment")
public class CommentController extends BaseController {

    @Autowired
    private OrganizationCommentService organizationCommentService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addComment(LoginUser loginUser, OrganizationComment comment) {
        if (StringUtils.isEmpty(comment.getComment())) {
            return "comment is null";
        }
        comment.setUserId(loginUser.getId());
        int raw = organizationCommentService.add(comment);
        if (raw <= 0) {
            return "评论失败";
        }
        return "ok";
    }
}
