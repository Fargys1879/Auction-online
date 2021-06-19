package com.auction.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(value = "com.auction")
@Import({HibernateConf.class,SpringWebConfig.class})
public class AppConfig {

}
