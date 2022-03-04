package com.lzy.springcode.lesson001.demo2;

import com.lzy.springcode.lesson001.demo1.HelloWord;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/3/2
 * Time: 14:54
 * Description: No Description
 */
public class Client {
    public static void main(String[] args) {
        //1.bean配置文件位置
        String xmlPath = "classpath:/lesson001/demo2/bean.xml";
        //2.创建ClassPathXmlApplicationContext容器，给容器指定需要加载的bean配置文件
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(xmlPath);

        for (String beanName : Arrays.asList("user1", "user2", "user3", "user4", "user5")) {
            //获取bean的别名
            String[] aliases = context.getAliases(beanName);
            System.out.println(String.format("beanName:%s,别名:[%s]", beanName, String.join(",", aliases)));
        }
        System.out.println("spring容器中所有bean如下：");
        //getBeanDefinitionNames用于获取容器中所有bean的名称
        for (String beanName : context.getBeanDefinitionNames()) {
            //获取bean的别名
            String[] aliases = context.getAliases(beanName);
            System.out.println(String.format("beanName:%s,别名:[%s]", beanName, String.join(",", aliases)));
        }
    }
}
