package com.demo.hutool;

import cn.hutool.core.bean.BeanUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author lzy
 * @version 1.0
 * @date 2021/12/1 20:08
 */
@Slf4j
public class HutoolDemo {
    public static void main(String[] args) {
//        Convert.convertQuietly()
        String string = "www.baidu.com/usr=123&pas=456";
        String string1 = "www.baidu.com/usr=123&pas=4562";
        System.out.println(string.hashCode());
        System.out.println(string1.hashCode());

        String str = "吉";
        String str1 = "\uD842\uDFB7";
        System.out.println(str1);
    }

    public static void main1(String[] args) {
        Long startTime = System.currentTimeMillis();
        for(int i=0;i<5120;i++){
            //FIXME 对于 trace/debug/info 级别的日志输出，必须进行日志级别的开关判断
            if(log.isDebugEnabled()){
                log.debug(i+"i");
            }
            log.info("this is async log");//24957
        }
        System.out.println("use time:"+(System.currentTimeMillis()-startTime));

        /**
         * • DO（Data Object）：此对象与数据库表结构一一对应，通过 DAO 层向上传输数据源对象。
         * • DTO（Data Transfer Object）：数据传输对象，Service 或 Manager 向外传输的对象。
         * • BO（Business Object）：业务对象，可以由 Service 层输出的封装业务逻辑的对象。
         * • Query：数据查询对象，各层接收上层的查询请求。注意超过 2 个参数的查询封装，禁止使用 Map 类
         * 来传输。
         * • VO（View Object）：显示层对象，通常是 Web 向模板渲染引擎层传输的对象。
         */

    }
}
