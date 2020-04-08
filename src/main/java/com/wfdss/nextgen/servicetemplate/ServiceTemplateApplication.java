package com.wfdss.nextgen.servicetemplate;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class ServiceTemplateApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ServiceTemplateApplication.class, args);
    }

    @Override
    public void run(String... args) {
        log.info("This is how we log!");
    }
}
