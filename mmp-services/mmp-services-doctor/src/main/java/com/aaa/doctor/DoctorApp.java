package com.aaa.doctor;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Service;

/**
 * @author 刘鸿飞
 * @version 1.0.0
 * @ProjectName medical-management-platform
 * @date 2023/3/24 15:23
 */
@SpringBootApplication
@MapperScan("com.aaa.doctor.dao")
@EnableFeignClients(basePackages = "com.aaa.doctor.feign")
public class DoctorApp {
    public static void main(String[] args) {
        SpringApplication.run(DoctorApp.class,args);
    }
}
