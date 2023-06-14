package com.aaa.charge;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @PROJECT_NAME: MedicalManagementPlatform
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MapperScan(basePackages = {"com.aaa.charge.dao"})
@EnableFeignClients(basePackages = "com.aaa.charge.fegin")
@EnableTransactionManagement
public class ChargeApp {
    public static void main(String[] args) {
        SpringApplication.run(ChargeApp.class, args);
    }
}
