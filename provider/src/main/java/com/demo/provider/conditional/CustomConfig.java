package com.demo.provider.conditional;

import com.demo.provider.entity.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @author lzy
 * @version 1.0
 * Date: 2022/6/3
 * Time: 14:01
 * Created with IntelliJ IDEA
 * Description: No Description
 */
@Configuration
public class CustomConfig {
    @Bean("winP")
    @Conditional(value = {WindowsCondition.class})
    public Person winP(){
        Person person = new Person();
        person.setUserName("windows");
        return person;
    }

    @Bean("linP")
    @Conditional(value = {LinuxCondition.class})
    public Person linP(){
        Person person = new Person();
        person.setUserName("linux");
        return person;
    }
}
