package com.perficient.bcten.util.exception;

public class CException extends RuntimeException {
	public CException() {
	}

	public CException(Throwable cause) {
		super(cause);
	}

	public CException(String message, Throwable cause) {
		super(message, cause);
	}

	public CException(String message) {
		super(message);
	}
}
