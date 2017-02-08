package com.ticket.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.ticket.model.Employee;
import com.ticket.model.Issue;
import com.ticket.model.Solution;
import com.ticket.util.ConnectionUtil;

public class SolutionDao implements Dao<Solution> {
	private JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();

	public void save(Solution s) {
		String sql = "INSERT INTO SOLUTIONS (ISSUE_ID,EMPLOYEE_ID) VALUES (?,?)";
		Object[] params = { s.getIssueId().getId(), s.getEmployeeId().getId() };
		jdbcTemplate.update(sql, params);
	}

	public void update(Solution s) {
		String sql = "UPDATE SOLUTIONS SET ISSUE_ID=?,EMPLOYEE_ID=?,RESOLUTION=? WHERE ID = ?";
		Object[] params = { s.getIssueId().getId(), s.getEmployeeId().getId(), s.getResolution(), s.getId() };
		jdbcTemplate.update(sql, params);

	}

	public void delete(int id) {
		String sql = "DELETE FROM SOLUTIONS WHERE ID=?";
		Object[] params = { id };
		jdbcTemplate.update(sql, params);

	}

	public Solution findOne(int id) {
		String sql = "SELECT ID,ISSUE_ID,EMPLOYEE_ID,RESOLUTION FROM SOLUTIONS WHERE ID= ?";
		Object[] params = { id };
		return jdbcTemplate.queryForObject(sql, params, (rs, rowNo) -> convert(rs));
	}

	public List<Solution> findAll() {
		String sql = "SELECT SELECT ID,ISSUE_ID,EMPLOYEE_ID,RESOLUTION FROM SOLUTIONS";
		return jdbcTemplate.query(sql, (rs, rowNo) -> convert(rs));
	}

	private Solution convert(ResultSet rs) throws SQLException {
		Solution s = new Solution();
		Employee employee = new Employee();
		Issue issue = new Issue();
		issue.setId(rs.getInt("ISSUE_ID"));
		s.setIssueId(issue);
		employee.setId(rs.getInt("EMPLOYEE_ID"));
		s.setEmployeeId(employee);
		s.setId(rs.getInt("ID"));
		s.setResolution(rs.getString("RESOLUTION"));
		return s;
	}

	public void updateEmployeeStatus(String resolution,int id) {
		String sql = "UPDATE SOLUTIONS SET RESOLUTION=? WHERE ISSUE_ID = ?";
		Object[] params = {resolution,id};
		jdbcTemplate.update(sql, params);

	}
	public Solution findEmployeeId(int issueId) {
		String sql = "SELECT EMPLOYEE_ID FROM SOLUTIONS WHERE ISSUE_ID = ?";
		Object[] params = { issueId };
		return jdbcTemplate.queryForObject(sql, params, (rs, rowNo) -> {
			Solution solution = new Solution();
			
			Employee employee=new Employee();
			employee.setId(rs.getInt("EMPLOYEE_ID"));
			solution.setEmployeeId(employee);
				
			return solution;
		});
	}
}
