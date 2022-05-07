package com.demo.effective.designPatterns.observer;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/5/7
 * Time: 15:18
 * Description: 主题
 */
public interface Subject {
    void registerObserver(Observer o);
    void notifyAllObserver(String orderNo);
}
