package com.ticket.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ticket.exception.PersistenceException;
import com.ticket.model.Department;
import com.ticket.model.Employee;
import com.ticket.model.Role;
import com.ticket.util.ConnectionUtil;

public class EmployeeDao implements Dao<Employee> {
	private JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();

	public void save(Employee e) {
		String sql = "INSERT INTO EMPLOYEES (DEPARTMENT_ID,ROLE_ID,NAME,EMAIL_ID,PASSWORD,ACTIVE) VALUES (?,?,?,?,?,?)";
		Object[] params = { e.getDepartmentId().getId(), e.getRoleId().getId(), e.getName(), e.getEmailId(),
				e.getPassword(), e.isActive() };
		jdbcTemplate.update(sql, params);
	}

	public void update(Employee e) {
		String sql = "UPDATE EMPLOYEES SET DEPARTMENT_ID=?,ROLE_ID=?,NAME=?,EMAIL_ID=?,PASSWORD=?,ACTIVE=? WHERE ID = ?";
		Object[] params = { e.getDepartmentId().getId(), e.getRoleId().getId(), e.getName(), e.getEmailId(),
				e.getPassword(), e.isActive(), e.getId() };
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
		Role r = new Role();
		d.setId(rs.getInt("DEPARTMENT_ID"));
		r.setId(rs.getInt("ROLE_ID"));
		e.setId(rs.getInt("ID"));
		e.setDepartmentId(d);
		e.setRoleId(r);
		e.setName(rs.getString("NAME"));
		e.setEmailId(rs.getString("EMAIL_ID"));
		e.setPassword(rs.getString("PASSWORD"));
		e.setActive(rs.getBoolean("ACTIVE"));
		return e;
	}

	public Employee findEmployee(int departmentId) {
		String sql = "SELECT ID FROM EMPLOYEES WHERE DEPARTMENT_ID=?";
		Object[] params = { departmentId };
		return jdbcTemplate.queryForObject(sql, params, (rs, rowNo) -> {
			Employee emp = new Employee();
			emp.setId(rs.getInt("ID"));
			return emp;
		});

	}

	public void reassignEmployee(int toempid, int issueid) {
		String sql = "UPDATE SOLUTIONS SET SOLUTIONS.`EMPLOYEE_ID`=? WHERE SOLUTIONS.`ISSUE_ID`=?";
		Object[] params = { toempid, issueid };
		jdbcTemplate.update(sql, params);

	}

	public void deleteTicketFromSolutions(int issueid) {
		String sql = "DELETE FROM SOLUTIONS WHERE ISSUE_ID=?";
		Object[] params = { issueid };
		jdbcTemplate.update(sql, params);

	}

	public void deleteTicketFromIssues(int issueid) {
		String sql = "DELETE FROM ISSUES WHERE ID=?";
		Object[] params = { issueid };
		jdbcTemplate.update(sql, params);

	}

	public Employee findRoleId(String username, String password) {
		String sql = "SELECT EMPLOYEES.`ROLE_ID` FROM EMPLOYEES WHERE EMPLOYEES.`EMAIL_ID`=? AND EMPLOYEES.`PASSWORD`=?";
		Object[] params = { username, password };
		return jdbcTemplate.queryForObject(sql, params, (rs, rowNo) -> {
			Employee emp = new Employee();
			Role role = new Role();
			role.setId(rs.getInt("ROLE_ID"));
			emp.setRoleId(role);
			return emp;
		});

	}

	public Employee findEmployeeEmailId(int employeeId) {
		String sql = "SELECT EMAIL_ID FROM EMPLOYEES WHERE ID=?";
		Object[] params = { employeeId };
		return jdbcTemplate.queryForObject(sql, params, (rs, rowNo) -> {
			Employee employee = new Employee();
			employee.setEmailId(rs.getString("EMAIL_ID"));
			return employee;

		});

	}

	public Employee findOne(String emailId, String password) throws PersistenceException {

		try {
			String sql = "SELECT ID FROM EMPLOYEES WHERE EMAIL_ID = ? AND PASSWORD=? AND ACTIVE=1";
			Object[] params = { emailId, password };
			return jdbcTemplate.queryForObject(sql, params, (rs, rowNo) -> {
				Employee employee = new Employee();
				employee.setId(rs.getInt("ID"));
				return employee;

			});

		} catch (EmptyResultDataAccessException e) {
			throw new PersistenceException("Wrong Email id or Password", e);
		}
	}
	
	
	
	public Employee findOneemp(String emailId, String password) throws PersistenceException {

		try {
			String sql = "SELECT ID FROM EMPLOYEES WHERE EMAIL_ID = ? AND PASSWORD=? AND ACTIVE=1";
			Object[] params = { emailId, password };
			return jdbcTemplate.queryForObject(sql, params, (rs, rowNo) -> {
				Employee employee = new Employee();
				employee.setId(rs.getInt("ID"));
				return employee;

			});

		} catch (EmptyResultDataAccessException e) {
			throw new PersistenceException("Wrong Email id or Password", e);
		}
	}
	
}
