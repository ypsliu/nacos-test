package com.demo.provider.conditional;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author lzy
 * @version 1.0
 * Date: 2022/6/3
 * Time: 13:58
 * Created with IntelliJ IDEA
 * Description: 操作系统的匹配条件，如果是windows系统，则返回true
 */
public class WindowsCondition implements Condition {
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        //获取当前环境信息
        Environment environment = conditionContext.getEnvironment();
        //获得当前系统名
        String property = environment.getProperty("os.name");
        //包含Windows则说明是windows系统，返回true
        if (property.contains("Windows")){
            return true;
        }
        return false;
    }
}
