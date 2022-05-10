package com.demo.elastic.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: lzy
 * Date: 2022/5/9
 * Time: 16:20
 * Description: No Description
 */
public class DBHelper {
    public static final String url = "jdbc:mysql://192.168.211.136:3306/lagou_position? useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai";
    public static final String name = "com.mysql.cj.jdbc.Driver";
    public static final String user = "root";
    public static final String password = "123456";
    public static Connection conn = null;
    public static Connection getConn() {
        try {
            Class.forName(name);
            conn = DriverManager.getConnection(url, user, password);//获取连接
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
