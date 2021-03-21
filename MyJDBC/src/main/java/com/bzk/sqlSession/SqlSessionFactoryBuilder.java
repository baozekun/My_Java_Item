package com.bzk.sqlSession;

import com.bzk.config.XMLConfigBuilder;
import com.bzk.pojo.Configuration;
import com.sun.org.apache.xerces.internal.xni.parser.XMLConfigurationException;

import java.io.InputStream;

/**
 * @author ：bzk
 * @date ：Created in 2021/3/12 17:23
 * @description：sqlSession工厂类
 * @modified By：
 */
public class SqlSessionFactoryBuilder {
    public SqlSessionFactory build(InputStream in){
        //使用dom4j解析配置文件，将解析出来的内容封装到Configuration中
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder();
        Configuration configuration = xmlConfigBuilder.parseConfig(in);
        //创建sqlSessionFactory对象---工厂类：生产sqlSession会话对象
        DefaultSqlSessionFactory defaultSqlSessionFactory = new DefaultSqlSessionFactory(configuration);



        return null;
    }
}
