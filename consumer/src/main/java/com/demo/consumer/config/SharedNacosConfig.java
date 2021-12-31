package com.demo.consumer.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2021/12/16
 * Time: 11:09
 * Description: 共享配置
 */
@Configuration
@RefreshScope
@Data
public class SharedNacosConfig {
    @Value("${lzy.user}")
    private String user;

    @Value("${lzy.password}")
    private String passwrod;
}
