package com.ticket.validator;

import com.ticket.exception.ValidationException;
import com.ticket.util.ValidationUtil;

public class ViewTicketValidator {
	public void viewTicket(String emailId,String password) throws ValidationException{
		if ( ValidationUtil.isInvalidString(emailId)) {
			throw new ValidationException("Invalid EmailId ");
		}
		else if ( ValidationUtil.isInvalidString(password)) {
			throw new ValidationException("Invalid Password");
		}

	}

}
