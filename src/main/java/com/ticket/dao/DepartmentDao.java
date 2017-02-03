package com.ticket.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.ticket.model.Department;
import com.ticket.util.ConnectionUtil;

public class DepartmentDao implements Dao<Department> {
	private JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();

	public void save(Department d) {
		String sql = "INSERT INTO DEPARTMENTS (NAME) VALUES (?)";
		Object[] params = { d.getName()};
		jdbcTemplate.update(sql, params);
	}

	public void update(Department d) {
		String sql = "UPDATE DEPARTMENTS SET NAME=?,ACTIVE=? WHERE ID = ?";
		Object[] params = {};
		jdbcTemplate.update(sql, params);

	}

	public void delete(int id) {
		String sql = "DELETE FROM DEPARTMENTS WHERE ID=?";
		Object[] params = { id };
		jdbcTemplate.update(sql, params);

	}

	public Department findOne(int id) {
		String sql = "SELECT ID,NAME,ACTIVE FROM DEPARTMENTS WHERE ID= ?";
		Object[] params = { id };
		return jdbcTemplate.queryForObject(sql, params, (rs, rowNo) -> convert(rs));
	}

	public List<Department> findAll() {
		String sql = "SELECT ID, NAME,ACTIVE FROM DEPARTMENTS";
		return jdbcTemplate.query(sql, (rs, rowNo) -> convert(rs));
	}

	private Department convert(ResultSet rs) throws SQLException {
		Department d = new Department();
		d.setId(rs.getInt("ID"));
		d.setName(rs.getString("NAME"));
		d.setActive(rs.getBoolean("ACTIVE"));
		return d;
	}
	

}