package com.MSBootCoinBootCamp.application.exception;

public class ResourceSpecificException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message = "Account couldn't be created";
	
	public ResourceSpecificException() {

	}

	public ResourceSpecificException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}}
