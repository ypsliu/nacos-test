package com.demo.thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/3/2
 * Time: 10:41
 * Description: No Description
 */
@Slf4j
public class FutureTaskDemo {
    //线程池最好作为全局变量, 若作为局部变量记得用完后shutdown()
    ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("thread-start-runner-%d").build();
    ExecutorService taskExe= new ThreadPoolExecutor(
            10,
            20,
            800L,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(100),
            namedThreadFactory);

    int count=0;
    public void test(String[] args) {

        //任务列表
        List<FutureTask<Integer>> taskList=new ArrayList<>();
        for(int i=0;i<100;i++){
            //创建100个任务放入【任务列表】
            FutureTask<Integer> futureTask=new FutureTask<Integer>(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    return 1;
                }
            });
            //执行的结果装回原来的FutureTask中,后续直接遍历集合taskList来获取结果即可
            taskList.add(futureTask);
            taskExe.submit(futureTask);
        }
        //获取结果
        try{
            for(FutureTask<Integer> futureTask:taskList){
                count+=futureTask.get();
            }
        } catch (InterruptedException e) {
            log.error("线程执行被中断",e);
        } catch (ExecutionException e) {
            log.error("线程执行出现异常",e);
        }
        //关闭线程池
        taskExe.shutdown();
        //打印: 100
        System.out.println(count);
    }
}
