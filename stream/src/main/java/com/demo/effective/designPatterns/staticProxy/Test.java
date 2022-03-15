package com.demo.effective.designPatterns.staticProxy;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/3/9
 * Time: 16:05
 * Description: No Description
 */
public class Test {
    public static void main(String[] args) {
        Subject subject = new ProxySubject(new RealSubject());
        subject.dealTask("hello world");
    }
}
