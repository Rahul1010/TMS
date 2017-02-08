package com.ticket.test;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import com.ticket.dao.CreateTicketDao;
import com.ticket.dao.IssueDao;
import com.ticket.dao.SolutionDao;
import com.ticket.exception.PersistenceException;
import com.ticket.model.Issue;
import com.ticket.model.Solution;

public class TestCreateTicketDao {

	public static void main(String[] args)
			throws SerialException, PersistenceException, SQLIntegrityConstraintViolationException {
		CreateTicketDao ct = new CreateTicketDao();

//		 ct.createTicket("rahul@gmail.com","123", "HR", "Keypad", "Problem",
//		"HIGH");
		// ct.updateTicket(21,1, "HeHe");
		// ct.updateClose(1,21 );
		// Issue issue=new Issue();
		// IssueDao i = new IssueDao();
		// List<Issue> list=i.viewMyTicket(1);
		// for(Issue pro:list){
		// System.out.println(pro.getId()+"\t"+pro.getUserId().getId()+"\t"+pro.getSubject()+"\t"+pro.getDescription()+"\t"+pro.getPriority()+"\t"+pro.getStatus());
		// }
		//

		// ct.viewTicket("rahul@gmail.com", "123");
		// ct.createTicket("rahul@gmail.com", "123", "HR", "LAP", "LAPTOP NOT
		// SWITCHING ON", "LOW");

//		ct.assignedTicket(3);
		//ct.answerTicket("arul@gmail.com", "123", "rahul", 58);
		ct.createTicket("rahul@gmail.com", "123", "HR", "HI", "HI", "HIGH");

		// ct.reassignTicket(3, 26);
//		ct.deleteTicket("naren@gmail.com", "123", 26);
	}
}
