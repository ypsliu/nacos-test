package com.example.demo.util;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2021/12/14
 * Time: 16:15
 * Description: 其他配置文件读取
 */
@Configuration
@PropertySource("classpath:xconfig.properties")
@Data
public class XConfigUtil {
    @Value("${com.example.name}")
    private String name;
}
