package com.ticket.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ticket.exception.PersistenceException;
import com.ticket.model.Department;
import com.ticket.model.Employee;
import com.ticket.model.Issue;
import com.ticket.model.Solution;
import com.ticket.model.User;
import com.ticket.util.ConnectionUtil;

public class IssueDao implements Dao<Issue> {
	private JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();

	public void save(Issue i) {
		String sql = "INSERT INTO ISSUES (USER_ID,DEPARTMENT_ID,SUBJECT,DESCRIPTION,PRIORITY,DATE_RESOLVED) VALUES (?,?,?,?,?,?)";
		Object[] params = { i.getUserId().getId(), i.getDepartmentId().getId(), i.getSubject(), i.getDescription(),
				i.getPriority(), i.getDateResolved() };
		jdbcTemplate.update(sql, params);
	}

	public void update(Issue i) {
		String sql = "UPDATE ISSUES SET USER_ID=?,DEPARTMENNT_ID=?,SUBJECT=?,DESCRIPTION=?,PRIORITY=?,DATE_REPORTED,DATE_RESOLVED,STATUS=? WHERE ID = ?";
		Object[] params = { i.getUserId().getId(), i.getDepartmentId().getId(), i.getSubject(), i.getDescription(),
				i.getPriority(), i.getDateReported(), i.getDateResolved(), i.getStatus(), i.getId() };
		jdbcTemplate.update(sql, params);

	}

	public void delete(int id) {
		String sql = "DELETE FROM ISSUES WHERE ID=?";
		Object[] params = { id };
		jdbcTemplate.update(sql, params);

	}

	public Issue findOne(int id) {
		String sql = "SELECT ID,USER_ID,DEPARTMENT_ID,SUBJECT,DESCRIPTION,PRIORITY,DATE_REPORTED,DATE_RESOLVED,STATUS WHERE ID= ?";
		Object[] params = { id };
		return jdbcTemplate.queryForObject(sql, params, (rs, rowNo) -> convert(rs));
	}

	public List<Issue> findAll() {
		String sql = "SELECT ID,USER_ID,DEPARTMENT_ID,SUBJECT,DESCRIPTION,PRIORITY,DATE_REPORTED,DATE_RESOLVED,STATUS FROM ISSUES";
		return jdbcTemplate.query(sql, (rs, rowNo) -> convert(rs));
	}

	private Issue convert(ResultSet rs) throws SQLException {
		Issue i = new Issue();
		User u = new User();
		Department d = new Department();
		i.setId(rs.getInt("ID"));
		u.setId(rs.getInt("ID"));
		i.setUserId(u);
		d.setId(rs.getInt("ID"));
		i.setDepartmentId(d);

		i.setSubject(rs.getString("SUBJECT"));
		i.setDescription(rs.getString("DESCRIPTION"));
		i.setPriority(rs.getString("PRIORITY"));
		i.setDateReported(rs.getDate("DATE_REPORTED").toLocalDate());
		i.setDateResolved(rs.getDate("DATE_RESOLVED").toLocalDate());
		i.setStatus(rs.getString("STATUS"));
		return i;
	}

	public Department findId(String department) {
		String sql = "SELECT ID FROM DEPARTMENTS WHERE NAME= ?";
		Object[] params = { department };
		return jdbcTemplate.queryForObject(sql, params, (rs, rowNo) -> {
			Department d = new Department();
			d.setId(rs.getInt("ID"));
			return d;

		});
	}

	public Issue findUserId(String emailId) throws PersistenceException {
		try {
			String sql = "SELECT ID FROM USERS WHERE EMAIL_ID= ?";
			Object[] params = { emailId };
			return jdbcTemplate.queryForObject(sql, params, (rs, rowNo) -> {
				Issue i = new Issue();
				i.setId(rs.getInt("ID"));
				return i;

			});
		} catch (EmptyResultDataAccessException e) {
			throw new PersistenceException("Invalid EmailId", e);

		}
	}

	public Issue findUserIdForView(String emailId) {
		String sql = "SELECT ID FROM USERS WHERE EMAIL_ID= ?";
		Object[] params = { emailId };
		return jdbcTemplate.queryForObject(sql, params, (rs, rowNo) -> {
			Issue i = new Issue();
			i.setId(rs.getInt("ID"));
			return i;

		});
	}

	public void updateDesc(Issue i) {
		String sql = "UPDATE ISSUES SET DESCRIPTION=?,STATUS=? WHERE ID = ? AND USER_ID=?";
		Object[] params = { i.getDescription(), i.getStatus(), i.getId(), i.getUserId().getId() };
		jdbcTemplate.update(sql, params);

	}

	public Issue findStatus(int userId, int issueId) {
		String sql = "SELECT STATUS FROM ISSUES WHERE USER_ID= ? AND ID=?";
		Object[] params = { userId, issueId };
		return jdbcTemplate.queryForObject(sql, params, (rs, rowNo) -> {
			Issue i = new Issue();
			i.setStatus(rs.getString("STATUS"));
			return i;

		});
	}

	public void updateClose(Issue i) {
		String sql = "UPDATE ISSUES SET STATUS=? WHERE ID = ? AND USER_ID=?";
		Object[] params = { i.getStatus(), i.getId(), i.getUserId().getId() };
		jdbcTemplate.update(sql, params);

	}

	public List<Issue> viewMyTicket(int id) {
		String sql = "SELECT ID,USER_ID,DEPARTMENT_ID,SUBJECT,DESCRIPTION,PRIORITY,DATE_REPORTED,DATE_RESOLVED,STATUS FROM ISSUES WHERE USER_ID=?";
		Object[] params = { id };
		return jdbcTemplate.query(sql, params, (rs, rowNo) -> convert(rs));
	}

	public Issue findIssueId(String sub,String desc) {
		String sql = "SELECT ID FROM ISSUES WHERE SUBJECT=? AND DESCRIPTION=? AND STATUS='OPEN' ";
		Object[] params = { sub, desc };
		return jdbcTemplate.queryForObject(sql, params, (rs, rowNo) -> {
			Issue i = new Issue();
			i.setId(rs.getInt("ID"));
			return i;
		});

	}

	public List<Issue> viewAssignedTicket(int empId) {
		String sql = "SELECT ISSUES.`ID`,ISSUES.`SUBJECT`,ISSUES.`DESCRIPTION`,ISSUES.`PRIORITY`,ISSUES.`STATUS` FROM ISSUES JOIN SOLUTIONS WHERE SOLUTIONS.`EMPLOYEE_ID`=? AND ISSUES.`ID`=SOLUTIONS.`ISSUE_ID`";
		Object[] params = { empId };
		return jdbcTemplate.query(sql, params, (rs, rowNo) -> // convert(rs));
		{

			Issue i = new Issue();
			i.setId(rs.getInt("ID"));
			i.setSubject(rs.getString("SUBJECT"));
			i.setDescription(rs.getString("DESCRIPTION"));
			i.setPriority(rs.getString("PRIORITY"));
			i.setStatus(rs.getString("STATUS"));
			return i;
		});
	}

	public void updateEmployeeStatus(String status, int id) {
		String sql = "UPDATE ISSUES SET STATUS=? WHERE ID = ?";
		Object[] params = { status, id };
		jdbcTemplate.update(sql, params);

	}
	public void reassignEmployee(int issueid) {
		String sql = "UPDATE ISSUES SET ISSUES.`STATUS`='INPROGRESS' WHERE ISSUES.`ID`=?";
		Object[] params = {issueid};
		jdbcTemplate.update(sql, params);

	}
}
