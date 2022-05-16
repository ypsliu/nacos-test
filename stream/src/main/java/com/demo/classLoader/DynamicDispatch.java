package com.demo.classLoader;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/5/16
 * Time: 11:40
 * Description: No Description
 */
public class DynamicDispatch {
    static abstract class Human{
        protected abstract void sayHello();
    }

    static class Man extends Human{
        @Override
        protected void sayHello() {
            System.out.println("man say hello!");
        }
    }
    static class Woman extends Human{
        @Override
        protected void sayHello() {
            System.out.println("woman say hello!");
        }
    }
    public static void main(String[] args) {

        Human man = new Man();
        Human woman = new Woman();
        man.sayHello();
        woman.sayHello();
        man = new Woman();
        man.sayHello();
        /**
         * man say hello!
         * woman say hello!
         * woman say hello!
         *
         * 父类引用指向子类时，如果执行的父类方法在子类中未被重写，则调用自身的方法；
         * 如果被子类重写了，则调用子类的方法。如果要使用子类特有的属性和方法，需要向下转型。
         */
    }
}
