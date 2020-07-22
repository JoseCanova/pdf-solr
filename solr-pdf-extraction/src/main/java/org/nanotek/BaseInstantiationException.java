package org.nanotek;

public class BaseInstantiationException extends RuntimeException {

	public BaseInstantiationException() {
		super();
		
	}

	public BaseInstantiationException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	public BaseInstantiationException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public BaseInstantiationException(String message) {
		super(message);
		
	}

	public BaseInstantiationException(Throwable cause) {
		super(cause);
		
	}

}
