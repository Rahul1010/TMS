package com.ticket.validator;

import com.ticket.exception.ValidationException;
import com.ticket.util.ValidationUtil;

public class UpdateTicketValidator {
	public void updateTicket(int issueId, String emailId, String password, String description)
			throws ValidationException {

		if (ValidationUtil.isInvalidString(emailId)) {
			throw new ValidationException("Invalid EmailId ");
		} else if (ValidationUtil.isInvalidString(password)) {
			throw new ValidationException("Invalid Password");
		} else if (ValidationUtil.isInvalidString(description)) {
			throw new ValidationException("Give Description");

		}

	}
}
