package com.demo.effective;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author lz
 * @date 2021-12-06 16:35
 */
public class Test {
    public static void main(String[] args) {
        System.out.println( BasicOperation.DIVIDE.apply(0.1,0.2));
        //随机数
        ThreadLocalRandom.current().nextInt(199);
    }
}
