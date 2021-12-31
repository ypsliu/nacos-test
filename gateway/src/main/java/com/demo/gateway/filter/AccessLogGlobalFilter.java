package com.demo.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.InetSocketAddress;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2021/12/21
 * Time: 19:33
 * Description: 全局过滤
 */
@Slf4j
@Component
@Order(Integer.MIN_VALUE)
public class AccessLogGlobalFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //filter的前置处理
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getPath().pathWithinApplication().value();
        InetSocketAddress remoteAddress = request.getRemoteAddress();
        return chain
            //继续调用filter
            .filter(exchange)
            //filter的后置处理
            .then(Mono.fromRunnable(() ->
                {
                    ServerHttpResponse response = exchange.getResponse();
                    HttpStatus statusCode = response.getStatusCode();
                    log.info("请求路径:{},远程IP地址:{},响应码:{}", path, remoteAddress, statusCode);
                }));
    }
}
