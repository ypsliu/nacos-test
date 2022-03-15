package com.demo.effective.designPatterns.singleton;

/**
 * @author lz
 * @date 2021-12-06 11:32
 *
 * 枚举单例
 *
 * 优点
 * 线程安全;
 * 没有锁，性能较好;
 * 实现简单，理解容易;
 * 不会因构造方法未设置为private而带来的破坏单例的风险.
 * 缺点
 * 未实现了懒加载；
 */
public enum Singleton3 {
    INSTANCE;

    public void test(){
        System.out.println("test()");
    }
}
