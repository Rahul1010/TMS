package com.ticket.test;

import com.ticket.dao.LoginDao;
import com.ticket.exception.PersistenceException;

public class TestLoginDao {
	public static void main(String[] args) throws PersistenceException {

		LoginDao ld = new LoginDao();
		System.out.println(ld.login("rahul@gmail.com", "123"));
	}

}
