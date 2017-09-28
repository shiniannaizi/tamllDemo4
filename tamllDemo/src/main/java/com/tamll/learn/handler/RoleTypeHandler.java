package com.tamll.learn.handler;

import com.tamll.learn.entiy.Role;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedJdbcTypes(JdbcType.INTEGER)
@MappedTypes(Role.class)
public class RoleTypeHandler extends BaseTypeHandler<Role> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Role role,
                                    JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(1,String.valueOf(role.getRole_Id()));
    }

    @Override
    public Role getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return null;
    }

    @Override
    public Role getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return null;
    }

    @Override
    public Role getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return null;
    }
}
