package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import xyz.erupt.core.annotation.EruptScan;

@SpringBootApplication
@EntityScan
@EruptScan
public class EruptApplication {

    public static void main(String[] args) {

        SpringApplication.run(EruptApplication.class, args);

        /**
         * erupt / erupt 默认账号密码
         */
    }

}
