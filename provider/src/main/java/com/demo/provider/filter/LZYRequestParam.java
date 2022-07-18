package com.demo.provider.filter;

import java.lang.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/7/18
 * Time: 15:52
 * Description: 自定义注解解析
 *
 * 只需将 @RequestParam 注解改为 @LZYRequestParam，接口即可同时兼容三种 content-type。
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LZYRequestParam {
}
