package com.kh.common;

public class MethodNotAllowedException extends RuntimeException {
	
	//Super class에 RuntimeException
	//generate constructor from superclass 
	public MethodNotAllowedException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MethodNotAllowedException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public MethodNotAllowedException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public MethodNotAllowedException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public MethodNotAllowedException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	
}
