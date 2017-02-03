package com.ticket.exception;

public class PersistenceException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PersistenceException(String msg, Throwable t) {
		super(msg, t);
	}
}