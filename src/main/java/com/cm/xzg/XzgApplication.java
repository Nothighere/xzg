package com.cm.xzg;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.cm.xzg.*")
@MapperScan( "com.cm.xzg.repository")
public class XzgApplication {

    public static void main(String[] args) {
        SpringApplication.run(XzgApplication.class, args);
    }

}
