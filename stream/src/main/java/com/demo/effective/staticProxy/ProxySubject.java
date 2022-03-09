package com.demo.effective.staticProxy;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/3/9
 * Time: 16:03
 * Description: No Description
 */
public class ProxySubject implements Subject{
    //需要加入委托代理对象
    private Subject subject;

    public ProxySubject(final Subject subject){
        this.subject = subject;
    }
    @Override
    public void dealTask(String taskName) {
        this.subject.dealTask(taskName);
        System.out.println("任务执行完毕");
    }
}
