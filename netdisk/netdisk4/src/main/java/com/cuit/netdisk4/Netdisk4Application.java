package com.cuit.netdisk4;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cuit.netdisk4.Dao") // 扫描DAO接口
@OpenAPIDefinition(info = @io.swagger.v3.oas.annotations.info.Info(
        title = "网盘系统API",
        version = "1.0.0",
        description = "网盘系统接口文档"
))
@SecurityScheme(
        name = "BearerAuth",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class Netdisk4Application {

    public static void main(String[] args) {
        SpringApplication.run(Netdisk4Application.class, args);
    }

}
