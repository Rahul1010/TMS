package com.ticket.validator;

import com.ticket.exception.ValidationException;
import com.ticket.util.ValidationUtil;

public class TicketValidator {
	public void createTicket(String emailId, String password, String department, String subject, String desc,
			String priority) throws ValidationException {

		if (ValidationUtil.isInvalidString(emailId)) {
			throw new ValidationException("Invalid EmailId ");
		} else if (ValidationUtil.isInvalidString(password)) {
			throw new ValidationException("Invalid Password");
		}else if (ValidationUtil.isInvalidString(department)) {
			throw new ValidationException("Specify Department");
		}else if (ValidationUtil.isInvalidString(subject)) {
			throw new ValidationException("Specify the Subject");
		}else if (ValidationUtil.isInvalidString(desc)) {
			throw new ValidationException("Give Description");
		}else if (ValidationUtil.isInvalidString(priority)) {
			throw new ValidationException("Set the Priority");
		}
	}
}
