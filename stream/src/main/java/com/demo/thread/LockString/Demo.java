package com.demo.thread.LockString;

/**
 * @author lzy
 * @version 1.0
 * Date: 2022/5/14
 * Time: 0:51
 * Created with IntelliJ IDEA
 * Description: 为何JVM中不建议使用synchronized（String s）
 */
public class Demo {
    /**
     * 因为 JVM 中，字符串常量池具有缓存功能 string1和string2其实地址引用的是同一个对象
     */
    private static String string1 = "aaa";
    private static String string2 = "aaa";
    public static void main(String[] args) {
        Thread thread1 = new Thread(
                ()->{
                    synchronized (string1){
                        while (true){
                            try {
                                Thread.sleep(1000);
                                System.out.println("string");
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
        );
        thread1.start();
        Thread thread2 = new Thread(
                ()->{
                    synchronized (string2){
                        System.out.println("string2");
                    }
                }
        );
        thread2.start();

        String string = "aaa";
        System.out.println(string);
    }
}
