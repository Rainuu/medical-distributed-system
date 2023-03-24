package com.aaa.charge;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @PROJECT_NAME: MedicalManagementPlatform
 * @DESCRIPTION:
 * @USER: HZM
 * @DATE: 2023/3/22 13:26
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MapperScan(basePackages = {"com.aaa.charge.dao"})
public class ChargeApp {
    public static void main(String[] args) {
        SpringApplication.run(ChargeApp.class,args);
    }
}
