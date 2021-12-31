package com.demo.consumer.sentinel;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2021/12/17
 * Time: 18:05
 * Description: 请求来源解析类
 */
@Configuration
public class RequestOrigin implements RequestOriginParser {

    @Override
    public String parseOrigin(HttpServletRequest request) {
        return request.getRemoteAddr();
    }
}
