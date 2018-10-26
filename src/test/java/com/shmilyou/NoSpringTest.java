package com.shmilyou;

import com.shmilyou.entity.User;
import com.shmilyou.utils.Utils;
import com.shmilyou.utils.WebUtils;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with 岂止是一丝涟漪     530060499@qq.com    2018/10/24
 */
public class NoSpringTest {
    @Test
    public void orderNum() {
        for (int i = 0; i < 10; i++) {
            Date date = new Date();
            DateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSS");
            String d = format.format(date);
            int random = (int) (Math.random() * 1000);
            System.out.println(random);
        }
    }

    @Test
    public void jacksonParse() {
        String json2 = "[\"北京\",\"天津\",\"杭州\"]";
        List<String> list = Utils.parseJsonArr(json2);
        System.out.println(list);

    }

    @Test
    public void jsonGenerate() {
        User user = new User();
        user.setId("ddd");
        user.setInterestList(new ArrayList() {
            {
                add("1");
                add("2");
            }
        });
        String s = Utils.generateJson(user);
        System.out.println(s);
    }

    @Test
    public void mkdir() {
        Utils.isExistDirectory("D:\\ProgramData\\idea_project\\phisoa\\target//index.php");
    }

    //邮件
    @Test
    public void javaxMail() throws Exception {
        WebUtils.sendQQEmail("1159171637@qq.com", "dlhpbctezbhlbabd", "530060499@qq.com", "w2u6");
    }

}
