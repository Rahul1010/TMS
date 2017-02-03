package com.ticket.dao;

import com.ticket.exception.PersistenceException;
import com.ticket.model.User;

public class LoginDao {
 UserDao userDao=new UserDao();
 User user=new User();

 public boolean login(String emailId,String password) throws PersistenceException{
	 String s=userDao.findOne(emailId).getPassword();
	 if(s.equals(password))
		 return true;
	 return false;
 }
 
 
 public void registration(String name,String emailId,String password) throws PersistenceException
 {
user.setName(name);
user.setEmailId(emailId);
user.setPassword(password);
userDao.save(user);
	 
 }
}
