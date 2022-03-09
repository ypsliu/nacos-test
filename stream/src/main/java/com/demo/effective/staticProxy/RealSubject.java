package com.demo.effective.staticProxy;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/3/9
 * Time: 16:02
 * Description: No Description
 */
public class RealSubject implements Subject{
    @Override
    public void dealTask(String taskName) {
        System.out.println("执行任务："+taskName);
    }
}
