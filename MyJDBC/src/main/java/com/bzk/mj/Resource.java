package com.bzk.mj;

import java.io.InputStream;

/**
 * @author ：bzk
 * @date ：Created in 2021/3/9 17:08
 * @description：加载配置文件到流中
 * @modified By：
 */
public class Resource {

    //根据配置文件的路径将配置文件加载成字节输入流存储在内存中
    public static InputStream getResourceAsStream(String path){
        InputStream resourceAsStream = Resource.class.getClassLoader().getResourceAsStream(path);
        return resourceAsStream;
    }
}
