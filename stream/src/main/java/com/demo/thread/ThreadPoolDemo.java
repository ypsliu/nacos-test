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

        ExecutorService threadPool = new ThreadPoolExecutor(
                8, //corePoolSize线程池中核心线程数
                10, //maximumPoolSize 线程池中最大线程数
                60, //线程池中线程的最大空闲时间，超过这个时间空闲线程将被回收
                TimeUnit.SECONDS,//时间单位
                new ArrayBlockingQueue(500), //队列
                new ThreadPoolExecutor.CallerRunsPolicy()); //拒绝策略

        //使用@Async注解开启的异步功能，默认情况下，每次都会创建一个新线程。
        //
        //如果在高并发的场景下，可能会产生大量的线程，从而导致OOM问题。
        //
        //建议大家在@Async注解开启的异步功能时，请别忘了定义一个线程池。

/**
 * 线程池 原理
 *
 * 线程池的执行流程是：
 * 先判断当前线程数是否大于核心线程数？如果结果为 false，则新建线程并执行任务；
 * 如果结果为 true，则判断任务队列是否已满？
 * 如果结果为 false，则把任务添加到任务队列中等待线程执行，
 * 否则则判断当前线程数量是否超过最大线程数？如果结果为 false，则新建线程执行此任务，否则将执行线程池的拒绝策略
 */
//        public void execute(Runnable command) {
//            if (command == null)
//                throw new NullPointerException();
//            int c = ctl.get();
//            // 当前工作的线程数小于核心线程数
//            if (workerCountOf(c) < corePoolSize) {
//                // 创建新的线程执行此任务
//                if (addWorker(command, true))
//                    return;
//                c = ctl.get();
//            }
//            // 检查线程池是否处于运行状态，如果是则把任务添加到队列
//            if (isRunning(c) && workQueue.offer(command)) {
//                int recheck = ctl.get();
//                // 再次检线程池是否处于运行状态，防止在第一次校验通过后线程池关闭
//                // 如果是非运行状态，则将刚加入队列的任务移除
//                if (! isRunning(recheck) && remove(command))
//                    reject(command);
//                    // 如果线程池的线程数为 0 时（当 corePoolSize 设置为 0 时会发生）
//                else if (workerCountOf(recheck) == 0)
//                    addWorker(null, false); // 新建线程执行任务
//            }
//            // 核心线程都在忙且队列都已爆满，尝试新启动一个线程执行失败
//            else if (!addWorker(command, false))
//                // 执行拒绝策略
//                reject(command);
//        }
    }
}
