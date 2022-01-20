package com.demo.provider.handler;

import com.demo.common.HttpUtils.domain.response.BaseResponseDto;
import com.demo.common.HttpUtils.domain.response.ResponseUtil;
import com.demo.common.HttpUtils.exception.BaseException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lz
 * @date 2021-08-18 10:52
 * @controllerAdvice+@ResponseBody
 */
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)//order拦截顺序
@Slf4j
public class DemoExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public BaseResponseDto handleAllExceptions(BaseException demoException) {
        return ResponseUtil.error(demoException);
    }

    /**
     * Assert异常
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public BaseResponseDto exceptionAssert(IllegalArgumentException e) {
        return ResponseUtil.error(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
    }

    @ExceptionHandler(BindException.class)
    public BaseResponseDto handleBindExceptions(BindException bindException){
        log.error("param check error:",bindException);
        // 获取到所有的校验失败的属性
        List<FieldError> fieldErrors = bindException.getFieldErrors();
        // 实例化一个用于装参数错误的list
        List<ParamErrDto> paramErrDtos = new ArrayList<>();
        for (FieldError fieldError : fieldErrors) {
            // 那段字段名
            String field = fieldError.getField();
            // 拿到异常的描述
            String defaultMessage = fieldError.getDefaultMessage();
            log.info("field:{} msg:{}", field, defaultMessage);
            // 添加到list中去
            paramErrDtos.add(new ParamErrDto(field, defaultMessage));
        }

        // 返回前端参数错误 并告诉前端那些字段不对 具体描述是什么
        return ResponseUtil.error(HttpStatus.BAD_REQUEST,paramErrDtos);
    }

    @Data
    @AllArgsConstructor
    class ParamErrDto{
        private String field;
        private String defaultMessage;
    }


}
