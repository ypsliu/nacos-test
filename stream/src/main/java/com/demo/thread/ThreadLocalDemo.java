package com.demo.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/3/9
 * Time: 10:19
 * Description: No Description
 */
public class ThreadLocalDemo {
    public static void main(String[] args) {
        incrementSameThreadId();
        new Thread(()->{
            incrementSameThreadId();
        }).start();

        new Thread(()->{
            incrementSameThreadId();
        }).start();
    }

    public static void incrementSameThreadId(){
        try {
            for(int i=0;i<5;i++){
                System.out.println(Thread.currentThread()+"_"+i+",ThreadId:"+ThreadLocalId.get());
            }
        } finally {
            ThreadLocalId.remove();
        }
    }
}
