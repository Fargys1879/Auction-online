package com.auction;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AuctionApp {
    public static ApplicationContext context;

    public static void main(String[] args) {
        try {
            context = new ClassPathXmlApplicationContext("applicationContext.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
