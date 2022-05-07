package com.demo.provider.transcation;

import com.demo.provider.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/5/7
 * Time: 14:19
 * Description: No Description
 */
public class TransactionDemo1 {
    /**
     * 解决方法二
     * 如果不想再新加一个Service类，在该Service类中注入自己也是一种选择
     * spring ioc内部的三级缓存保证了它，不会出现循环依赖问题
     */
    @Autowired
    private TransactionDemo1 TransactionDemo1;


    public void save(User user) {
//        queryData1();
//        queryData2();
        doSave();

    }


    @Transactional(rollbackFor=Exception.class)
    public void doSave() {
//        addData1();
//        updateData2();
    }
}
