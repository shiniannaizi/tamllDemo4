package com.tamll.learn.handler;

import com.tamll.learn.entiy.Category;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.BaseTypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * MyBatis工具类，用于解决一对多插入问题
 */
@MappedJdbcTypes(JdbcType.INTEGER)
@MappedTypes(Category.class)
public class CategoryTypeHanlder extends BaseTypeHandler<Category> {
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Category category,
                                    JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(2,String.valueOf(category.getCategory_Id()));
    }

    public Category getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return null;
    }

    public Category getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return null;
    }

    public Category getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return null;
    }
}
