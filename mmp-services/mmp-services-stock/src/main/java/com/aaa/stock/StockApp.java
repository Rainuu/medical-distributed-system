package com.aaa.stock;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @PROJECT_NAME: MedicalManagementPlatform
 * @DESCRIPTION: stock模块启动类
 * @USER: HZM
 * @DATE: 2023/3/22 13:27
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class}) // 声明此类是SpringBoot的核心启动类
@MapperScan("com.aaa.stock.dao") // Mybatis: 配置扫描地址所有接口生成实现类
public class StockApp {
    public static void main(String[] args) {
        SpringApplication.run(StockApp.class,args);
    }
}
