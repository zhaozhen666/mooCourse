package com.zhao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.zhao.mapper")
public class FoodApplication {
    public static void main(String[] args) {
        SpringApplication.run(FoodApplication.class,args);
    }
}
