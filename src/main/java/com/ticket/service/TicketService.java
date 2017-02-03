package com.ticket.service;

import org.springframework.dao.EmptyResultDataAccessException;

import com.ticket.dao.CreateTicketDao;
import com.ticket.exception.PersistenceException;
import com.ticket.exception.ServiceException;
import com.ticket.exception.ValidationException;
import com.ticket.validator.TicketValidator;

public class TicketService {
	
	TicketValidator tv=new TicketValidator();
	CreateTicketDao ctd=new CreateTicketDao();
	
	public void createTicket(String emailId, String password, String department, String subject, String desc,
			String priority) throws  PersistenceException, ValidationException, ServiceException{
		try {
			tv.createTicket(emailId, password, department, subject, desc, priority);
			ctd.createTicket(emailId, password, department, subject, desc, priority);
		
		}
		
		catch (ValidationException|PersistenceException e) {
		throw new ServiceException("Ticket creation failed", e);
		}
		
	}
}
