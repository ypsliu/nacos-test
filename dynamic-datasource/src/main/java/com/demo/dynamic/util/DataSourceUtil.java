package com.demo.dynamic.util;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/5/18
 * Time: 15:52
 * Description: No Description
 */
public class DataSourceUtil {
    /**
     * 通过字符串获取数据源class对象
     *
     * @param typeStr
     * @return
     */
    public static Class<? extends DataSource> getDataSourceType(String typeStr) {
        Class<? extends DataSource> type;
        try {
            if (StringUtils.hasLength(typeStr)) {
                // 字符串不为空则通过反射获取class对象
                type = (Class<? extends DataSource>) Class.forName(typeStr);
            } else {
                // 默认为hikariCP数据源，与springboot默认数据源保持一致
                type = HikariDataSource.class;
            }
            return type;
        } catch (Exception e) {
            throw new IllegalArgumentException("can not resolve class with type: " + typeStr); //无法通过反射获取class对象的情况则抛出异常，该情况一般是写错了，所以此次抛出一个runtimeexception
        }
    }
}
