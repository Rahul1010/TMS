package com.ticket.dao;

import java.util.List;

import com.ticket.exception.PersistenceException;
import com.ticket.model.Department;
import com.ticket.model.Issue;
import com.ticket.model.User;

public class CreateTicketDao {
	User user=new User();
	Department dept=new Department();
	Issue issue=new Issue();
	IssueDao i=new IssueDao();
	LoginDao l=new LoginDao();

	public void createTicket(String emailId,String password,String department,String subject,String desc,String priority) throws PersistenceException{
		if(l.login(emailId, password)){
		int userId=i.findUserId(emailId).getId();	
		user.setId(userId);
		dept.setName(department);
		int deptId=i.findId(department).getId();
		dept.setId(deptId);
		issue.setDepartmentId(dept);  
		issue.setUserId(user);
		issue.setSubject(subject);
		issue.setDescription(desc);
		issue.setPriority(priority);
		i.save(issue);
		}
		}
	
	public void updateTicket(int issueId,String emailId,String password,String description) throws PersistenceException{
		if(l.login(emailId, password)){
			int userId=i.findUserId(emailId).getId();	
		if("CLOSED".equals(i.findStatus(userId, issueId).getStatus()) || "Closed".equals(i.findStatus(userId, issueId).getStatus()) ){
			
			System.out.println("You cant update now!");
		}
		else{
		
		User user=new User();
		Issue issue=new Issue();
		IssueDao i=new IssueDao();
		issue.setId(issueId);
		issue.setStatus("Inprogress");
		user.setId(userId);
		issue.setUserId(user);
		issue.setDescription(description);
		i.updateDesc(issue);
		
		}
		}
	}

	public void updateClose(int issueId,String emailId,String password) throws PersistenceException{
		if(l.login(emailId, password)){
		int userId=i.findUserId(emailId).getId();	
		user.setId(userId);
		issue.setUserId(user);
		
		issue.setId(issueId);
		issue.setStatus("CLOSED");
		i.updateClose(issue);
		}	
	}
		public void viewTicket(String emailId,String password) throws PersistenceException{
			if(l.login(emailId, password)){
				int userId=i.findUserIdForView(emailId).getId();	
				user.setId(userId);
				i.viewMyTicket(userId);
				
				List<Issue> list=i.viewMyTicket(userId);
				 for(Issue pro:list){
				 System.out.println(pro.getId()+"\t"+pro.getUserId().getId()+"\t"+pro.getSubject()+"\t"+pro.getDescription()+"\t"+pro.getPriority()+"\t"+pro.getStatus());
				 }

			
			
		}}
}
