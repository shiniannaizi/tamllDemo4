package com.tamll.learn.handler;

import com.tamll.learn.entiy.Product;
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
@MappedJdbcTypes(JdbcType.BIGINT)
@MappedTypes(Product.class)
public class ProductTypeHandler extends BaseTypeHandler<Product>{
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Product product, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(3,String.valueOf(product.getProduct_Id()));
    }

    public Product getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return null;
    }

    public Product getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return null;
    }

    public Product getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return null;
    }
}
