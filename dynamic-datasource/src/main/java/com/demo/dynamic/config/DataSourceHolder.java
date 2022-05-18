package com.demo.dynamic.config;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/5/18
 * Time: 13:46
 * Description: 使用ThreadLocal存储切换数据源后的KEY
 */
public class DataSourceHolder {
    // 线程 本地环境
    private static final ThreadLocal<String> dataSources = new InheritableThreadLocal<>();

    //设置数据源
    public static void setDataSource(String datasource) {
        dataSources.set(datasource);
    }

    //获取数据源
    public static String getDataSource() {
        return dataSources.get();
    }

    //清除数据源
    public static void clearDataSource() {
        dataSources.remove();
    }
}
