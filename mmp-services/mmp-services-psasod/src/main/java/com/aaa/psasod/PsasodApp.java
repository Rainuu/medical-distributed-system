package com.aaa.psasod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @PROJECT_NAME: MedicalManagementPlatform
 * @DESCRIPTION:
 * @USER: HZM
 * @DATE: 2023/3/22 13:27
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class PsasodApp {
    public static void main(String[] args) {
        SpringApplication.run(PsasodApp.class,args);
    }
}
