package com.ticket.test;

import java.util.List;

import com.ticket.dao.CreateTicketDao;
import com.ticket.dao.IssueDao;
import com.ticket.exception.PersistenceException;
import com.ticket.model.Issue;

public class TestCreateTicketDao {

	public static void main(String[] args) throws PersistenceException {
		CreateTicketDao ct = new CreateTicketDao();
		ct.createTicket("rahul@gmai.com","123", "HR", "LAPTOP", "DISABLED", "HIGH");
		//ct.updateTicket(21,1, "HeHe");
//		ct.updateClose(1,21 );
//		IssueDao i=new IssueDao();
//		List<Issue> list=i.viewMyTicket(1);
//		 for(Issue pro:list){
//		 System.out.println(pro.getId()+"\t"+pro.getUserId().getId()+"\t"+pro.getSubject()+"\t"+pro.getDescription()+"\t"+pro.getPriority()+"\t"+pro.getStatus());
//		 }
//		
		
		
//		ct.viewTicket("rahul@gmail.com", "123");
		
		

	}
	}

