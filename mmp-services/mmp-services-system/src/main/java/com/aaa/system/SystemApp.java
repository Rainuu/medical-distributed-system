package com.aaa.system;

import com.aaa.core.entity.User;
import com.aaa.system.dao.UserDao;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

/**
 * @PROJECT_NAME: MedicalManagementPlatform
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MapperScan("com.aaa.system.dao")
public class SystemApp {
    @Resource
    private UserDao userDao;

    public static void main(String[] args) {
        SpringApplication.run(SystemApp.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CommandLineRunner createAdminAccount() {
        return (args) -> {
            // 检查是否已存在管理员账户
            User admin = userDao.selectById(1000000L);
            if (admin == null) {
                // 如果不存在管理员账户，创建一个新的管理员账户
                User newAdmin = new User();
                newAdmin.setUserName("admin");
                newAdmin.setUserId(1000000L);
                // 请注意：这里使用了一个简单的明文密码。实际项目中，请使用加密的密码。
                newAdmin.setPassword(passwordEncoder().encode("123456"));

                // 设置管理员角色，如ROLE_ADMIN
                // 在此之前，您需要在User类和数据库中实现与角色相关的逻辑

                userDao.insert(newAdmin);
                System.out.println("插入成功");
            }
        };
    }

}
