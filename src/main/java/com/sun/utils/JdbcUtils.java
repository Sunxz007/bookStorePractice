package com.sun.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 获取和释放数据库连接
 * @author sun
 */
public class JdbcUtils {
    private static Properties properties =new Properties();
    private static DataSource ds=null;
    static {
        InputStream is=JdbcUtils.class.getClassLoader().getResourceAsStream("druid.properties");
        try {
            if (is != null) {
                properties.load(is);
                ds=DruidDataSourceFactory.createDataSource(properties);
                is.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接实例
     * @return Connection
     */
    public static Connection getConnection() {
        Connection connection=null;
        try {
            connection= ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 释放数据库连接
     * @param connection 数据库连接类实例
     */
    public static void releaseConnection(Connection connection){
        try {
            if(connection!=null){
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
