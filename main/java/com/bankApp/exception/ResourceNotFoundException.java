package com.bankApp.exception;

public class ResourceNotFoundException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9110977607173714193L;

	public ResourceNotFoundException(String exception) {
		super(exception);
	}

}
