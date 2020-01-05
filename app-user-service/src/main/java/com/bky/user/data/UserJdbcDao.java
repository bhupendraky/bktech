package com.bky.user.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.bky.user.entity.User;

@Repository
public class UserJdbcDao {


	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<User> findAll(){
		RowMapper<User> rowMapper = new UserEntityRowMapper();
		return jdbcTemplate.query("select * from user", rowMapper);
	}

	public User findById(Long id){
		RowMapper<User> rowMapper = new UserEntityRowMapper();
		return jdbcTemplate.queryForObject("select * from user where id=?",
				new Object[] {id}, rowMapper);
	}

	public int deleteById(Long id){
		return jdbcTemplate.update("delete from user where id=?", new Object[] {id});
	}

	public int insert(User user) {
		return jdbcTemplate.update("insert into user (id, name, updated_by, updated_on) " +
				"values(?, ?, ?, ?)",
				new Object[] {
						user.getId(),
						user.getName(),
						user.getUpdatedBy(),
						user.getUpdatedOn()
		});
	}

	public int update(User user) {
		return jdbcTemplate.update("update user "
				+ "set name=?, updated_by=?, updated_on=? " +
				"where id=?",
				new Object[] {
						user.getName(),
						user.getUpdatedBy(),
						user.getUpdatedOn(),
						user.getId()
				});
	}
}
