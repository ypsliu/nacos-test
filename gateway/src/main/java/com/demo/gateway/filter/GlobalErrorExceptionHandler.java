package com.demo.gateway.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2021/12/21
 * Time: 20:26
 * Description: 网关的全局异常处理
 */
@Slf4j
@Order(-1)
@Component
@RequiredArgsConstructor
public class GlobalErrorExceptionHandler implements ErrorWebExceptionHandler {
    private final ObjectMapper objectMapper;
    @SuppressWarnings({"rawtypes", "unchecked", "NullableProblems"})
    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        ServerHttpResponse response = exchange.getResponse();
        if(response.isCommitted()){
            return Mono.error(ex);
        }
        //json格式返回
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        if(ex instanceof ResponseStatusException){
            response.setStatusCode(((ResponseStatusException) ex).getStatus());
        }
        return response.writeWith(Mono.fromSupplier(() -> {
            DataBufferFactory bufferFactory = response.bufferFactory();
            try {
                //todo 返回响应结果，根据业务需求，自己定制
                CommonResponse resultMsg = new CommonResponse("500",ex.getMessage(),null);
                return bufferFactory.wrap(objectMapper.writeValueAsBytes(resultMsg));
            }catch (JsonProcessingException jsonProcessingException){
                log.error("Error writing response", ex);
                return bufferFactory.wrap(new byte[0]);
            }
        }));
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class CommonResponse{
        private String code;
        private String message;
        private Object data;
    }
}
