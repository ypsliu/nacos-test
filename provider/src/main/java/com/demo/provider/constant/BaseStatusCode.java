package com.demo.provider.constant;

import lombok.AllArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2021/12/9
 * Time: 15:52
 * Description: No Description
 */
@AllArgsConstructor
public enum BaseStatusCode implements IStatusCode{
    SUCCESS(200,"成功!"),

    ERR_1000(1000,"参数错误!"),

    ERR_9999(9999,"未知错误!");

    // 状态码
    private Integer status;

    // 状态码描述
    private String msg;

    @Override
    public int getStatus() {
        return status;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
