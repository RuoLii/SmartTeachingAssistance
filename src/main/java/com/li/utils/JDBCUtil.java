package com.li.utils;


import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

/**
 * JDBC 工具类
 * 1、维护一个连接池对象、维护了一个线程绑定变量的 ThreadLocal 对象
 * 2、对外提供在 ThreadLocal 中获取连接的方法
 * 3、对外提供回收连接的方法，回收过程中，将要回收的连接从 ThreadLocal 中移除
 */
public class JDBCUtil {
    private static DataSource dataSource;
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    //  在项目启动时，创建连接池对象，赋值给 dataSource
    static {
        try {
            Properties properties = new Properties();
            InputStream resourceAsStream = JDBCUtil.class.getClassLoader().getResourceAsStream("jdbc-Config.properties");
            properties.load(resourceAsStream);
            //  创建连接池对象
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        //  在 ThreadLocal 中获取连接池对象
        try {
            Connection connection = threadLocal.get();
            if (connection == null) {
                connection = dataSource.getConnection();
                threadLocal.set(connection);
            }
            return connection;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void release() {
        try {
            Connection connection = threadLocal.get();
            if (connection != null) {
                //  从 threadLocal中移除当前已经存储的 Connection 对象
                threadLocal.remove();
                //  将 connection 对象归还连接
                connection.close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
