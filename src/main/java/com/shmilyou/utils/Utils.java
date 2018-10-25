package com.shmilyou.utils;

import com.alibaba.fastjson.JSON;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with 岂止是一丝涟漪
 * Date: 2018/8/16
 */
public class Utils {

    private static Logger logger = LoggerFactory.getLogger(Utils.class);

    /**
     * 驼峰法转下划线
     *
     * @param line 源字符串
     * @return 转换后的字符串
     */
    public static String camel2Underline(String line) {
        if (line == null || "".equals(line)) {
            return "";
        }
        line = String.valueOf(line.charAt(0)).toUpperCase()
                .concat(line.substring(1));
        StringBuffer sb = new StringBuffer();
        Pattern pattern = Pattern.compile("[A-Z]([a-z\\d]+)?");
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            String word = matcher.group();
            sb.append(word.toLowerCase());
            sb.append(matcher.end() == line.length() ? "" : "_");
        }
        return sb.toString();
    }

    public static boolean isEmail(String emailStr) {
        if (StringUtils.isEmpty(emailStr)) return false;

        String regEx1 = "^([a-zA-Z0-9]+[_|\\_|\\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\\_|\\.]?)*[a-zA-Z0-9]+\\.[a-zA-Z]{2,3}$";
        Pattern p = Pattern.compile(regEx1);
        Matcher m = p.matcher(emailStr);
        return m.matches();

    }

    /**
     * 判断是否为整数
     *
     * @param str 传入的字符串
     * @return 是整数返回true, 否则返回false
     */

    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    /**
     * 生成订单号
     */
    public static String generateOrderNum() {
        //时间
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSS");
        String d = format.format(date);
        //随机数3位
        int random = (int) (Math.random() * 1000);
        return d + random;
    }

    /**
     * 利用Jackson解析json字符型数组
     *
     * @param json json数组, <b>NOTE:</b> ["1","2","3"]
     * @return
     */
    public static List<String> parseJsonArr(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        List list = Collections.emptyList();
        try {
            list = objectMapper.readValue(json, List.class);
        } catch (IOException e) {
            logger.error(e.getLocalizedMessage(), e);
        }
        return list;
    }

    /**
     * 利用fastJson生成json
     */
    public static String generateJson(Object obj) {
        if (obj == null) {
            return "";
        }
        return JSON.toJSONString(obj);
    }

}
