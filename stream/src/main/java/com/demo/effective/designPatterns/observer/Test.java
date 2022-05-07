package com.demo.effective.designPatterns.observer;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/5/7
 * Time: 15:23
 * Description: No Description
 */
public class Test {
    public static void main(String[] args) {
        Subject subject = new SubjectImpl();
        subject.registerObserver(new OrderObserver());
        subject.registerObserver(new StockObserver());
        subject.notifyAllObserver("001");
    }
}
