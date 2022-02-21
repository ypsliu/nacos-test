package com.demo.provider.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2021/12/14
 * Time: 17:32
 * Description: static>constructer>@PostConstruct>CommandLineRunner和ApplicationRunner
 */
@Component
public class RunnerHelper2 {
    static {
        System.out.println("static 3");
    }
    public RunnerHelper2() {
        System.out.println("constructer 4");
    }

    @PostConstruct
    public void init() {
        System.out.println("PostConstruct 5");
    }
}
