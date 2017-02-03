package com.ticket.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.ticket.model.Department;
import com.ticket.model.Employee;
import com.ticket.model.Role;
import com.ticket.util.ConnectionUtil;

public class EmployeeDao implements Dao<Employee> {
	private JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();

	public void save(Employee e) {
		String sql = "INSERT INTO EMPLOYEES (DEPARTMENT_ID,ROLE_ID,NAME,EMAIL_ID,PASSWORD,ACTIVE) VALUES (?,?,?,?,?,?)";
		Object[] params = { e.getDepartmentId().getId(),e.getRoleId().getId(), e.getName(), e.getEmailId(), e.getPassword(), e.isActive() };
		jdbcTemplate.update(sql, params);
	}

	public void update(Employee e) {
		String sql = "UPDATE EMPLOYEES SET DEPARTMENT_ID=?,ROLE_ID=?,NAME=?,EMAIL_ID=?,PASSWORD=?,ACTIVE=? WHERE ID = ?";
		Object[] params = { e.getDepartmentId().getId(),e.getRoleId().getId(), e.getName(), e.getEmailId(), e.getPassword(), e.isActive(),
				e.getId() };
		jdbcTemplate.update(sql, params);

	}

	public void delete(int id) {
		String sql = "DELETE FROM EMPLOYEES WHERE ID=?";
		Object[] params = { id };
		jdbcTemplate.update(sql, params);

	}

	public Employee findOne(int id) {
		String sql = "SELECT ID,DEPARTMENT_ID,ROLE_ID,NAME,EMAIL_ID,PASSWORD,ADDRESS,ACTIVE FROM EMPLOYEES WHERE ID= ?";
		Object[] params = { id };
		return jdbcTemplate.queryForObject(sql, params, (rs, rowNo) -> convert(rs));
	}

	public List<Employee> findAll() {
		String sql = "SELECT ID,DEPARTMENT_ID,ROLE_ID,NAME,EMAIL_ID,PASSWORD,ACTIVE FROM EMPOLYEES";
		return jdbcTemplate.query(sql, (rs, rowNo) -> convert(rs));
	}

	private Employee convert(ResultSet rs) throws SQLException {
		Employee e = new Employee();
		Department d = new Department();
		Role r=new Role();
		d.setId(rs.getInt("ID"));
		r.setId(rs.getInt("ID"));
		e.setId(rs.getInt("ID"));
		e.setDepartmentId(d);
		e.setRoleId(r);
		e.setName(rs.getString("NAME"));
		e.setEmailId(rs.getString("EMAIL_ID"));
		e.setPassword(rs.getString("PASSWORD"));
		e.setActive(rs.getBoolean("ACTIVE"));
		return e;
	}

}
