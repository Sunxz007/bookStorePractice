package com.sun.utils;


import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * web相关工具
 * @author sun
 */
public class WebUtils {

    /**
     * 传入request对象将request对象中的数据封装成对象
     * @param request request对象
     * @param t 需要封装的实例
     * @param <T> 需要封装的对象的类型
     * @return 封装好的对象
     */

    public static<T> T param2bean(HttpServletRequest request,T t){

        Field[] fields=t.getClass().getDeclaredFields();
        for (Field field:fields){
            //获取属性名
            String name = field.getName();
            //在request中获取相应的属性值
            String value = request.getParameter(name);
            //封装对象
            try {
                BeanUtils.setProperty(t,name,value);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return t;
    }

    public static<T> T param2bean2(HttpServletRequest request,T t){
        //populate 将map中的键值对直接映射到javaBean中
        Map map = request.getParameterMap();
        try {
            BeanUtils.populate(t,map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return  t;
    }
}
