package com.demo.config;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.actuate.autoconfigure.jdbc.DataSourceHealthContributorAutoConfiguration;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.jdbc.DataSourceHealthIndicator;
import org.springframework.boot.jdbc.metadata.DataSourcePoolMetadataProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/2/22
 * Time: 16:55
 * Description: sharding  数据源健康监测异常处理
 */
//@Configuration
//public class DataSourceHealthConfig extends DataSourceHealthContributorAutoConfiguration {
//
//    private String defaultQuery = "select 1";
//
//
//    public DataSourceHealthConfig(Map<String, DataSource> dataSources, ObjectProvider<DataSourcePoolMetadataProvider> metadataProviders) {
//        super(dataSources, metadataProviders);
//    }
//
//    @Override
//    protected AbstractHealthIndicator createIndicator(DataSource source) {
//        DataSourceHealthIndicator indicator = (DataSourceHealthIndicator) super.createIndicator(source);
//        if (!StringUtils.hasText(indicator.getQuery())) {
//            indicator.setQuery(defaultQuery);
//        }
//        return indicator;
//    }
//}
