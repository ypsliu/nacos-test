package com.demo.stream;

import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2021/12/21
 * Time: 16:34
 * Description: No Description
 */
public class OptionalDemo {
    public static void main(String[] args) {
        String str = "hello";
        Optional.ofNullable(str).ifPresent(
                s -> {
                    System.out.println("str:"+s);
                }
        );
        String str1 = "world";
        Optional.of(str1).ifPresent(
                s -> {
                    System.out.println("str1:"+s);
                }
        );
        //空字符串
        String str2 = "";
        str2 = Optional.ofNullable(str2).orElse("hello world");
        System.out.println("str2:"+str2);
        //null
        String str3 = null;
        str3 = Optional.ofNullable(str3).orElse("hello world");
        System.out.println("str3:"+str3);

        String str4 = null;
        //Optional.of() 如果为null 会直接报空指针异常
//        Optional.of(str4).ifPresent(
//                s -> {
//                    System.out.println("str4:"+str4);
//                }
//        );
        String str5 = null;
        Optional.ofNullable(str5).ifPresent(
                s -> {
                    //不会进来
                    System.out.println("str5:"+str5);
                }
        );

        String str6 = "";
        Optional.ofNullable(str6).ifPresent(
                s -> {
                    System.out.println("str6:"+str6);
                }
        );

        String str7 = null;
        Optional.ofNullable(str7).orElseThrow(
                ()->{
                    return new RuntimeException("str7 is null");
                }
        );
    }
}
