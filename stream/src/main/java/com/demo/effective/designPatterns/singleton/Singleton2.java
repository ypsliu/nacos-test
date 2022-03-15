package com.demo.effective.designPatterns.singleton;

/**
 * @author lz
 * @date 2021-12-06 11:25
 *
 * 静态内部类
 *
 * 优点
 * 实现了懒加载；
 * 线程安全
 * 没有锁，性能较好
 * 缺点
 * 实现及理解起来相对复杂
 * 存在构造方式未设置private而导致反射实例化破坏单例的风险
 *
 */
public class Singleton2 {
    // 一定要将默认构造方法设置为private 否则反射实例化将破坏单例
    private Singleton2(){}

    public static class LazyHoader{
        private static final Singleton2 INSTANCE = new Singleton2();
    }

    public static Singleton2 getInstance(){
        return LazyHoader.INSTANCE;
    }
}
