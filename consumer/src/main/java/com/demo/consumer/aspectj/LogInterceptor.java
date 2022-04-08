package com.demo.consumer.aspectj;

import org.apache.logging.log4j.ThreadContext;
import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/4/6
 * Time: 9:31
 * Description: 日志跟踪更方便 DMC是配置logback和log4j使用的，使用方式和ThreadContext差不多，
 * 将ThreadContext.put替换为MDC.put即可，同时修改日志配置文件。
 * <property name="pattern">[TRACEID:%X{traceId}] %d{HH:mm:ss.SSS} %-5level %class{-1}.%M()/%L - %msg%xEx%n</property>
 */
public class LogInterceptor implements HandlerInterceptor {
    private final static String TRACE_ID = "traceId";
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String traceId = java.util.UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
//        ThreadContext.put("traceId", traceId);
        MDC.put("traceId", traceId);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
//        ThreadContext. remove(TRACE_ID);
        MDC. remove(TRACE_ID);
    }
}
