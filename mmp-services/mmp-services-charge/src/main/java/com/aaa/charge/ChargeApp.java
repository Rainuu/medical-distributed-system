package com.aaa.charge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @PROJECT_NAME: MedicalManagementPlatform
 * @DESCRIPTION:
 * @USER: HZM
 * @DATE: 2023/3/22 13:26
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ChargeApp {
    public static void main(String[] args) {
        SpringApplication.run(ChargeApp.class,args);
    }
}
