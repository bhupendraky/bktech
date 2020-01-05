package com.bky.user.data;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.bky.user.entity.User;

public class UserEntityRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		user.setId(rs.getLong("id"));
		user.setName(rs.getString("name"));

		user.setCreatedBy(rs.getString("created_by"));
		user.setCreatedOn(rs.getDate("created_on"));
		user.setUpdatedBy(rs.getString("updated_by"));
		user.setUpdatedOn(rs.getDate("updated_on"));
		return user;
	}

}
