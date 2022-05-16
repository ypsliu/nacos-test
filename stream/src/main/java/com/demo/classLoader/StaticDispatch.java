package com.demo.classLoader;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/5/16
 * Time: 11:36
 * Description: No Description
 */
public class StaticDispatch {
    static abstract class Human {
    }

    static class Man extends Human {
    }

    static class Woman extends Human {
    }

    public void sayHello(Human guy) {
        System.out.println("hello,guy！");
    }

    public void sayHello(Man guy) {
        System.out.println("hello,gentleman！");
    }

    public void sayHello(Woman guy) {
        System.out.println("hello,lady！");
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();
        StaticDispatch sr = new StaticDispatch();
        sr.sayHello(man);
        sr.sayHello(woman);
        /**
         * hello,guy！
         * hello,guy！
         */

        /**
         * 静态类型和实际类型。拿Human man = new Man();来说Human称为变量的静态类型，而Man我们称为变量的实际类型，区别如下：
         *
         * 静态类型的变化仅仅在使用时才发生，变量本身的静态类型是不会被改变，并且最终静态类型在编译期是可知的。
         * 实际类型的变化是在运行期才知道，编译器在编译程序时并不知道一个对象的具体类型是什么。
         * 此处之所以执行的是Human类型的方法，是因为编译器在重载时，会通过参数的「静态类型」来作为判定执行方法的依据，而不是使用「实际类型」。
         *
         * 所有依赖静态类型来定位方法执行版本的分派动作称为静态分派。静态分派的典型应用就是方法重载。静态分派发生在编译阶段，因此确定静态分派的动作实际上不是由虚拟机来执行的，而是由编译器来完成。
         */
    }
}
