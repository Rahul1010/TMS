package com.ticket.exception;

public class ValidationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ValidationException(String msg, Throwable e){
		super(msg,e);
	}

	public ValidationException(String msg){
		super(msg);
	}
}
