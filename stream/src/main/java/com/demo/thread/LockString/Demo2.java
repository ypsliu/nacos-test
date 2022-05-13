package com.demo.thread.LockString;

/**
 * @author lzy
 * @version 1.0
 * Date: 2022/5/14
 * Time: 0:51
 * Created with IntelliJ IDEA
 * Description: 为何JVM中不建议使用synchronized（String s）
 */
public class Demo2 {
    /**
     * class 文件路径下 javap -c -v Demo2.class 查看反编译文件
     */
    public void method() {
        synchronized (this) {
            System.out.println("synchronized 代码块");
        }
    }
    public static void main(String[] args) {
        StringDemo stringDemo1 = new StringDemo("aaa");
        StringDemo stringDemo2 = new StringDemo("aaa");
        System.out.println(stringDemo1 == stringDemo2);
        System.out.println(stringDemo1.equals(stringDemo2));
        Thread thread1 = new Thread(
                ()->{
                    synchronized (stringDemo1){
                        while (true){
                            try {
                                Thread.sleep(1000);
                                System.out.println("stringDemo1");
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
                    synchronized (stringDemo2){
                        System.out.println("stringDemo2");
                    }
                }
        );
        thread2.start();

    }
}
