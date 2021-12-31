package com.demo.hutool;

/**
 * @author lzy
 * @version 1.0
 * @date 2021/12/1 20:08
 */
public class HutoolDemo {
    public static void main(String[] args) {
//        Convert.convertQuietly()
        String string = "www.baidu.com/usr=123&pas=456";
        String string1 = "www.baidu.com/usr=123&pas=4562";
        System.out.println(string.hashCode());
        System.out.println(string1.hashCode());
    }
}
