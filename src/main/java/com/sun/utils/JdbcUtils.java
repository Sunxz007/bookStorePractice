package com.sun.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
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
     * 存储数据库连接的各个线程信息，保证每一次请求只有一个线程
     */
    private static Map<Long,Connection>conns=new HashMap<>();

    /**
     * 获取数据库连接实例
     * @return Connection
     */
    public static Connection getConnection() {

        long id=Thread.currentThread().getId();
        System.out.println("JDBC当前连接线程："+id);
        //获取当前线程的连接
        Connection connection=conns.get(Thread.currentThread().getId());

        if (connection==null) {
            //如果当前线程没有connection，则创建一个
            try {
                connection= ds.getConnection();
                connection.setAutoCommit(false);
                //连接存储到map中，保证一次连接的一个线程只有一个connection
                conns.put(id,connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    /**
     * 释放数据库连接
     */
    public static void releaseConnection(){
        //获取当前线程的连接
        Connection connection=getConnection();
        try {
            if(connection!=null){
                connection.close();
                Long id=Thread.currentThread().getId();
                //删除当前线程信息
                conns.remove(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
