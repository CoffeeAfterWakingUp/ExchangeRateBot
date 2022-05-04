package com.example.exchangeratebot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ExchangeRateBotApplication {

    public static void main(String[] args) {
        System.setProperty("https.protocols", "TLSv1.2,TLSv1.1,TLSv1");
        System.setProperty("javax.net.ssl.debug", "all");
        SpringApplication.run(ExchangeRateBotApplication.class, args);
    }

}
