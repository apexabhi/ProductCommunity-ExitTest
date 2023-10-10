package com.nagarro.exceptions;

public class NoPinCodeAvailableException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoPinCodeAvailableException(String msg) {
		super(msg);
	}

}
