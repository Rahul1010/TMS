package com.ticket.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ticket.util.ConnectionUtil;
import com.ticket.exception.PersistenceException;
import com.ticket.model.User;

public class UserDao implements Dao<User> {
	private JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();

	public void save(User u) {
		String sql = "INSERT INTO USERS (NAME,EMAIL_ID,PASSWORD,ACTIVE) VALUES (?,?,?,?)";
		Object[] params = { u.getName(), u.getEmailId(), u.getPassword(), u.isActive() };
		jdbcTemplate.update(sql, params);
	}

	public void update(User u) {
		String sql = "UPDATE USERS SET NAME=?,EMAIL_ID=?,PASSWORD=?,ACTIVE=? WHERE ID = ?";
		Object[] params = { u.getName(), u.getEmailId(), u.getPassword(), u.isActive(), u.getId() };
		jdbcTemplate.update(sql, params);

	}

	public void delete(int id) {
		String sql = "DELETE FROM USERS WHERE ID=?";
		Object[] params = { id };
		jdbcTemplate.update(sql, params);

	}

	public User findOne(int id) {
		String sql = "SELECT ID, NAME,EMAIL_ID,PASSWORD,ADDRESS,ACTIVE FROM USERS WHERE ID= ?";
		Object[] params = { id };
		return jdbcTemplate.queryForObject(sql, params, (rs, rowNo) -> convert(rs));
	}

	public User findOne(String emailid) throws PersistenceException {
		try {
			String sql = "SELECT PASSWORD FROM USERS WHERE EMAIL_ID= ?";
			Object[] params = { emailid };
			return jdbcTemplate.queryForObject(sql, params, (rs, rowNo) -> {
				User user = new User();
				user.setPassword(rs.getString("PASSWORD"));
				return user;
			});
		} catch (EmptyResultDataAccessException e) {
			throw new PersistenceException("Wrong EmailId", e);
		}

	}

	public List<User> findAll() {
		String sql = "SELECT ID, NAME,EMAIL_ID,PASSWORD,ACTIVE FROM USERS";
		return jdbcTemplate.query(sql, (rs, rowNo) -> convert(rs));
	}

	private User convert(ResultSet rs) throws SQLException {
		User u = new User();
		u.setId(rs.getInt("ID"));
		u.setName(rs.getString("NAME"));
		u.setEmailId(rs.getString("EMAIL_ID"));
		u.setPassword(rs.getString("PASSWORD"));
		u.setActive(rs.getBoolean("ACTIVE"));
		return u;
	}

	public User findIfPresent(String emailId) {
		String sql = "SELECT EMAIL_ID FROM USERS WHERE EMAIL_ID= ?";
		Object[] params = { emailId };
		return jdbcTemplate.queryForObject(sql, params, (rs, rowNo) -> {
			User user = new User();
			user.setEmailId(rs.getString("EMAIL_ID"));
			return user;
		});

	}

}