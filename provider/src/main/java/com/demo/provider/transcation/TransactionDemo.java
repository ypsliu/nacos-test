package com.demo.provider.transcation;

import com.demo.provider.entity.User;
import org.springframework.aop.framework.AopContext;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/5/7
 * Time: 14:19
 * Description: No Description
 */
public class TransactionDemo {

    public void save(User user) {
//        queryData1();
//        queryData2();
        doSave();
        // 事务会失效
        /*
        因为@Transactional注解的声明式事务是通过spring aop起作用的，
        而spring aop需要生成代理对象，直接方法调用使用的还是原始对象，所以事务不会生效
         */

        /**
         * 解决方法一 写新的service
         */

        /**
         * 解决方法三
         */
        ((TransactionDemo) AopContext.currentProxy()).doSave();
    }


    @Transactional(rollbackFor=Exception.class)
    public void doSave() {
//        addData1();
//        updateData2();
    }
}
