package com.ticket.test;

import com.ticket.exception.PersistenceException;
import com.ticket.exception.ServiceException;
import com.ticket.exception.ValidationException;
import com.ticket.service.TicketService;

public class TestTicketService {
	public static void main(String[] args) throws PersistenceException, ValidationException, ServiceException{
	TicketService cc = new TicketService();
	cc.createTicket("","","", "", "", "");

}
}
