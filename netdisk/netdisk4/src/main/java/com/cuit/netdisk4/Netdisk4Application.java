package com.cuit.netdisk4;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cuit.netdisk4.Dao") // 扫描DAO接口
public class Netdisk4Application {

    public static void main(String[] args) {
        SpringApplication.run(Netdisk4Application.class, args);
    }

}
