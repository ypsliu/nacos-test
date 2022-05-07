package com.demo.effective.designPatterns.chain;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/5/7
 * Time: 15:53
 * Description: No Description
 */
public class Test {
    public static void main(String[] args) {
        Processor p1 = new ProcessorImpl1(null);
        Processor p2 = new ProcessorImpl2(p1);
        p2.process("something");
    }
}
