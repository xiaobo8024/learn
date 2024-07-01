package com.txb.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@ServletComponentScan("com.txb.app.config")
//@EnableGlobalMethodSecurity
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
