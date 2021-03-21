package com.bzk.sqlSession;

/**
 * @author ：bzk
 * @date ：Created in 2021/3/12 17:25
 * @description：sqlSession工厂类
 * @modified By：
 */
public interface SqlSessionFactory {
    public SqlSession openSession();

}
