package com.demo.dynamic.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/5/18
 * Time: 16:12
 * Description: No Description
 */
public class SqlSessionFactoryInitialization {
    /**
     * 创建动态数据源的SqlSessionFactory，传入的是动态数据源
     * @Primary这个注解很重要，如果项目中存在多个SqlSessionFactory，这个注解一定要加上
     */
    @Primary
    @Bean("sqlSessionFactory2")
    public SqlSessionFactory sqlSessionFactoryBean(DynamicDataSource dynamicDataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dynamicDataSource);
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setDefaultFetchSize(100);
        configuration.setDefaultStatementTimeout(30);
        sqlSessionFactoryBean.setConfiguration(configuration);
        return sqlSessionFactoryBean.getObject();
    }
}
