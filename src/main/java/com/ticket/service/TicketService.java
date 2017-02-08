package com.ticket.service;

import org.springframework.dao.EmptyResultDataAccessException;

import com.ticket.dao.CreateTicketDao;
import com.ticket.dao.LoginDao;
import com.ticket.exception.PersistenceException;
import com.ticket.exception.ServiceException;
import com.ticket.exception.ValidationException;
import com.ticket.validator.CloseTicketValidator;
import com.ticket.validator.LoginValidator;
import com.ticket.validator.RegistrationValidator;
import com.ticket.validator.TicketValidator;
import com.ticket.validator.UpdateTicketValidator;
import com.ticket.validator.ViewTicketValidator;

public class TicketService {
	LoginValidator lv = new LoginValidator();
	TicketValidator tv = new TicketValidator();
	UpdateTicketValidator utv = new UpdateTicketValidator();
	CreateTicketDao ctd = new CreateTicketDao();
	CloseTicketValidator ctv = new CloseTicketValidator();
	RegistrationValidator rv = new RegistrationValidator();
	LoginDao ld = new LoginDao();
	ViewTicketValidator vtv = new ViewTicketValidator();

	public void createTicket(String emailId, String password, String department, String subject, String desc,
			String priority) throws ServiceException {
		try {
			tv.createTicket(emailId, password, department, subject, desc, priority);
			ctd.createTicket(emailId, password, department, subject, desc, priority);

		} catch (ValidationException | PersistenceException e) {
			throw new ServiceException("Ticket creation failed", e);
		}

	}

	public void updateTicket(int issueId, String emailId, String password, String description) throws ServiceException {

		try {
			utv.updateTicket(issueId, emailId, password, description);
			ctd.updateTicket(password, emailId, issueId, description);
		} catch (ValidationException | PersistenceException e) {
			throw new ServiceException("Ticket updation failed", e);
		}
	}

	public void updateClose(int issueId, String emailId, String password) throws ServiceException {
		try {

			ctv.updateClose(issueId, emailId, password);
			ctd.updateClose(issueId, password, emailId);

		} catch (ValidationException | PersistenceException e) {
			throw new ServiceException("Ticket updation failed", e);
		}

	}

	public void registration(String name, String emailId, String password) throws ServiceException {

		try {
			rv.registration(name, emailId, password);
			ld.registration(name, emailId, password);
		} catch (ValidationException | PersistenceException e) {

			throw new ServiceException("Ticket registration failed", e);
		}

	}
	
	
	public void login(String emailId, String password) throws ServiceException {

		try {
			lv.validateForLogin(emailId, password);
			ld.login(emailId, password);
		} catch (ValidationException | PersistenceException e) {

			throw new ServiceException("Ticket registration failed", e);
		}

	}


	public void viewTicket(String emailId, String password) throws ServiceException {
		try {
			vtv.viewTicket(emailId, password);
			ctd.viewTicket(emailId, password);
		} catch (ValidationException | PersistenceException | EmptyResultDataAccessException e) {
			throw new ServiceException("Process failed", e);
		}

	}

	public void assignedTicket(String emailId, String password, int empId) throws ServiceException {
		try {
			lv.validateForLogin(emailId, password);
			ctd.assignedTicket(empId);
		} catch (ValidationException e) {
			throw new ServiceException("You cannot view the assigned tickets", e);
		}
	}

	public void deleteTicket(String emailid, String password, int issueid) throws ServiceException {
		try {
			lv.validateForLogin(emailid, password);
			ctd.deleteTicket(emailid, password, issueid);
		} catch (ValidationException | PersistenceException e) {
			throw new ServiceException("You are not authorized to delete tickets", e);
		}
	}

	public void answerTicket(String emailid, String password, int issueid, String resolution) throws ServiceException {
		try {
			lv.validateForLogin(emailid, password);
			ctd.answerTicket(emailid,password,resolution, issueid);
		} catch (ValidationException | PersistenceException e) {
			throw new ServiceException("You cannot answer tickets", e);
		}
	}
	public void reassignTicket(String emailid,String password,int toEmpId, int issueId) throws ServiceException{
		try{
			lv.validateForLogin(emailid, password);
			ctd.reassignTicket(emailid, password, issueId,toEmpId);
		} catch (ValidationException e) {
			throw new ServiceException("You cannot answer tickets", e);
		}
	}

}
