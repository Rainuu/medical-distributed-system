package com.aaa.charge.util;

import com.aaa.charge.config.AlipayConfig;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;

/**
 */
public class MyAlipayUtil {
    /**
     * @param out_trade_no: 订单编号 唯一
     * @param total_amount: 商品/支付 价格
     * @param subject:      主题
     * @return {@link ：java.lang.String}
     * @description
     * @author Benjamin Yang @date 2022/3/6 19:29:01
     * @Throws
     */
    public static String createOrderForm(String out_trade_no, String total_amount, String subject) throws AlipayApiException {
        DefaultAlipayClient alipayClient = new DefaultAlipayClient(
                AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key,
                "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        request.setNotifyUrl(AlipayConfig.notify_url);
        request.setReturnUrl(AlipayConfig.return_url);
        JSONObject bizContent = new JSONObject();
        bizContent.put("out_trade_no", out_trade_no);
        bizContent.put("total_amount", total_amount);
        bizContent.put("subject", subject);
        bizContent.put("product_code", "FAST_INSTANT_TRADE_PAY");
        request.setBizContent(bizContent.toJSONString());
        AlipayTradePagePayResponse response = alipayClient.pageExecute(request);
        String body = response.getBody();
        return body;
    }

}