package com.bakery.exception;

public class FileNotFoundException extends RuntimeException{
	private static final long serialVersionUID  = 1L;
	public FileNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public FileNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
