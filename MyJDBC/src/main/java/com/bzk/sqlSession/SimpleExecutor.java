package com.bzk.sqlSession;

import com.bzk.config.BoundSql;
import com.bzk.pojo.Configuration;
import com.bzk.pojo.MappedStatement;
import com.bzk.utils.GenericTokenParser;
import com.bzk.utils.ParameterMapping;
import com.bzk.utils.ParameterMappingTokenHandler;

import javax.sql.DataSource;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ：bzk
 * @date ：Created in 2021/3/17 15:03
 * @description：调用JDBC实现类
 * @modified By：
 */
public class SimpleExecutor implements Executor {
    public <E> List<E> query(Configuration configuration, MappedStatement mappedStatement, Object... params) throws SQLException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException, IntrospectionException, InstantiationException, InvocationTargetException {
        //1.注册驱动，获取链接
        DataSource dataSource = configuration.getDataSource();
        Connection connection = dataSource.getConnection();
        //2.获取SQL语句 select * from user where id=#{id} and username=#{username}
            //转换sql语句  select * from user where id=? and username=?
            //转换的过程中需要对#{}中的值进行解析
        String sql = mappedStatement.getSql();
        BoundSql boundSql = getBoundSql(sql);
        //3.获取预处理对象
        PreparedStatement preparedStatement = connection.prepareStatement(boundSql.getSqlText());
        //4.设置参数
            //获取参数全路径
        String paramterType = mappedStatement.getParamterType();
        Class<?> paramterClass =  getClassType(paramterType);
        List<ParameterMapping> parameterMappingList = boundSql.getParameterMappingList();
        for (int i = 0; i <parameterMappingList.size() ; i++) {
            ParameterMapping parameterMapping = parameterMappingList.get(i);
            String content = parameterMapping.getContent();
            //使用反射，根据参数名称获取User实体中对应的属性，通过属性获取属性值
            Field declaredField = paramterClass.getDeclaredField(content);
            //设置暴力访问
            declaredField.setAccessible(true);
            //属性值注入
            Object o = declaredField.get(params[0]);
            preparedStatement.setObject(i+1,o);

        }
        //5.执行sql
        ResultSet resultSet = preparedStatement.executeQuery();
        String resultType = mappedStatement.getResultType();
        Class<?> resultTypeClass = getClassType(resultType);
        ArrayList<Object> objects = new ArrayList<Object>();
        //6.封装返回结果集
        while(resultSet.next()){
            Object o = resultTypeClass.newInstance();
            //元数据
            ResultSetMetaData metaData = resultSet.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                String catalogName = metaData.getCatalogName(i);
                //获取到字段的值
                Object object = resultSet.getObject(catalogName);
                //使用反射或者内省，根据数据库表和实体对应关系完成封装
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(catalogName, resultTypeClass);
                Method writeMethod = propertyDescriptor.getWriteMethod();
                writeMethod.invoke(o,object);
            }
            objects.add(o);
        }
        return (List<E>) objects;
    }

    private Class<?> getClassType(String paramterType) throws ClassNotFoundException {
        if(null != paramterType){
            Class<?> aClass = Class.forName(paramterType);
            return aClass;
        }
        return null;
    }

    /**
     * 将#{}使用？进行代替
     * 解析出#{}里面的值进行存储
     * @param sql
     * @return
     */
    private BoundSql getBoundSql(String sql) {
        ParameterMappingTokenHandler parameterMappingTokenHandler = new ParameterMappingTokenHandler();
        GenericTokenParser genericTokenParser = new GenericTokenParser("#{", "}", parameterMappingTokenHandler);
        String parseSql = genericTokenParser.parse(sql);
        //#{}解析出来的参数名称
        List<ParameterMapping> parameterMappings = parameterMappingTokenHandler.getParameterMappings();
        BoundSql boundSql = new BoundSql(parseSql,parameterMappings);
        return boundSql;
    }
}
