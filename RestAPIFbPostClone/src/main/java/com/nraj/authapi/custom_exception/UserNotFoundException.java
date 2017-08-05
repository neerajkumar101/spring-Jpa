package com.nraj.authapi.custom_exception;

public class UserNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String message = null;
	
	public UserNotFoundException(String message) {
		this.message = message;
	}

}
