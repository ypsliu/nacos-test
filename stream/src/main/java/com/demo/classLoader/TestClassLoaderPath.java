package com.demo.classLoader;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/3/4
 * Time: 15:23
 * Description: No Description
 */
public class TestClassLoaderPath {
    //        Bootstrap ClassLoader:这个加载器不是一个Java类，而是由底层的c++实现，负责在虚拟机启动时加载Jdk核心类库（如：rt.jar、resources.jar、charsets.jar等）以及加载后两个类加载器。这个ClassLoader完全是JVM自己控制的，需要加载哪个类，怎么加载都是由JVM自己控制，别人也访问不到这个类

    //        Extension ClassLoader:是一个普通的Java类，继承自ClassLoader类，负责加载{JAVA_HOME}/jre/lib/ext/目录下的所有jar包。

    //        App ClassLoader：是Extension ClassLoader的子对象，负责加载应用程序classpath目录下的所有jar和class文件。
    public static void main(String[] args) {
        //Bootstrap ClassLoader 加载的文件
        System.out.println(System.getProperty("sun.boot.class.path"));

        //Extension ClassLoader 加载的文件
        System.out.println(System.getProperty("java.ext.dirs"));

        //App ClassLoader
        System.out.println(System.getProperty("java.class.path"));

        //两种类的加载方式
        //Class.forName()
        //ClassLoader.loadClass() 与上一种方式的最本质的不同是，类不会被初始化，只有显式调用才会进行初始化

    }

    @Override
    public String toString(){
        return "自定义类加载成功";
    }
}
