package com.bzk.config;

import com.bzk.utils.ParameterMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：bzk
 * @date ：Created in 2021/3/17 15:51
 * @description：存储转换之后的sql语句以及参数
 * @modified By：
 */
public class BoundSql {
    private String sqlText;
    private List<ParameterMapping> parameterMappingList = new ArrayList<ParameterMapping>();

    public BoundSql(String sqlText, List<ParameterMapping> parameterMappingList) {
        this.sqlText = sqlText;
        this.parameterMappingList = parameterMappingList;
    }

    public String getSqlText() {
        return sqlText;
    }

    public void setSqlText(String sqlText) {
        this.sqlText = sqlText;
    }

    public List<ParameterMapping> getParameterMappingList() {
        return parameterMappingList;
    }

    public void setParameterMappingList(List<ParameterMapping> parameterMappingList) {
        this.parameterMappingList = parameterMappingList;
    }
}
