package com.bzk.sqlSession;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author ：bzk
 * @date ：Created in 2021/3/17 14:46
 * @description：SqlSession接口
 * @modified By：
 */
public interface SqlSession {
    /**
     * 查询所有
     * statementid:sql唯一标识
     * Object... params:可变参数
     */
    public <E> List<E> selectList(String statementid,Object... params) throws IllegalAccessException, IntrospectionException, InstantiationException, NoSuchFieldException, SQLException, InvocationTargetException, ClassNotFoundException;
    /**
     * 根据条件查询单个
     * statementid:sql唯一标识
     * Object... params:可变参数
     */
    public <T> T selectOne(String statementid,Object... params) throws IllegalAccessException, ClassNotFoundException, IntrospectionException, InstantiationException, SQLException, InvocationTargetException, NoSuchFieldException;

    public <T> T getMapper(Class<?> mapperClass);
}
