package ru.itis.csc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class CityServiceClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(CityServiceClientApplication.class, args);
    }

}
