package com.ticket.dao;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;

import com.ticket.exception.PersistenceException;
import com.ticket.model.Department;
import com.ticket.model.Employee;
import com.ticket.model.Issue;
import com.ticket.model.Solution;
import com.ticket.model.User;
import com.ticket.util.MailUtil;

public class CreateTicketDao {
	UserDao userDao = new UserDao();
	User user = new User();
	Department dept = new Department();
	Issue issue = new Issue();
	IssueDao i = new IssueDao();
	LoginDao l = new LoginDao();
	Employee emp = new Employee();
	EmployeeDao employeeDao = new EmployeeDao();
	Solution solution = new Solution();
	SolutionDao solutionDao = new SolutionDao();

	public void createTicket(String emailId, String password, String department, String subject, String desc,
			String priority) throws PersistenceException {
		if (l.login(emailId, password)) {
			int userId = i.findUserId(emailId).getId();
			user.setId(userId);
			dept.setName(department);
			int deptId = i.findId(department).getId();
			dept.setId(deptId);
			issue.setDepartmentId(dept);
			issue.setUserId(user);
			issue.setSubject(subject);
			issue.setDescription(desc);
			issue.setPriority(priority);
			i.save(issue);


			int issueId = i.findIssueId(subject, desc).getId();
			int empId = employeeDao.findEmployee(deptId).getId();
			String employeeEmail = employeeDao.findEmployeeEmailId(empId).getEmailId();
			issue.setId(issueId);
			emp.setId(empId);
			solution.setIssueId(issue);
			solution.setEmployeeId(emp);
			solutionDao.save(solution);

			try {
				MailUtil.sendSimpleMail(emailId, "Ticket Created Sucessfully.Your Ticket id is:", issueId);
				MailUtil.sendSimpleMail(employeeEmail, "A ticket has been created. The issue id is:", issueId);

			} catch (Exception e) {

			}

		}
	}

	public void updateTicket(String emailId, String password, int issueId, String description)
			throws PersistenceException {
		if (l.login(emailId, password)) {
			int userId = i.findUserId(emailId).getId();
			if ("CLOSED".equals(i.findStatus(userId, issueId).getStatus())
					|| "Closed".equals(i.findStatus(userId, issueId).getStatus())) {

				System.out.println("You cant update now!");
			} else {

				User user = new User();
				Issue issue = new Issue();
				IssueDao i = new IssueDao();
				issue.setId(issueId);
				issue.setStatus("Inprogress");
				user.setId(userId);
				issue.setUserId(user);
				issue.setDescription(description);
				i.updateDesc(issue);

			}
		}
	}

	public void updateClose(int issueId, String password, String emailId) throws PersistenceException {
		if (l.login(emailId, password)) {
			int userId = i.findUserId(emailId).getId();
			user.setId(userId);
			issue.setUserId(user);

			issue.setId(issueId);
			issue.setStatus("CLOSED");
			i.updateClose(issue);
		}
	}

	public void viewTicket(String emailId, String password) throws PersistenceException {
		try {

			if (l.login(emailId, password)) {
				if (((emailId).equals(userDao.findIfPresent(emailId).getEmailId()))) {
					int userId = i.findUserIdForView(emailId).getId();
					List<Issue> list = i.viewMyTicket(userId);
					for (Issue pro : list) {
						System.out.println(pro.getId() + "\t" + pro.getUserId().getId() + "\t" + pro.getSubject() + "\t"
								+ pro.getDescription() + "\t" + pro.getPriority() + "\t" + pro.getStatus());
					}

				}
			}

		} catch (EmptyResultDataAccessException e) {
			throw new PersistenceException("You are not authorized to view the details", e);
		}
	}

	public void assignedTicket(int empId) {

		List<Issue> list = i.viewAssignedTicket(empId);
		for (Issue pro : list) {
			System.out.println(pro.getId() + "\t" + pro.getSubject() + "\t" + pro.getDescription() + "\t"
					+ pro.getPriority() + "\t" + pro.getStatus());

		}
	}

	public void answerTicket(String emailId,String password,String resolution, int issueid) throws PersistenceException {
		
		
		if (l.loginemp(emailId, password)) {
			Employee employee=new Employee();
			employee.setEmailId(emailId);
			employee.setPassword(password);
			EmployeeDao employeeDao = new EmployeeDao();

			Solution solution = new Solution();
			SolutionDao solutionDao = new SolutionDao();

			if(employeeDao.findOne(emailId, password).getId()==solutionDao.findEmployeeId(issueid).getEmployeeId().getId()){

			
			issue.setId(issueid);
			solution.setIssueId(issue);
			solution.setResolution(resolution);
			
			IssueDao i = new IssueDao();
			SolutionDao s = new SolutionDao();
			i.updateEmployeeStatus("RESOLVED", issueid);
			s.updateEmployeeStatus(resolution, issueid);
			}
		
		
	
		try {
			System.out.println(emailId+" "+resolution+" "+issueid);
			MailUtil.sendSimpleMail(emailId,"The Solution for your query is as follows:"+resolution+"-"+"Your ticket id is:",issueid);
		} catch (Exception e) {

		}}}



	public void reassignTicket(String emailid, String password, int toEmpId, int issueId) {
		employeeDao.reassignEmployee(toEmpId, issueId);
		i.reassignEmployee(issueId);
	}

	public void deleteTicket(String emailid, String password, int issueid) throws PersistenceException {
		if (employeeDao.findRoleId(emailid, password).getRoleId().getId() == 1) {
			employeeDao.deleteTicketFromSolutions(issueid);
			employeeDao.deleteTicketFromIssues(issueid);
		}

	}

}