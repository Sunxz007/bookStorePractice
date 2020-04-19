package com.sun.dao;

import com.sun.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Dao基础查询操作，返回从数据中查询到的具体数据
 * @author sun
 * @param <T> 返回的bean类型
 */
public class BaseDao<T> {

    /**
     * commons.dbutil 查询启动对象
     */
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
        ParameterizedType parameterizedType=(ParameterizedType)clazz.getGenericSuperclass();
        //获取具体的泛型类型，getActualTypeArguments()会返回一个Type的数组，代表泛型的类型
        Type[] types=parameterizedType.getActualTypeArguments();
        //获取具体的泛型类型
        type = (Class<T>) types[0];
        System.out.println(type);
    }

    /**
     * 获取一个结果对象
     * @return 返回一个封装了结果对应的泛型对象
     */
    public T getBean(String sql ,Object ...params){
        Connection connection = JdbcUtils.getConnection();
        T query;
        try {
            query=runner.query(connection,sql,new BeanHandler<>(type),params);
        } catch (SQLException e) {
            throw new RuntimeException("查询发生异常");
        }
        return query;
    }

    /**
     * 获取结果对象的集合
     * @return 返回一组封装了结果对应的泛型对象
     */
    public List<T> getBeanList(String sql ,Object ...params){
        Connection connection=JdbcUtils.getConnection();
        List<T> list;
        try {
            list=runner.query(connection,sql,new BeanListHandler<>(type),params);
        } catch (SQLException e) {
            throw new RuntimeException("批量查询发生异常");
        }
        return list;
    }

    /**
     * 执行增删改查
     * @return int 返回受影响的行数
     */
    public int update(String sql,Object ...params){
        Connection connection=JdbcUtils.getConnection();
        int count;
        try {
            count=runner.update(connection,sql,params);
        } catch (SQLException e) {
            throw new RuntimeException("更新发生异常");
        }
        return count;
    }

    /**
     * 查询单个值
     * @param sql SQL语句
     * @param params 参数值
     * @return 返回单个值
     */
    public Object getSingleValue(String sql ,Object ...params){

        Connection connection;
        Object query;
        try {
            connection = JdbcUtils.getConnection();
            query = runner.query(connection,sql,new ScalarHandler(),params);
        } catch (SQLException e) {
            throw new RuntimeException("查询值发生异常");
        }
        return query;
    }

    /**
     * 批量执行sql语句
     * @param sql sql预处理语句
     * @param params Object[][] 第一位长度代表sql处理的次数，第二位代表处理的可变参数
     */
    public void batch(String sql,Object[][] params){
        Connection connection=JdbcUtils.getConnection();
        try {
            runner.batch(connection,sql,params);
        } catch (SQLException e) {
            throw new RuntimeException("批量更新发生异常");
        }
    }
}
