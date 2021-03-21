package com.bzk.dao.impl;

import com.bzk.dao.IUserDao;
import com.bzk.mj.Resource;
import com.bzk.pojo.User;
import com.bzk.sqlSession.SqlSession;
import com.bzk.sqlSession.SqlSessionFactory;
import com.bzk.sqlSession.SqlSessionFactoryBuilder;

import java.beans.IntrospectionException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;
import java.sql.SQLException;
import java.util.List;

/**
 * @author ：bzk
 * @date ：Created in 2021/3/17 16:45
 * @description：
 * @modified By：
 */
public class UserDao implements IUserDao {
    public List<User> findAll() throws IllegalAccessException, IntrospectionException, InstantiationException, NoSuchFieldException, SQLException, InvocationTargetException, ClassNotFoundException {
        InputStream resourceAsStream = Resource.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = build.openSession();
        //调用
        User user = new User();
        user.setId("1");
        user.setUsername("张三");
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        List<User> all = userDao.findAll();
        return all;
    }

    public User findByCondition(User user) throws IllegalAccessException, IntrospectionException, InstantiationException, NoSuchFieldException, SQLException, InvocationTargetException, ClassNotFoundException {
        InputStream resourceAsStream = Resource.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = build.openSession();
        //调用
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        User user1 = userDao.findByCondition(user);
        return user1;
    }
}
