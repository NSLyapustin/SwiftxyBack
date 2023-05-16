package com.example.swiftxyback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@EntityScan("com.example.swiftxyback.model")
public class SwiftxyBackApplication {
    public static void main(String[] args) {
        SpringApplication.run(SwiftxyBackApplication.class, args);
    }
}
