package com.bzk.sqlSession;

import com.bzk.pojo.Configuration;

import java.beans.IntrospectionException;
import java.lang.reflect.*;
import java.sql.SQLException;
import java.util.List;

/**
 * @author ：bzk
 * @date ：Created in 2021/3/17 14:47
 * @description：
 * @modified By：
 */
public class DefaultSqlSession implements SqlSession {
    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    public <E> List<E> selectList(String statementid, Object... params) throws IllegalAccessException, IntrospectionException, InstantiationException, NoSuchFieldException, SQLException, InvocationTargetException, ClassNotFoundException {
        //完成对simpleExecutor中的query方法的调用
        SimpleExecutor simpleExecutor = new SimpleExecutor();
        List<Object> query = simpleExecutor.query(configuration, configuration.getMappedStatementMap().get(statementid), params);

        return (List<E>) query;
    }

    public <T> T selectOne(String statementid, Object... params) throws IllegalAccessException, ClassNotFoundException, IntrospectionException, InstantiationException, SQLException, InvocationTargetException, NoSuchFieldException {
        List<Object> objects = selectList(statementid, params);
        if(objects.size()==1){
            return (T) objects.get(0);
        }else{
            throw new RuntimeException("查询结果为空或返回结果过多！");
        }
    }

    /**
     * 使用JDK动态代理为Dao接口生成代理对象并返回
     * @param mapperClass
     * @param <T>
     * @return
     */
    @Override
    public <T> T getMapper(Class<?> mapperClass) {
        Object proxyInstance = Proxy.newProxyInstance(DefaultSqlSession.class.getClassLoader(), new Class[]{mapperClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //底层执行JDBC代码--根据不同情况调用selectList/selectOne
                //参数：1.statementId 2.方法名 3.args
                String methodName = method.getName();
                String className = method.getDeclaringClass().getName();
                String statementId = className+"."+methodName;
                //判断返回类型决定执行哪个方法
                Type genericReturnType = method.getGenericReturnType();
                //判断是否进行了泛型类型参数化
                if(genericReturnType instanceof ParameterizedType){
                    List<Object> objects = selectList(statementId, args);
                    return objects;
                }
                return selectOne(statementId,args);
            }
        });
        return (T) proxyInstance;
    }


}
