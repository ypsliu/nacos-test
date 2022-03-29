package com.demo.provider;

import com.gyx.superscheduled.core.SuperScheduledManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/3/25
 * Time: 10:15
 * Description: No Description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {
    //直接注入管理器
    @Autowired
    private SuperScheduledManager superScheduledManager;

    @Test
    public void test() {
        //获取所有定时任务
        List<String> allSuperScheduledName = superScheduledManager.getAllSuperScheduledName();
        System.out.println(allSuperScheduledName);
        String name = allSuperScheduledName.get(0);
        //终止定时任务
        superScheduledManager.cancelScheduled(name);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("任务名：" + name);
        //启动定时任务
        superScheduledManager.addCronScheduled(name, "0/2 * * * * ?");
        //获取启动汇总的定时任务
        List<String> runScheduledName = superScheduledManager.getRunScheduledName();
        runScheduledName.forEach(System.out::println);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //修改定时任务执行周期
        superScheduledManager.setScheduledCron(name, "0/5 * * * * ?");
    }

}
