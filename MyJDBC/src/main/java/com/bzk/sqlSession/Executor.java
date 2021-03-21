package com.bzk.sqlSession;

import com.bzk.pojo.Configuration;
import com.bzk.pojo.MappedStatement;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author ：bzk
 * @date ：Created in 2021/3/17 15:02
 * @description：调用JDBC接口
 * @modified By：
 */
public interface Executor {
    public <E> List<E> query(Configuration configuration, MappedStatement mappedStatement,Object... params) throws SQLException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException, IntrospectionException, InstantiationException, InvocationTargetException;
}
