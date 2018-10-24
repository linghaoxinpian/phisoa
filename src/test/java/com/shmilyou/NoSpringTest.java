package com.shmilyou;

import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
}
