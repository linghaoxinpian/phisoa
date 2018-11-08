package com.shmilyou.service.job;
/* Created with 岂止是一丝涟漪     530060499@qq.com    2018/11/8 */

import org.springframework.scheduling.annotation.Scheduled;

//@Component    暂不开启此定时任务
public class BaseSchedule {

    @Scheduled(cron = "0/30 * * * * ?")
    public void job1() {
        System.out.println("定时任务 " + System.currentTimeMillis());
    }
}
