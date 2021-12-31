package com.demo.provider.handler;

import com.demo.common.HttpUtils.constant.target.ResponseDataBody;
import com.demo.common.HttpUtils.domain.response.BaseResponseDto;
import com.demo.common.HttpUtils.domain.response.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.annotation.Annotation;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2021/12/9
 * Time: 17:48
 * Description: No Description
 */
@RestControllerAdvice(basePackages = "com.demo.provider.controller")
@Order(Ordered.LOWEST_PRECEDENCE)//order拦截顺序
@Slf4j
public class ResponseDataBodyAdvice implements ResponseBodyAdvice<Object> {

    /**
     * 得到自定义的注解
     */
    private static final Class<? extends Annotation> ANNOTATION_TYPE = ResponseDataBody.class;


    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return AnnotatedElementUtils.hasAnnotation(methodParameter.getContainingClass(), ANNOTATION_TYPE) || methodParameter.hasMethodAnnotation(ANNOTATION_TYPE);
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        // 防止重复包裹的问题出现 如果已经是要返回的基础对象了 就直接返回
        if (o instanceof BaseResponseDto) {
            return o;
        }
        return ResponseUtil.success(o);
    }
}
