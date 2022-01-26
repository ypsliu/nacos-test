package com.demo.hutool;

import lombok.extern.slf4j.Slf4j;

/**
 * @author lzy
 * @version 1.0
 * @date 2021/12/1 20:08
 */
@Slf4j
public class HutoolDemo {
    public static void main1(String[] args) {
//        Convert.convertQuietly()
        String string = "www.baidu.com/usr=123&pas=456";
        String string1 = "www.baidu.com/usr=123&pas=4562";
        System.out.println(string.hashCode());
        System.out.println(string1.hashCode());
    }

    public static void main(String[] args) {
        Long startTime = System.currentTimeMillis();
        for(int i=0;i<5120;i++){
            if(log.isDebugEnabled()){
                log.debug(i+"i");
            }
            log.info("this is async log");//24957
        }
        System.out.println("use time:"+(System.currentTimeMillis()-startTime));
    }
}
