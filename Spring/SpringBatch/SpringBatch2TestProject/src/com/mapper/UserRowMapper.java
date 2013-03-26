package com.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.bean.User;

public class UserRowMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		User user = new User();
		user.setId(resultSet.getString("USER_ID"));
		user.setName(resultSet.getString("USER_NAME"));
		user.setName(resultSet.getString("USER_LASTNAME"));
		return user;
	}

}
