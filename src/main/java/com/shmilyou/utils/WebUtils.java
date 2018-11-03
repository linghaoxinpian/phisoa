package com.shmilyou.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with 岂止是一丝涟漪     530060499@qq.com    2018/10/25
 */
public class WebUtils {

    private static Logger logger = LoggerFactory.getLogger(WebUtils.class);

    /**
     * 保存上传的图片
     *
     * @param path     目录
     * @param fileName 保存时的文件名,不带后缀
     * @return 带后缀的文件名
     */
    public static String uploadPicture(MultipartFile pic, String path, String fileName) {
        if (pic != null && pic.getSize() > 0) {
            String picName = pic.getOriginalFilename();
            String picType = picName.substring(picName.lastIndexOf("."));
            if (".png".equals(picType) || ".jpg".equals(picType) || ".jpeg".equals(picType)) {
                //检查目录是否存在
                Utils.isExistDirectory(path);
                try {
                    //存储图片
                    pic.transferTo(new File(path + fileName + picType));
                    return fileName + picType;
                } catch (IOException e) {
                    logger.error(e.getLocalizedMessage(), e);
                }
            }
        }
        return "";
    }

    /** 返回状态码200，以及json数据 */
    public static ResponseEntity<Map<String, Object>> ok(Object msg) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", 200);
        map.put("msg", msg);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    /** 返回状态码200 */
    public static ResponseEntity<Map<String, Object>> ok() {
        Map<String, Object> map = new HashMap<>();
        map.put("status", 200);
        map.put("msg", "ok");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    /** 返回状态码400，以及json数据 */
    public static ResponseEntity<Map<String, Object>> error(Object msg) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 400);
        map.put("msg", msg);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    /** 发送验证码 */
    public static boolean sendVerificationCode(String reviceEmail, String code) {
        return Utils.sendQQEmail(Constant.SEND_ACCOUNT_EMAIL, Constant.SEND_ACCOUNT_PASSWORD, reviceEmail, code);
    }
}
