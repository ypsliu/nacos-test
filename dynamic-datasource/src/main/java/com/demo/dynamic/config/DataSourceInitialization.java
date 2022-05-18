package com.demo.dynamic.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.demo.dynamic.constant.Constant;
import com.demo.dynamic.util.DataSourceUtil;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/5/18
 * Time: 14:01
 * Description: 初始化连接池
 */
@Configuration
public class DataSourceInitialization {
    /**
     * @Bean：向IOC容器中注入一个Bean
     * @ConfigurationProperties：使得配置文件中以spring.datasource为前缀的属性映射到Bean的属性中
     */
    @ConfigurationProperties(prefix = "spring.datasource.lzy-master")
    @Bean("masterDataSource")
    public DataSource masterDataSource(){
        return new DruidDataSource();
    }

    /**
     * 向IOC容器中注入另外一个数据源
     */
    @ConfigurationProperties(prefix = "spring.datasource.lzy-salve")
    @Bean("salveDataSource")
    public DataSource salveDataSource() {
        return new DruidDataSource();
    }

    @Bean(name = "dynamicDataSource")
    // @Primary：当有多个同类型bean时，该bean生效(因此这里创建了三个DataSource类型的bean，名为dynamicDataSource的bean先生效)
    @Primary
    public DynamicDataSource dataSource(DataSource masterDataSource,DataSource salveDataSource)
    {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(Constant.MASTER_DATA_SOURCE, masterDataSource);
        // 从数据源的方法名是slaveDataSource(方法名即beanName)
        targetDataSources.put(Constant.SALVE_DATA_SOURCE, salveDataSource);
        // 将key为MASTER，value为主数据源、key为SLAVE，value为从数据源的数据添加到targetDataSources中
        // 创建自定义的动态数据源类
        return new DynamicDataSource(masterDataSource, targetDataSources);
    }


}
