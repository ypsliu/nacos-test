package com.demo.consumer.ribbon;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/3/16
 * Time: 16:11
 * Description: No Description
 */
//@Configuration
@Slf4j
public class RestTemplateConfig {
    /**
     * http请求默认连接超时时间
     */
    final int DEFAULT_CONNECT_TIMEOUT = 30 * 1000;

    /**
     * http请求读数据超时时间
     */
    final int DEFAULT_READ_TIMEOUT = 30 * 1000;

    @Primary
    @Bean("restTemplate")
    public RestTemplate restTemplate() {
        RestTemplateBuilder builder = new RestTemplateBuilder();
        return builder
                .setConnectTimeout(Duration.ofMillis(DEFAULT_CONNECT_TIMEOUT))
                .setReadTimeout(Duration.ofMillis(DEFAULT_READ_TIMEOUT))
                .build();
    }

    @Bean("appRestTemplate")
    public RestTemplate appRestTemplate() {
        RestTemplateBuilder builder = new RestTemplateBuilder();
        ResponseErrorHandler responseErrorHandler = new ResponseErrorHandler() {
            @Override
            public boolean hasError(ClientHttpResponse clientHttpResponse) {
                return true;
            }

            @Override
            public void handleError(ClientHttpResponse clientHttpResponse) {
            }
        };

        ClientHttpRequestInterceptor requestInterceptor = (httpRequest, bytes, clientHttpRequestExecution) -> {
            ClientHttpResponse execute;
            try {
                execute = clientHttpRequestExecution.execute(httpRequest, bytes);
            } catch (IOException e) {
                log.info("appRestTemplate...error:" + e);
                // 特殊处理超时异常
                if (e instanceof SocketTimeoutException) {
                    // 返回自定义异常
                    // throw new AppException(AppErrorEnum.REQUEST_TIMEOUT);
                }
                throw e;
            }
            return execute;
        };
        return builder
                .setConnectTimeout(Duration.ofMillis(DEFAULT_CONNECT_TIMEOUT))
                .setReadTimeout(Duration.ofMillis(DEFAULT_READ_TIMEOUT))
                // 自定义拦截器
                .interceptors(requestInterceptor)
                // 自定义异常处理
                .errorHandler(responseErrorHandler)
                .messageConverters(new WxMappingJackson2HttpMessageConverter())
                .build();

    }


    class WxMappingJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {
        public WxMappingJackson2HttpMessageConverter() {
            List<MediaType> mediaTypes = new ArrayList<>();
            mediaTypes.add(MediaType.APPLICATION_OCTET_STREAM);
            mediaTypes.add(MediaType.APPLICATION_JSON);
            setSupportedMediaTypes(mediaTypes);
        }
    }

}
