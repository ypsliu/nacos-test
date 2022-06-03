package com.demo.provider.util;

import com.demo.provider.entity.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    @Autowired(required = false)
    @Qualifier(value = "winP")
    private Person winP;

    @Autowired(required = false)
    @Qualifier(value = "linP")
    private Person linP;
    @Override
    public void run(String... args) throws Exception {
        log.info("server start success,you can do something here^ ^ 1");
        System.out.println(winP);
        System.out.println(linP);
    }
}
