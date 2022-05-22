package com.demo.thread;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.processing.Completion;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.LockSupport;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/5/19
 * Time: 9:41
 * Description: CompletionService的实现目标是任务先完成可优先获取到，即结果按照完成先后顺序排序。
 */
public class CompletionServiceDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        List<Future> futureList = new ArrayList<>();
        futureList.add(executorService.submit(
                ()->{

                    return "A";
                }
        ));
        futureList.add(executorService.submit(
                ()->{
                    return "B";
                }
        ));
        futureList.add(executorService.submit(
                ()->{
                    //眠3s后执行自动恢复执行
                    LockSupport.parkUntil(System.currentTimeMillis()+3000);
                    return "C";
                }
        ));
        futureList.add(executorService.submit(
                ()->{
                    return "D";
                }
        ));
        for (Future future:futureList){
            System.out.println("Future get:"+ LocalTime.now()+"-" +future.get());
        }
        executorService.shutdown();

        /**
         * 二者到底有啥不同呢？Future get() 方法的致命缺陷
         * 如果 Future 结果没有完成，调用 get() 方法，程序会阻塞在那里，直至获取返回结果
         */

        ExecutorService executorService1 = Executors.newFixedThreadPool(4);
        CompletionService completionService = new ExecutorCompletionService<>(executorService1);
        List<Future> futureList1 = new ArrayList<>();
        futureList1.add(completionService.submit(
                ()->{
                    return "A";
                }
        ));
        futureList1.add(completionService.submit(
                ()->{
                    return "B";
                }
        ));
        futureList1.add(completionService.submit(
                ()->{
                    //眠3s后执行自动恢复执行
                    LockSupport.parkUntil(System.currentTimeMillis()+3000);
                    return "C";
                }
        ));
        futureList1.add(completionService.submit(
                ()->{
                    return "D";
                }
        ));
        for (Future future:futureList1){
            System.out.println("Future1 get:"+ LocalTime.now()+"-" +completionService.take().get());
        }
        executorService1.shutdown();
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class Result{
        private String result;
    }

    /**
     * 假设你有一组针对某个问题的solvers，
     * 每个都返回一个类型为Result的值，并且想要并发地运行它们，
     * 处理每个返回一个非空值的结果，在某些方法使用(Result r)
     */
    void solve(Executor e,
               Collection<Callable<Result>> solvers)
            throws InterruptedException, ExecutionException {
        CompletionService<Result> ecs
                = new ExecutorCompletionService<Result>(e);
        for (Callable<Result> s : solvers) {
            ecs.submit(s);
        }
        int n = solvers.size();
        for (int i = 0; i < n; ++i) {
            Result r = ecs.take().get();
            if (r != null) {
                // 逻辑处理
            }
        }
    }

    /**
     * 假设你想使用任务集的第一个非空结果，
     * 忽略任何遇到异常的任务，并在第一个任务准备好时取消所有其他任务
     */
    void solve1(Executor e,
               Collection<Callable<Result>> solvers)
            throws InterruptedException {
        CompletionService<Result> ecs
                = new ExecutorCompletionService<Result>(e);
        int n = solvers.size();
        List<Future<Result>> futures
                = new ArrayList<Future<Result>>(n);
        Result result = null;
        try {
            for (Callable<Result> s : solvers) {
                futures.add(ecs.submit(s));
            }
            for (int i = 0; i < n; ++i) {
                try {
                    Result r = ecs.take().get();
                    if (r != null) {
                        result = r;
                        break;
                    }
                } catch (ExecutionException ignore) {}
            }
        }
        finally {
            for (Future<Result> f : futures)
                // 注意这里的参数给的是 true，详解同样在前序 Future 源码分析文章中
            {
                f.cancel(true);
            }
        }

        if (result != null) {
            // 逻辑处理
        }
    }

}
