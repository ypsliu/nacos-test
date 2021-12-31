package com.demo.provider.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2021/12/14
 * Time: 11:00
 * Description: No Description
 */
@Data
@ConfigurationProperties("com.example")
@Component
public class ConfigUtil {
    private String name;

    private String title;

    private String desc;

    private String value;

    private int number;

    private long bignumber;

    private int test1;

    private int test2;

}
