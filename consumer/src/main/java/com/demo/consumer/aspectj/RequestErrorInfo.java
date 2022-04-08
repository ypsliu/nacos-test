package com.demo.consumer.aspectj;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/4/6
 * Time: 9:28
 * Description: No Description
 */
@Data
public class RequestErrorInfo {
    private String ip;
    private String url;
    private String httpMethod;
    private String classMethod;
    private Object requestParams;
    private RuntimeException exception;
}
