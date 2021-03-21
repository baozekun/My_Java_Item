package com.bzk.JTest;

import com.bzk.dao.IUserDao;
import com.bzk.mj.Resource;
import com.bzk.pojo.User;
import com.bzk.sqlSession.SqlSession;
import com.bzk.sqlSession.SqlSessionFactory;
import com.bzk.sqlSession.SqlSessionFactoryBuilder;

import java.beans.IntrospectionException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author ：bzk
 * @date ：Created in 2021/3/9 17:24
 * @description：测试接口
 * @modified By：
 */
public class JTest {
    public static void main(String[] args) throws IllegalAccessException, IntrospectionException, InstantiationException, NoSuchFieldException, SQLException, InvocationTargetException, ClassNotFoundException {
        InputStream resourceAsStream = Resource.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = build.openSession();
        //调用
        User user = new User();
        user.setId("1");
        user.setUsername("张三");
//        User user1 = sqlSession.selectOne("user.selectOne",user);
//        System.out.println(user1);
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        List<User> all = userDao.findAll();
    }
}
