package com.aaa.charge.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {

//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2021000122668848";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC3DhtT1WPG0EFwswediSjp2dqFes1hA+LM8I5DJJrRvXfI5HySZG8RxL73fc+jBBZDL19MK/3MWq2AdbOv8K0ffHSRxryRT1ahaV0ozJfr/VmMSvvtPEUhCwbgSiCwvCP/DIC5CyeWP39bT/7amz5erFlG1fV2ZKV6QkeG+vFwnAW+LXsSHL0g8vNsycBRTv2fId9x0VOSczkvJQFyqj1N2jrTK8P9KGQR04dBd5ek1nuYH4DAkoWyG6dAUbYBKR/0G3su8IXCOySAl+r+yB412A7As7d5osMsWO1BSgTvqxOKHCN6w4pmgNOH/LFGqegG7nw6+7WLeqR4cL9yy1kPAgMBAAECggEAM1aVe3GBRiTp9X2ASUJKYytiCcVjDC5W0Ts16sa9VM1aZtENztC0Bbu0Ax0ZupJO3BDRHT/JMiLMPhbEijdplkcFOk0OXYJuvV4bbnHSubCYCKyO8v3QNXAuQYEIoghh4AnVoA/szkqIoUdSJmUbnNmhQCiJaubXMe6cuNJwHDBrSLdd5wTSBjfxwFsXJV5hCM7sRDMWHKfU2QBc7ipH4u18ViUIo5mmNI0LIxW/g+pZYV53RcmRXybSepI83r1E68FZmN9iekJimwUJ/i5/vbNzPO/7AreXWhUbGRUUbqSWfulNjfLAcT6IPFJWTduoHg/gZwcOqhBzx+62fmjjAQKBgQD2f0oJinQUCNxnhREHGWq61Ernc62ALFrKMAX2GjZhKFwiotDe7lMNCp4irj52iDIGFT2UkauHjwXPi9NxxHMF0UDksXs5kA11hgWkm6iTUVJclFi1Z5Z7HzToszy9Gjvs4DiPM0sy0Xh29MuEmMDKJhBt7yMqHQNu83F4OBce4QKBgQC+HLMao/qLMJMCWKO6DDEOJ7aj7zfLYTirmbUNQp/J6VwCMABvKTWWHrrURv7t1T+Z1wLIvxM9hzLKXswMn0sG5qcI5J/fCCi2iUGzQr9MxSrQwRBMTEmJYQmmM+f82ScZPmwDYXvRl9UkGSU3Jo6cmdVxFxfsE9VWfwBNkJ8l7wKBgAkSbjsjqVoDaXk14IaOXOs7XPZfv4b0GvNOCSohlNbGkX2E2IMb6lqUhXVpTEs4f/7RUU7ifZmjnechkkqhnEoehXDlR0+7CYPFxvdj8zRYzdsyaoFiqpslbuERl66uBH6XUf2TObVJGFHoeIUMyiumcvxYzMaEAthWhVqTVeaBAoGBAIlwUB87Wv7TcAQrNrpi4/f6x/Q+FdUHhSOxrI1YlBIxjZ3bPAUhAqdzsdeTN+uS1jcOlOmyEWPS4OoinFunsWLczvcD6Xwg9uX31rKU153VkL14RaMTJ7qUl5g7QESwbK64TPgouo3suV8uepQjYMhgAkmOy3GVaQ7uI7bvjc6rAoGADDoofnaTp+i6sKVjsIy5MdaBlCnJs8erMaBP7j6L0PEM6ehLNJ3C6zdPCVkx/I47FQo24OgRKvArlsKjFmgIOCrtuCV2NNdyiVsjKF9dZBZhDHPDwcWs4ouy5MdkQu6M1Tg7tvtweir5DUextxQDLXyhFfpPzf2pz2fEufnk3K4=";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA8B6ootRt8N5Q1PGqk1eYe4yv/CELckpFTzoyxc9RutC9k0Qg7i17QsYYHJR74eorcuaMwUZJI9yG4a3d+B89WImNtjuycwmgVOWHpS5k0ZYurR4MtqAsF50Nthm3SOFuxEq2dHryv1ZX+HYKOGFoTVq54Q7jyo/l9y1RgagWQbAMGLUMhBb8rCtZKKjFajnLtg4I9lN9fCqOJILkDF/lpf/6hV5c2hWp3VFD4KYlUX1ufCsa+GX1drrDzWTd+w1jYS6HzH+0eywiG54l53XOTy/MsegZ2iUWkGYRfdhai+sNr2SIj8BbiTRIocxrMm6ori2gImf1HdzgwzKuBwQaHwIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://localhost:8090/alipay/pay";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://zzsimm.natappfree.cc/alipay/return";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     *
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis() + ".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
