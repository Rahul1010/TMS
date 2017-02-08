package com.ticket.exception;

public class PersistenceException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PersistenceException(String msg, Throwable t) {
		super(msg, t);
	}

	public PersistenceException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public PersistenceException(String msg, Object e) {
		// TODO Auto-generated constructor stub
	}
}