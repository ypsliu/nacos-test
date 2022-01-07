package com.demo.logHandler;

import lombok.extern.slf4j.Slf4j;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/1/7
 * Time: 9:29
 * Description: No Description
 */
@Slf4j
public class LocalUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler{

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        log.error("Uncaught exception in thread: {}",t.getName(),e);
    }
}
