package com.aaa.charge.util;


import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class MyAlipayUtil {

    @Value("${alipay.appid}")
    private String appid;
    @Value("${alipay.url}")
    private String url;
    @Value("${alipay.privateKey:DefaultValue}")
    private String privateKey;
    @Value("${alipay.publicKey:DefaultValue}")
    private String publicKey;
    @Value("${alipay.notifyUrl}")
    private String notifyUrl;
    @Value("${alipay.returnUrl}")
    private String returnUrl;



    public String pay(String  itemId, String itemName, String allAmount){
        AlipayClient alipayClient = new DefaultAlipayClient(url,appid,privateKey,"json","UTF-8",publicKey,"RSA2");
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
//异步接收地址，仅支持http/https，公网可访问
        request.setNotifyUrl(notifyUrl);
//同步跳转地址，仅支持http/https
        request.setReturnUrl(returnUrl);
/******必传参数******/
        JSONObject bizContent = new JSONObject();
//商户订单号，商家自定义，保持唯一性
        bizContent.put("out_trade_no", itemId);
//支付金额，最小值0.01元
        bizContent.put("total_amount", allAmount);
//订单标题，不可使用特殊符号
        bizContent.put("subject", itemName);
//电脑网站支付场景固定传值FAST_INSTANT_TRADE_PAY
        bizContent.put("product_code", "FAST_INSTANT_TRADE_PAY");
        request.setBizContent(bizContent.toString());
        AlipayTradePagePayResponse response = null;
        String form=null;
        try {
            response = alipayClient.pageExecute(request);
            form=response.getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        if(response.isSuccess()){
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
        }
        return form;
    }
    }
