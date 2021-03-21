package com.bzk.dao;

import com.bzk.pojo.User;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author ：bzk
 * @date ：Created in 2021/3/17 16:44
 * @description：
 * @modified By：
 */
public interface IUserDao {
    public List<User> findAll() throws IllegalAccessException, IntrospectionException, InstantiationException, NoSuchFieldException, SQLException, InvocationTargetException, ClassNotFoundException;

    public User findByCondition(User user) throws IllegalAccessException, IntrospectionException, InstantiationException, NoSuchFieldException, SQLException, InvocationTargetException, ClassNotFoundException;

}
