package com.sun.dao;

import com.sun.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BaseDao<T> {

    private QueryRunner runner=new QueryRunner();

    /**
     * 定义一个变量来接受泛型的类型
     */
    private Class<T> type;

    /**
     * 获取T的Class对象，获取泛型的类型，泛型是在被子类继承时才确定的
     */
    public BaseDao() {
        //获取子类的类型
        Class<? extends BaseDao> clazz = this.getClass();
        //获取当前类的父类
        //getGenericSuperclass()用来获取当前类的父类的类型
        //paramterizedType表示带泛型的类型
        ParameterizedType ParameterizedType=(ParameterizedType)clazz.getGenericSuperclass();
        //获取具体的泛型类型，getActualTypeArguments()会返回一个Type的数组，代表泛型的类型
        Type[] types=ParameterizedType.getActualTypeArguments();
        //获取具体的泛型类型
        type=(Class<T>)types[0];
        System.out.println(type);
    }

    /**
     * 获取一个结果对象
     * @return
     */
    public T getBean(String sql ,Object ...params){
        Connection connection = JdbcUtils.getConnection();
        T query=null;
        try {
            query=runner.query(connection,sql,new BeanHandler<>(type),params);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.releaseConnection(connection);
        }
        return query;
    }

    /**
     * 获取结果对象的集合
     * @return List<T>
     */
    public List<T> getBeanList(String sql ,Object ...params){
        Connection connection=JdbcUtils.getConnection();
        List<T> list=new ArrayList<>();
        try {
            list=runner.query(connection,sql,new BeanListHandler<>(type),params);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.releaseConnection(connection);
        }
        return list;
    }

    /**
     * 执行增删改查
     * @return int 返回受影响的行数
     */
    public int update(String sql,Object ...params){
        Connection connection=JdbcUtils.getConnection();
        int count=0;
        try {
            count=runner.update(connection,sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.releaseConnection(connection);
        }
        return count;
    }

}
