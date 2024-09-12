package com.xzro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.xzro.mapper")
public class EmployeeSystemSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeSystemSpringbootApplication.class, args);
    }

}
