package com.tamll.learn.handler;

import com.tamll.learn.entiy.Order;
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
@MappedJdbcTypes(JdbcType.VARCHAR)
@MappedTypes(Order.class)
public class OrderTypeHandler extends BaseTypeHandler<Order>{
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Order order,
                                    JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(4,order.getOrder_Number());
    }

    public Order getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return null;
    }

    public Order getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return null;
    }

    public Order getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return null;
    }
}
