package com.demo.provider.security;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.StrUtil;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/6/2
 * Time: 9:50
 * Description: 拦截器，在preHandle中解析请求头的中的token信息，将其放入SecurityContextHolder中
 *  *                      在afterCompletion方法中移除对应的ThreadLocal中信息
 *  *                      确保每个请求的用户信息独立
 */
public class AuthInterceptor implements AsyncHandlerInterceptor {
    /**
     * 在执行controller方法之前将请求头中的token信息解析出来，放入SecurityContextHolder中（TransmittableThreadLocal）
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!(handler instanceof HandlerMethod))
            return true;
        //获取请求头中的加密的用户信息
        String token = request.getHeader("token");
        if (StrUtil.isBlank(token))
            return true;
        //解密
        String loginVal = Base64.decodeStr(token);
        //封装数据到ThreadLocal中
        SecurityContextHolder.set(loginVal);
        return true;
    }

    /**
     * 在视图渲染之后执行，意味着一次请求结束，清除TTL中的身份信息
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex){
        SecurityContextHolder.remove();
    }
}
