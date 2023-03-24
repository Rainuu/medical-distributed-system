package com.aaa.check;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @PROJECT_NAME: MedicalManagementPlatform
 * @DESCRIPTION:
 * @USER: HZM
 * @DATE: 2023/3/22 13:26
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.aaa.check.dao"})
public class CheckApp {
    public static void main(String[] args) {
        SpringApplication.run(CheckApp.class,args);
    }
}
