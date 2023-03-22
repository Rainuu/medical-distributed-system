package com.aaa.seeadoctor;

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
public class SeeadoctorApp {
    public static void main(String[] args) {
        SpringApplication.run(SeeadoctorApp.class,args);
    }
}
