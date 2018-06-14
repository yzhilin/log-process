package com.egaga;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class},scanBasePackages = "com.egaga")
@EnableScheduling
public class LogProcessApplication {

    public static void main(String[] args) {
        SpringApplication.run(LogProcessApplication.class, args);
    }
}
