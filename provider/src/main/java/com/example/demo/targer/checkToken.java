package com.example.demo.targer;

import java.lang.annotation.*;

/**
 * @author lz
 * @date 2021-08-18 9:51
 */
@Documented
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface checkToken {
}
