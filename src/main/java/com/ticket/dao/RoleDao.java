package com.ticket.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.ticket.model.Role;
import com.ticket.util.ConnectionUtil;

public class RoleDao implements Dao<Role> {
	private JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();

	public void save(Role r) {
		String sql = "INSERT INTO ROLES (NAME) VALUES (?)";
		Object[] params = { r.getName() };
		jdbcTemplate.update(sql, params);
	}

	public void update(Role r) {
		String sql = "UPDATE ROLES SET NAME=?,ACTIVE=? WHERE ID = ?";
		Object[] params = { r.getName(), r.isActive(), r.getId() };
		jdbcTemplate.update(sql, params);

	}

	public void delete(int id) {
		String sql = "DELETE FROM ROLES WHERE ID=?";
		Object[] params = { id };
		jdbcTemplate.update(sql, params);

	}

	public Role findOne(int id) {
		String sql = "SELECT ID, NAME,ACTIVE FROM ROLES WHERE ID= ?";
		Object[] params = { id };
		return jdbcTemplate.queryForObject(sql, params, (rs, rowNo) -> convert(rs));
	}

	public List<Role> findAll() {
		String sql = "SELECT ID, NAME,ACTIVE FROM ROLES";
		return jdbcTemplate.query(sql, (rs, rowNo) -> convert(rs));
	}

	private Role convert(ResultSet rs) throws SQLException {
		Role r = new Role();
		r.setId(rs.getInt("ID"));
		r.setName(rs.getString("NAME"));
		r.setActive(rs.getBoolean("ACTIVE"));
		return r;
	}

}
