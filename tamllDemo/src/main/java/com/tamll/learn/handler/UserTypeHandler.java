package com.tamll.learn.handler;

import com.tamll.learn.entiy.User;
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
 * 当为用户插入一天收货信息时，Recive对象中包含一个User对象
 * 数据库需要接收的只是user_id,本类的作用即是:
 * 在SQL语句执行之前,从服务层传过来的User对象中获取user_id,
 * 并把它作为参数传入SQL语句中，从而进行正确的插入
 */
@MappedJdbcTypes(JdbcType.INTEGER)
@MappedTypes(User.class)
public class UserTypeHandler extends BaseTypeHandler<User> {

    public void setNonNullParameter(PreparedStatement preparedStatement, int i, User user,
                                    JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(3,String.valueOf(user.getUser_Id()));
    }

    public User getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return new User();
    }

    public User getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return new User();
    }

    public User getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return new User();
    }
}
