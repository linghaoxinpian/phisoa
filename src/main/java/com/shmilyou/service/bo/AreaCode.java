package com.shmilyou.service.bo;

import lombok.Data;

/** Created with 岂止是一丝涟漪     530060499@qq.com    2018/11/1 */

/** 存储省市县级的编码 */
@Data
public class AreaCode {

    /** 地区编码 */
    private String province;
    private String city;
    private String district;
}
