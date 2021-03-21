package com.bzk.sqlSession;

import com.bzk.pojo.Configuration;

/**
 * @author ：bzk
 * @date ：Created in 2021/3/17 14:43
 * @description：SqlSessionFactory实现类
 * @modified By：
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {
    private Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }
}
