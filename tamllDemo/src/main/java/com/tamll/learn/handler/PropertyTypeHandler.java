package com.tamll.learn.handler;

import com.tamll.learn.entiy.Property;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * MyBatis工具类，用于解决一对多插入问题
 */
@MappedJdbcTypes(JdbcType.INTEGER)
@MappedTypes(Property.class)
public class PropertyTypeHandler extends BaseTypeHandler<Property>{
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Property property, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(2,String.valueOf(property.getProperty_Id()));
    }

    public Property getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return null;
    }

    public Property getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return null;
    }

    public Property getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return null;
    }
}
