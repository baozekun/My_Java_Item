package com.bzk.config;


import com.bzk.mj.Resource;
import com.bzk.pojo.Configuration;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.beans.PropertyVetoException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * @author ：bzk
 * @date ：Created in 2021/3/12 17:26
 * @description：XML配置类
 * @modified By：
 */
public class XMLConfigBuilder {

    private Configuration configuration;

    public XMLConfigBuilder() {
    }

    public XMLConfigBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    //该方法就是将配置文件进行解析，封装Configuration的方法
    public Configuration parseConfig(InputStream inputStream){
        //使用dom4j对配置文件进行解析(pom导入依赖)
        try {
            Document read = new SAXReader().read(inputStream);
            //拿到sqlMapConfig.xml配置文件中的根标签<configuration>对象
            Element rootElement = read.getRootElement();
            //获取sqlMapConfig.xml配置文件中所有的property标签对象
            List<Element> list = rootElement.selectNodes("//property");
            Properties properties = new Properties();
            for(Element e : list){
                String name = e.attributeValue("name");
                String value = e.attributeValue("value");
                properties.setProperty(name,value);
            }
            //使用连接池
            ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
            comboPooledDataSource.setDriverClass(properties.getProperty("driver"));
            comboPooledDataSource.setJdbcUrl(properties.getProperty("url"));
            comboPooledDataSource.setUser(properties.getProperty("username"));
            comboPooledDataSource.setPassword(properties.getProperty("password"));

            //将连接池对象封装在configurstion中
            configuration.setDataSource(comboPooledDataSource);

            //mapper.xml的解析：拿到路径，字节输入流，dom4j解析
            List<Element> list1 = rootElement.selectNodes("//mapper");
            for (Element element :list1) {
                String mapperPath = element.attributeValue("resource");
                InputStream resourceAsStream = Resource.getResourceAsStream(mapperPath);
                XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(configuration);
                xmlMapperBuilder.parse(resourceAsStream);//解析封装
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }

        return null;
    }
}
