package com.demo.provider.aspectj;

/**
 *
 * @author lz
 * @date 2021-08-18 9:56
 *
 */

import com.demo.common.HttpUtils.constant.status.DeviceStatusCode;
import com.demo.common.HttpUtils.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Aspect
@Component
@Slf4j
public class AopConfig {
    @Value("${tokenConfig.open}")
    private boolean open;

    @Pointcut("@within(org.springframework.stereotype.Controller) || @within(org.springframework.web.bind.annotation.RestController)")
    public void pointcut() {
    }

    @Around("pointcut() && (@within(com.demo.provider.targer.checkToken) || @annotation(com.demo.provider.targer.checkToken))")
    public Object simpleAop(final ProceedingJoinPoint joinPoint) throws Throwable {

        try {
            // 调用原有的方法
            Long before = System.currentTimeMillis();
            Object o = joinPoint.proceed();
            Long after = System.currentTimeMillis();
            log.debug("call method consume: " + (after - before) + "ms");
            log.debug("method return: " + o);
            return o;
        } catch (Throwable throwable) {
            throw throwable;
        }
    }

    @Before("@within(com.demo.provider.targer.checkToken) || @annotation(com.demo.provider.targer.checkToken)")
    public void doBefore(JoinPoint joinPoint) throws Exception{

        log.debug("method start: " + getFunctionName(joinPoint));
        Object[] args = joinPoint.getArgs();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        if (open){
            //token校验
            throw new BaseException(DeviceStatusCode.ERR_3002);
        }
        String url = request.getRequestURL().toString();
        log.debug("request URL: {},request params: {}",url,args);
        Long before = System.currentTimeMillis();
        log.debug("call method start: "+before);
    }

    @After("@within(com.demo.provider.targer.checkToken) || @annotation(com.demo.provider.targer.checkToken)")
    public void doAfter() {
        // TODO
        Long after = System.currentTimeMillis();
        log.debug("method end：" + after);
    }

    /**
     * 获取切面方法
     * @param joinPoint
     * @return
     */
    private String getFunctionName(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        return signature.getName();
//        CheckToken annotation = signature.getMethod().getAnnotation(CheckToken.class);

    }

    /**
     * 获取目标主机的ip
     */
    private String getRemoteHost(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }


//    //连接点是@RequestMapping注解的方法
//    @Pointcut("execution(* com.demo.provider.*.*(..))")
//    private void webPointcut() {}
//
//    //切点在webpointCut()
//    @AfterThrowing(pointcut = "webPointcut()", throwing = "e")
//    //controller类抛出的异常在这边捕获
//    public void handleThrowing(JoinPoint joinPoint, Exception e) {
//        String className = joinPoint.getTarget().getClass().getName();
//        String methodName = joinPoint.getSignature().getName();
//        Object[] args = joinPoint.getArgs();
//        //开始打log
//        System.out.println("异常:" + e.getMessage());
//        System.out.println("异常所在类：" + className);
//        System.out.println("异常所在方法：" + methodName);
//        System.out.println("异常中的参数：");
//        System.out.println(methodName);
//        for (int i = 0; i < args.length; i++) {
//            System.out.println(args[i].toString());
//        }
//    }

}
