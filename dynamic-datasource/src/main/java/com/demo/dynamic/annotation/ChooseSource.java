package com.demo.dynamic.annotation;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/5/18
 * Time: 13:51
 * Description: 切换数据源的注解
 */

import com.demo.dynamic.constant.Constant;
import java.lang.annotation.*;

@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface ChooseSource {
    /**
     * 需要切换到数据的KEY
     */
    String value() default Constant.MASTER_DATA_SOURCE;
}
