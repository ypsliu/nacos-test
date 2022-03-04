package com.lzy.springcode.lesson001.demo1;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/3/2
 * Time: 14:32
 * Description: No Description
 */
public class Client {
    public static void main(String[] args) {
        //1.bean配置文件位置
        String xmlPath = "classpath:/lesson001/demo1/bean.xml";
        //2.创建ClassPathXmlApplicationContext容器，给容器指定需要加载的bean配置文件
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(xmlPath);
        //3.从容器中获取需要的bean
        HelloWord helloWord = context.getBean("helloWorld",HelloWord.class);
        //4.使用对象
        helloWord.say();
    }
}
