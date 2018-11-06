package com.shmilyou.service.bo;
/* Created with 岂止是一丝涟漪     530060499@qq.com    2018/11/6 */

import com.shmilyou.entity.CourseComment;
import lombok.Data;

import java.util.List;

@Data
public class CourseCommentBO {

    private Integer star1;
    private Integer star2;
    private Integer star3;

    private List<CourseComment> comments;
}
