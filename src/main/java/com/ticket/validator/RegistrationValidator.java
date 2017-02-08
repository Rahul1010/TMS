package com.ticket.validator;

import com.ticket.exception.ValidationException;
import com.ticket.util.ValidationUtil;

public class RegistrationValidator {
	public void registration(String name,String emailId,String password) throws ValidationException{

		if ( ValidationUtil.isInvalidString(name)) {
			throw new ValidationException("Invalid Name");
		}
		else if ( ValidationUtil.isInvalidString(emailId)) {
			throw new ValidationException("Invalid EmailId ");
		}
		else if ( ValidationUtil.isInvalidString(password)) {
			throw new ValidationException("Invalid Password");
		}

	}

}
