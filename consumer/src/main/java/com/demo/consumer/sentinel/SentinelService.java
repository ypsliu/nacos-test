package com.demo.consumer.sentinel;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2021/12/17
 * Time: 15:04
 * Description: No Description
 */
@Service
public class SentinelService {

    /*这里将会用到一个注解@SentinelResource，这个在上文也是提到过，这个注解中有两个
    关于限流兜底方法的属性，如下：
    blockHandler： 对应处理 BlockException 的函数名称。blockHandler 函数访
    问范围需要是 public，返回类型需要与原方法相匹配，参数类型需要和原方法相
    匹配并且最后加一个额外的参数，类型为 BlockException。blockHandler 函数
    默认需要和原方法在同一个类中。
    blockHandlerClass：指定 blockHandlerClass 为对应的类的 Class 对象，注意
    对应的函数必需为 static 函数，否则无法解析。*/
    @SentinelResource(value = "block",blockHandlerClass = BlockHandler.class,
            blockHandler = "handler")
    public String getBlockResult(){
        return "block success";
    }
//     自定义类之后，记得把此方法注释掉，否则会不生效
//    public String handler(BlockException  blockException){
//        return "sentinel handler "+blockException.getRuleLimitApp();
//    }

    /*fallback：fallback 函数名称，可选项，用于在抛出异常的时候提供 fallback 处理
    逻辑。fallback 函数可以针对所有类型的异常（除了 exceptionsToIgnore 里面排
    除掉的异常类型）进行处理。fallback 函数签名和位置要求：
    返回值类型必须与原函数返回值类型一致；
    方法参数列表需要和原函数一致，或者可以额外多一个 Throwable 类型
    的参数用于接收对应的异常。
    fallback 函数默认需要和原方法在同一个类中。若希望使用其他类的函
    数，则可以指定 fallbackClass 为对应的类的 Class 对象
    fallbackClass：指定对应的类的 Class 对象，注意对应的函数必需为 static 函
    数，否则无法解析。
    defaultFallback（since 1.6.0）：默认的 fallback 函数名称，可选项，通常用于
    通用的 fallback 逻辑（即可以用于很多服务或方法）。默认 fallback 函数可以针对
    所有类型的异常（除了 exceptionsToIgnore 里面排除掉的异常类型）进行处
    理。若同时配置了 fallback 和 defaultFallback，则只有 fallback 会生效。 defaultFallback 函数签名要求：
    返回值类型必须与原函数返回值类型一致；
    方法参数列表需要为空，或者可以额外多一个 Throwable 类型的参数
    用于接收对应的异常。
    defaultFallback 函数默认需要和原方法在同一个类中。若希望使用其他
    类的函数，则可以指定 fallbackClass 为对应的类的 Class 对象，注意
    对应的函数必需为 static 函数，否则无法解析。
    exceptionsToIgnore（since 1.6.0）：用于指定哪些异常被排除掉，不会计入异
    常统计中，也不会进入 fallback 逻辑中，而是会原样抛出。*/

    @SentinelResource(value = "fallback",fallbackClass = FallBackHandler.class,
            fallback = "handler")
    public String getFallBackResult(){
        System.out.println(1/0);
        return "fall back success";
    }

    /*结论：若 blockHandler 和 fallback 都进行了配置，则被限流降级而抛出
    BlockException 时只会进入 blockHandler 处理逻辑。若未配置
    blockHandler、fallback 和 defaultFallback，则被限流降级时会将
    BlockException 直接抛出。*/
    @SentinelResource(value = "all",
            fallbackClass = FallBackHandler.class,
            fallback = "handler",
            blockHandlerClass = BlockHandler.class,
            blockHandler = "handler")
    public String getAllResult(){
        System.out.println(1/0);
        return "all success";
    }
}
