package cn.itzxy.abb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("cn.itzxy.abb.mapper")
@SpringBootApplication
public class AbbApplication {

    public static void main(String[] args) {
        SpringApplication.run(AbbApplication.class, args);
    }

}
