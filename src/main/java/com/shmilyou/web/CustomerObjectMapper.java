package com.shmilyou.web;
/* Created with 岂止是一丝涟漪     530060499@qq.com    2018/11/8 */

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class CustomerObjectMapper extends ObjectMapper {

    public CustomerObjectMapper() {
        this.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        // 设置 SerializationFeature.FAIL_ON_EMPTY_BEANS 为 false
        this.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }
}
