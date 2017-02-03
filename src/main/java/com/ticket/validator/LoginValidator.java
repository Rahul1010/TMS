package com.ticket.validator;

import com.ticket.exception.ValidationException;
import com.ticket.model.User;
import com.ticket.util.ValidationUtil;

public class LoginValidator {
	public static void validateForLogin(User c) throws ValidationException{

		if ( ValidationUtil.isInvalidString(c.getEmailId())) {
			throw new ValidationException("Invalid EmailId ");
		}
		else if ( ValidationUtil.isInvalidString(c.getPassword())) {
			throw new ValidationException("Invalid Password");
		}

	}

}
