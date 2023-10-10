package com.nagarro.exceptions;

public class ProductAlreadyExistException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProductAlreadyExistException(String msg) {
		super(msg);
	}

}
