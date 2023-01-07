package com.hiqgroup.hiq;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.hiqgroup.hiq.dao")
public class HiQApplication {

    public static void main(String[] args) {
        SpringApplication.run(HiQApplication.class, args);
    }

}
