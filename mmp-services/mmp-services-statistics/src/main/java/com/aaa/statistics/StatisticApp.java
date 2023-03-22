package com.aaa.statistics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @PROJECT_NAME: MedicalManagementPlatform
 * @DESCRIPTION:
 * @USER: HZM
 * @DATE: 2023/3/22 13:28
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class StatisticApp {
    public static void main(String[] args) {
        SpringApplication.run(StatisticApp.class,args);
    }
}
