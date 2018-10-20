package com.shmilyou.service.bo;

import com.shmilyou.entity.OrganizationComment;
import lombok.Data;

import java.util.List;

/**
 * Created with 岂止是一丝涟漪     530060499@qq.com    2018/10/20
 */
@Data
public class OrganizationCommentBO {

    /**
     * 六边形打分--begin
     */
    //信誉度
    private Integer creditScore;
    //环境
    private Integer environmentScore;
    //师资
    private Integer facultyScore;
    //效果
    private Integer effectScore;
    //认证等级->见level字段
    //满意度
    private Integer satisfactionScore;
    //六边形打分--end

    List<OrganizationComment> comments;
}
