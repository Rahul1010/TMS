package com.ticket.dao;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;

import com.ticket.exception.PersistenceException;
import com.ticket.model.User;

public class LoginDao {

	UserDao userDao = new UserDao();
	EmployeeDao emp=new EmployeeDao();
	User user = new User();

	public boolean login(String emailId, String password) throws PersistenceException {
		try {

			String s = userDao.findOne(emailId).getPassword();
			
			if (s.equals(password))
				return true;
			
		} catch (Exception e) {
			
			throw new EmptyResultDataAccessException("Wrong EmailId", 0);
		}
		return true;
	}
	
	
	public boolean loginemp(String emailId, String password) throws PersistenceException {
		try {

	 emp.findOneemp(emailId, password);
			
			
		} catch (Exception e) {
			
			throw new EmptyResultDataAccessException("Wrong EmailId", 0);
		}
		return true;
	}

	public void registration(String name, String emailId, String password) throws PersistenceException {
		try {
			user.setName(name);
			user.setEmailId(emailId);
			user.setPassword(password);
			userDao.save(user);
		} catch (DuplicateKeyException e) {
			throw new PersistenceException("You have already registered");
		}

	}
}