package com.shmilyou.web.controller.vo;

/* Created with 岂止是一丝涟漪     530060499@qq.com    2018/11/3 */

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedList;

@Data
public class CourseCommentVO {

    private String comment;

    private LinkedList<MultipartFile> pics;

    /** 是否匿名 */
    private boolean anonymous;

    private Integer star;

    private String courseId;

}
