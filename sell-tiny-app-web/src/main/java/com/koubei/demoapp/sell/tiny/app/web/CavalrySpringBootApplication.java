package com.koubei.demoapp.sell.tiny.app.web;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Slite2WebSpringBootApplication
 * <p/>
 * Created by yangguanchao on 16/7/22.
 */
@EnableScheduling
@ImportResource({"classpath*:META-INF/sell-tiny-app/*.xml", "classpath*:META-INF/cavalry/core/*.xml"})
@SpringBootApplication(
        scanBasePackages = {"com.koubei.cavalry", "com.koubei.demoapp.sell.tiny.app"},
        exclude = {
                DataSourceAutoConfiguration.class
//                DispatcherServletAutoConfiguration.class
        })
public class CavalrySpringBootApplication {

    // 在Java类中创建 logger 实例
    private static final Logger logger = LoggerFactory.getLogger(CavalrySpringBootApplication.class);

    public static void main(String[] args) throws Exception {
        SpringApplication springApplication = new SpringApplication(CavalrySpringBootApplication.class);
        ApplicationContext applicationContext = springApplication.run(args);
    }

}