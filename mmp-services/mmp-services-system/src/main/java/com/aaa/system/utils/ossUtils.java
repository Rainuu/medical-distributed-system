package com.aaa.system.utils;

import org.springframework.beans.factory.InitializingBean;

public class ossUtils implements InitializingBean {

    private String endpoint="oss-cn-beijing.aliyuncs.com";

    private String accessKeyId="LTAI5tQ7h8HkuniQr2JEPrai";

    private String secret="dAhFfzng93jLW3mrZg9GdLoI7KDjra";

    private String bucket="liangmenglei";

    public static String EDNPOINT;
    public static String ACCESS_KEY_ID;
    public static String SECRECT;
    public static String BUCKET;

    @Override
    public void afterPropertiesSet() throws Exception {
        EDNPOINT=endpoint;
        ACCESS_KEY_ID=accessKeyId;
        SECRECT=secret;
        BUCKET=bucket;
    }
}
