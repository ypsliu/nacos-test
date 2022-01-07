package com.demo.thread;

import java.util.concurrent.*;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/1/6
 * Time: 9:13
 * Description: No Description
 */
public class ThreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        ExecutorService executorService1 = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
//        corePoolSize：线程池中的线程数量；
//        maximumPoolSize：线程池中的最大线程数量；
//        keepAliveTime：当线程池线程数量超过corePoolSize时，多余的空闲线程会在多长时间内被销毁；
//        unit：keepAliveTime的时间单位；
//        workQueue：任务队列，被提交但是尚未被执行的任务；
//        threadFactory：线程工厂，用于创建线程，一般情况下使用默认的，即Executors类的静态方法defaultThreadFactory()；handler：拒绝策略。当任务太多来不及处理时，如何拒绝任务。

//        corePoolSize与maximumPoolSize的关系
//        首先corePoolSize肯定是 <= maximumPoolSize。
//        其他关系如下：
//        若当前线程池中线程数 < corePoolSize，则每来一个任务就创建一个线程去执行；
//        若当前线程池中线程数 >= corePoolSize，会尝试将任务添加到任务队列。如果添加成功，则任务会等待空闲线程将其取出并执行；
//        若队列已满，且当前线程池中线程数 < maximumPoolSize，创建新的线程；
//        若当前线程池中线程数 >= maximumPoolSize，则会采用拒绝策略（JDK提供了四种，下面会介绍到）。
//        注意：关系3是针对的有界队列，无界队列永远都不会满，所以只有前2种关系。
//
//        workQueue
//        参数workQueue是指提交但未执行的任务队列。若当前线程池中线程数>=corePoolSize时，就会尝试将任务添加到任务队列中。主要有以下几种：
//        SynchronousQueue：直接提交队列。SynchronousQueue没有容量，所以实际上提交的任务不会被添加到任务队列，总是将新任务提交给线程执行，如果没有空闲的线程，则尝试创建新的线程，如果线程数量已经达到最大值（maximumPoolSize），则执行拒绝策略。
//        LinkedBlockingQueue：无界的任务队列。当有新的任务来到时，若系统的线程数小于corePoolSize，线程池会创建新的线程执行任务；当系统的线程数量等于corePoolSize后，因为是无界的任务队列，总是能成功将任务添加到任务队列中，所以线程数量不再增加。若任务创建的速度远大于任务处理的速度，无界队列会快速增长，直到内存耗尽。

//        JDK内置了四种拒绝策略：
//        DiscardOldestPolicy策略：丢弃任务队列中最早添加的任务，并尝试提交当前任务；
//        CallerRunsPolicy策略：调用主线程执行被拒绝的任务，这提供了一种简单的反馈控制机制，将降低新任务的提交速度。
//        DiscardPolicy策略：默默丢弃无法处理的任务，不予任何处理。
//        AbortPolicy策略：直接抛出异常，阻止系统正常工作。
    }
}
