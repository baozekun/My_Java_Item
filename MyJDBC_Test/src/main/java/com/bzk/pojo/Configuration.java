package com.bzk.pojo;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ：bzk
 * @date ：Created in 2021/3/12 17:14
 * @description：
 * @modified By：
 */
public class Configuration {

    //数据源对象
    private DataSource dataSource;
    /**
     *  Map<String,MappedStatement>:
     *  key: statementid=namespace+id  配置文件中的标识
     *  value : MappedStatement对象
     */
    Map<String,MappedStatement> mappedStatementMap = new HashMap<String, MappedStatement>();
}
