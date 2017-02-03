package com.ticket.validator;

import com.ticket.exception.ValidationException;
import com.ticket.model.User;
import com.ticket.util.ValidationUtil;

public class RegistrationValidator {
	public static void validateForRegisteration(User c) throws ValidationException{

		if ( ValidationUtil.isInvalidString(c.getName())) {
			throw new ValidationException("Invalid Name");
		}
		else if ( ValidationUtil.isInvalidString(c.getEmailId())) {
			throw new ValidationException("Invalid EmailId ");
		}
		else if ( ValidationUtil.isInvalidString(c.getPassword())) {
			throw new ValidationException("Invalid Password");
		}

	}

}
