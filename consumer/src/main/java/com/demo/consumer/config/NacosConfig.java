package com.demo.consumer.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2021/12/15
 * Time: 17:16
 * Description: No Description
 */
@Configuration
@RefreshScope
@Data
public class NacosConfig {

    @Value("${lzy.version}")
    private String version;

}
