package com.fabio.neostoreapi.exception;

public class PersistenceException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private String internalMessage;
	
	public PersistenceException(String message, String internalMessage) {
		super(message);
		this.internalMessage = internalMessage;
	}
	
	public String getInternalMessage() {
		return internalMessage;
	}
	
}
