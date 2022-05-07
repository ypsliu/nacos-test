package com.demo.provider.transcation;

import com.demo.provider.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/5/7
 * Time: 14:05
 * Description: 编程式 事务
 */
public class TransactionTemplateDemo {

    @Autowired
    private TransactionTemplate transactionTemplate;

    public void save( User user) {
        boolean success = (boolean) transactionTemplate.execute(new TransactionCallback() {
            @Override
            public Object doInTransaction(TransactionStatus transactionStatus) {
                return Boolean.TRUE;
            }
        });
    }
}
