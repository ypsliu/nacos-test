package com.example.demo.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2021/12/14
 * Time: 17:32
 * Description: No Description
 */
@Slf4j
@Component
public class RunnerHelper implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        log.info("server start success,you can do something here^ ^");
    }
}
