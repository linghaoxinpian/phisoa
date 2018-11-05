package com.shmilyou.service.bo;

/* Created with 岂止是一丝涟漪     530060499@qq.com    2018/11/3 */

import lombok.Data;

@Data
public class AppIdAndOpenIdAndAccessToken {

    private String client_id;

    private String openid;

    private String access_token;
}
