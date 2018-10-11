package com.shmilyou.web.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with 岂止是一丝涟漪     530060499@qq.com    2018/10/12
 */
@RequestMapping("/phisoa/pay")
@Controller
public class PayController extends BaseController {

    //支付宝网页支付
    @RequestMapping(value = "/ali", method = RequestMethod.GET)
    public void alipayPage(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
        String APP_PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCqjsGvhNkftwsnZOOqiqQBAU3utw8xKtoKYp4D919tN3Q2YeluLK8n+vQaS6gXspSVtHnF6sBQiYYtt2YYEATTeBZ/wzrjadyAYu8me9Lw58vse5mXVBgecvOhVIlGe0Vt/bwY86mYU+FG4UM1BYGY1vwb7vHORD9ao7pe/A8TlLCtVclPvkL+zN9T0vYMwTvWTkoD9V9/3G2SRWDUEPSnu2MAAp0bRl7c9OqM63C9g/L5BwO72Qgy5C9D3bGMAJKuE2zhS3+2EYT2sorcySI+xNXb+ingzDRQ/HXKymeHMKPhAtN8MwiIuCEUnhYYUctF4arLJNby2FWdZExcLyLlAgMBAAECggEAaKUiLSCjXxQdodQC45YgvgUg3cAvcAlMsmaDcQeL4yTNBrjYUiLUbQFKV//naHLHO3r54/b+e+uFCfOmjkj1zRG3LDiKnxFKmvMYrFH4wJpXwgar2+9axQPeezO07iugE7ZEfEKz4aRFagm4BIYWV8I/+JuIfPferywZo0GftnDYSGRBTUWK5SgzZejgNtdJUUnVkHSfkWLFJny7xVuIC8CnfWDoSDi+EuJTbGzCP4Tet22rM+aVT1JcBc7QRvfcPB/qHDq6jF4luGCyvHsVPsvKtguehIdf80W3Nb06ZobRvfwA/l5JBshtVXjW5YTV73E10BR9EhDywNcMVz3/AQKBgQDqNqlocsynZ2Zfrwp4FnQHZ9lrwKVC/5JXdserz7H+W5NkBQnhzjFmvZoE2EpdBAj/2hh0XE3xTSajkHBurzZRzv4oRO8nsfR1AGJZhxrkXC3gUpjJMlxaqDXk8+JrxgZ5pXweG6u95lAnSfic8kIX9KKyjMX2ZXPxPgKJx3Lc4QKBgQC6bEET+7onYlweGCTOfQWA4K2j0OEAb+zWjFh/yrt/aaEHiW11ZtJivVHrIAg8m+GdlyFfwQRW65mKYCWMKylmjIU3ZJJKCnNAoz1QqdkQqGE9uM8sCqatFtFSfRv1kVtQL1eaM8AmHyTBtyacSjZjIovU7Sgaw8Z7qbGdaTyihQKBgGcZcs9goYgbcywxkGk2cRMFeub84YKxoAjSZ6jQ/6hZRXNk6Gm263YvhkoVr/5sFU4TAmrOax0KejTME0IV7NJsTOMLHT+bDWInriN83rChhyxVD56ZhZ/+peLIHOaWQClyWEWkpqAsbyIsZL0pw0BqVuhEIayKElSimFlwK7bhAoGBAIvpZ0Y6LNIWLhCgCi+3AQRim69Quy160wOKvXjbjLDpc+OWLsJn8woMEIkot0XVsBR4MqIM2NbrZ4bE9ikm06GVYP3byzcqdKlUWlZJwxGPbbvHEiV5sPJDJ8KDBxLcju21OkgPAu0ZfpqsfJvbzsnESbH6c5jyyepRx23eZ5WJAoGBAOPTaueDnQGxYWdRxnYL+jhqL1Ih4r5PTsdGZ1SJCu+fbdtS2zUEq7sLvZBJMBKP3YXQkZqV/RvNzENCaFZSszwE1wSoyZKFUnM+HlTEOC4hazWzY0VdFnDMxQ64Uiw9i9aWOrFp9fhdF3qKDzCOZjMMbGPkPQGE72lpPX61Yx4h";
        String appId = "2016092100560352";
        String FORMAT = "json";
        String CHARSET = "UTF-8";
        String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAxx4ShnPL2IBoYL1oPtz8PEQQCQOjoEVs8A1lU2PZgzEeMu+nugVtQKmVloDHCMK1/owvT7UAmc6mKSOGbM2p7DVbyc9JsbyzVDIkb2imesMq5vTam89zRXJH4uie9DOVt/WYdrjyJngLUi/BfUSdiYBLxpoktkfsrDlNzQ0Scxgqq4stp8cYF32qN4pHvyXo7e/8r4HmNyG8HgSIS1addknUQM5c3/phmvmqRhRntR/0CWyctpWDplHDbiAI1I+EdTUA/9rQ0f397F0R9upLrCQgpGfzdCw4Yi0cz0sra6bZzChfISvndCJNnNxSfmCXy2c1ODvZukva+16pjfq9fQIDAQAB";
        String SIGN_TYPE = "RSA2";
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipaydev.com/gateway.do\n", appId,
                APP_PRIVATE_KEY, FORMAT, CHARSET, ALIPAY_PUBLIC_KEY, SIGN_TYPE); //获得初始化的AlipayClient
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();//创建API对应的request
        alipayRequest.setReturnUrl("http://domain.com/CallBack/return_url.jsp");
        alipayRequest.setNotifyUrl("http://domain.com/CallBack/notify_url.jsp");//在公共参数中设置回跳和通知地址
        alipayRequest.setBizContent("{" +
                "    \"out_trade_no\":\"20150320010101001\"," +
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
                "    \"total_amount\":88.88," +
                "    \"subject\":\"Iphone6 16G\"," +
                "    \"body\":\"Iphone6 16G\"," +
                "    \"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\"," +
                "    \"extend_params\":{" +
                "    \"sys_service_provider_id\":\"2088511833207846\"" +
                "    }" +
                "  }");//填充业务参数
        String form = "";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        httpResponse.setContentType("text/html;charset=" + CHARSET);
        try {
            httpResponse.getWriter().write(form);//直接将完整的表单html输出到页面
            httpResponse.getWriter().flush();
            httpResponse.getWriter().close();
        } catch (IOException e) {
            logger.error("支付宝网页支付-写入页面出错！！！");
            logger.error(e.getLocalizedMessage(), e);
        }
    }

}
