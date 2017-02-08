package com.ticket.test;



import java.sql.SQLIntegrityConstraintViolationException;

import com.ticket.dao.LoginDao;
import com.ticket.exception.PersistenceException;
import com.ticket.exception.ServiceException;
//import com.ticket.service.TicketService;
import com.ticket.service.TicketService;

public class TestTicketService {
	public static void main(String[] args) throws ServiceException{
	TicketService cc = new TicketService();
	LoginDao ld=new LoginDao();
//	ld.registration("rahul", "rahulss@gmail.com", "123");

	//cc.createTicket("","","", "", "", "");
	//cc.updateTicket( 1, "", "", "");
	//cc.updateClose(0,"", "");
//	cc.viewTicket("dsfsdf", "sdfs");
	cc.assignedTicket("rahul@gmail.com", "123", 3);

}
}
