package com.ticket.test;

import java.sql.SQLIntegrityConstraintViolationException;

import com.ticket.dao.LoginDao;
import com.ticket.exception.PersistenceException;

public class TestLoginDao {
	public static void main(String[] args) throws PersistenceException, SQLIntegrityConstraintViolationException {

		LoginDao ld = new LoginDao();
		ld.registration("arul", "rahul@gmail.com", "12345");
		System.out.println(ld);
	}

}
