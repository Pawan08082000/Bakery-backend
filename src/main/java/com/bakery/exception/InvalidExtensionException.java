package com.bakery.exception;

public class InvalidExtensionException extends RuntimeException{
	private static final long serialVersionUID  = 1L;
	public InvalidExtensionException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public InvalidExtensionException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
