package com.demo.gateway.filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2021/12/21
 * Time: 16:16
 * Description: 自定义局部过滤器
 */
@Component
@Slf4j
public class AuthorizeGatewayFilterFactory
        extends AbstractGatewayFilterFactory<AuthorizeGatewayFilterFactory.Config> {
    private static final String AUTHORIZE_TOKEN = "token";

    //构造函数，加载Config
    public AuthorizeGatewayFilterFactory() {
        //固定写法
        super(AuthorizeGatewayFilterFactory.Config.class);
        log.info("Loaded GatewayFilterFactory [Authorize]");
    }

    //读取配置文件中的参数 赋值到 配置类中
    @Override
    public List<String> shortcutFieldOrder() {
//        Config.enabled
        return Arrays.asList("enabled");
    }

    @Override
    public GatewayFilter apply(AuthorizeGatewayFilterFactory.Config config) {
        return (exchange, chain) -> {
            //判断是否开启授权验证
            if (!config.isEnabled()) {
                return chain.filter(exchange);
            }
            ServerHttpRequest request = exchange.getRequest();
            HttpHeaders headers = request.getHeaders();
            //从请求头中获取token
            String token = headers.getFirst(AUTHORIZE_TOKEN);
            if (null == token) {
                //从请求头参数中获取token
                token = request.getQueryParams().getFirst(AUTHORIZE_TOKEN);
            }
            ServerHttpResponse response = exchange.getResponse();
            //如果token为空，直接返回401，未授权
            if (null == token) {
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                //处理完成，直接拦截，不再进行下去
                return response.setComplete();
            }
            //授权正常，继续下一个过滤器链的调用
            return chain.filter(exchange);
            /**
             *  chain.filter(exchange)
             * 之前的都是过滤器的前置处理 *
             * chain.filter().then(
             * 过滤器的后置处理)
             */
        };
    }
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Config { // 控制是否开启认证
         private boolean enabled;
    }
}
