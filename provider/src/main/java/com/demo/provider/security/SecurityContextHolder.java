package com.demo.provider.security;

import com.alibaba.ttl.TransmittableThreadLocal;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/6/2
 * Time: 9:49
 * Description: 使用TransmittableThreadLocal存储用户身份信息
 */
public class SecurityContextHolder {
    //使用TTL存储身份信息
    private static final TransmittableThreadLocal<String> THREAD_LOCAL = new TransmittableThreadLocal<>();

    public static void set(String loginVal){
        THREAD_LOCAL.set(loginVal);
    }

    public static String get(){
        return THREAD_LOCAL.get();
    }

    public static void remove(){
        THREAD_LOCAL.remove();
    }

}
