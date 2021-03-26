package com.auction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
public class AuctionApp {
    public static void main(String[] args) {
        SpringApplication.run(AuctionApp.class,args);
    }
}
