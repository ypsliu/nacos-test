package com.example.demo.handler;

import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2021/12/9
 * Time: 17:55
 * Description: 规范响应数据的注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@ResponseBody
public @interface ResponseDataBody {
}
