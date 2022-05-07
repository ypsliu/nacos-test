package com.demo.effective.designPatterns.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/5/7
 * Time: 15:20
 * Description: No Description
 */
public class SubjectImpl implements Subject{

    private final List<Observer> list = new ArrayList<>();

    /**
     * 订阅
     * @param o
     */
    @Override
    public void registerObserver(Observer o) {
        list.add(o);
    }

    /**
     * 发布
     * @param orderNo
     */
    @Override
    public void notifyAllObserver(String orderNo) {
        list.forEach(c -> c.notify(orderNo));
    }
}
