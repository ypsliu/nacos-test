package com.demo.dynamic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/5/18
 * Time: 16:13
 * Description: No Description
 */
public class TransactionManagerInitialization {
    @Primary
    @Bean(value = "transactionManager2")
    public PlatformTransactionManager annotationDrivenTransactionManager(DynamicDataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
