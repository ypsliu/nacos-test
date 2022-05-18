package com.demo.effective.designPatterns.creatorMode.singletonPattern;

/**
 * @author lz
 * @date 2021-12-06 11:17
 *
 * 双重检查DCL
 *
 * 优点
 * 实现了懒加载；
 * 线程安全
 * 锁粒度较细，只有第一次初始化的时候会走到synchronized部分
 * 缺点
 * 实现起来相对复杂，对于volatile的理解会比较的难
 * 存在构造方式未设置private而导致反射实例化破坏单例的风险
 */
public class Singleton1 {
    //这里一定要加volatile 否则可能因为指令重排的问题导致对象未初始化完成的情况
    private volatile static Singleton1 singleton1 = null;

    // 一定要将默认构造方法设置为private 否则反射实例化将破坏单例
    private Singleton1(){}

    public static Singleton1 getInstance(){
        if(null == singleton1){
            synchronized (Singleton1.class){
                if(null == singleton1){
                    singleton1 = new Singleton1();
                }
            }
        }
        return singleton1;
    }
}
