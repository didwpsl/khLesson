package com.kh.common;

//RuntimeException 상속 
//부모 생성자로부터 생성자 모두 생성 
public class ControllerNotFoundException extends RuntimeException {

	public ControllerNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ControllerNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public ControllerNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ControllerNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public ControllerNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
}
