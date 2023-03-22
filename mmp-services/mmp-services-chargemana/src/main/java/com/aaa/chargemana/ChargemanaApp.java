package com.aaa.chargemana;

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
public class ChargemanaApp {
    public static void main(String[] args) {
        SpringApplication.run(ChargemanaApp.class,args);
    }
}
