package com.demo.provider.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.MethodParameter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;
import org.springframework.web.servlet.mvc.method.annotation.ServletModelAttributeMethodProcessor;

import javax.servlet.ServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/7/18
 * Time: 15:53
 * Description: 自定义注解解析
 *
 * 在 Java 中，一个接口只支持一种 content-type，
 * json 就用 @RequestBody，form 表单就用 @RequestParam 或不写，form-data 就用 MultipartFile
 *
 */
public class LZYMethodProcessor implements HandlerMethodArgumentResolver {
    private ServletModelAttributeMethodProcessor formResolver;
    private RequestResponseBodyMethodProcessor jsonResolver;

    public LZYMethodProcessor() {
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();

        HttpMessageConverter httpMessageConverter = new MappingJackson2XmlHttpMessageConverter();
        messageConverters.add(httpMessageConverter);

        jsonResolver = new RequestResponseBodyMethodProcessor(messageConverters);
        formResolver = new ServletModelAttributeMethodProcessor(false);
    }
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        LZYRequestParam ann = methodParameter.getParameterAnnotation(LZYRequestParam.class);
        return (ann != null);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        ServletRequest servletRequest = nativeWebRequest.getNativeRequest(ServletRequest.class);
        String contentType = servletRequest.getContentType();
        if (contentType == null) {
            throw new IllegalArgumentException("不支持contentType");
        }

        if (contentType.contains("application/json")) {
            return jsonResolver.resolveArgument(methodParameter, modelAndViewContainer, nativeWebRequest, webDataBinderFactory);
        }

        if (contentType.contains("application/x-www-form-urlencoded")) {
            return formResolver.resolveArgument(methodParameter, modelAndViewContainer, nativeWebRequest, webDataBinderFactory);
        }

        if (contentType.contains("multipart")) {
            return formResolver.resolveArgument(methodParameter, modelAndViewContainer, nativeWebRequest, webDataBinderFactory);
        }

        throw new IllegalArgumentException("不支持contentType");
    }


    /**
     *  form-data 的特殊处理
     *   引入 jar 包
     *      <dependency>
     *       <groupId>commons-fileupload</groupId>
     *       <artifactId>commons-fileupload</artifactId>
     *       <version>1.3.1</version>
     *     </dependency>
     *     <dependency>
     *       <groupId>commons-io</groupId>
     *       <artifactId>commons-io</artifactId>
     *       <version>2.4</version>
     *     </dependency>
     *
     *     新增解析 bean
     *
     *     @Bean(name = "multipartResolver")
     *  public MultipartResolver multipartResolver(){
     *     CommonsMultipartResolver resolver = new CommonsMultipartResolver();
     *     resolver.setDefaultEncoding("UTF-8");
     *     resolver.setResolveLazily(true);//resolveLazily属性启用是为了推迟文件解析，以在在UploadAction中捕获文件大小异常
     *     resolver.setMaxInMemorySize(40960);
     *     resolver.setMaxUploadSize(50*1024*1024);//上传文件大小 50M 50*1024*1024
     *     return resolver;
     *  }
     *
     */
}
