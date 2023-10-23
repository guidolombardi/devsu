package com.devsu.cuenta.exception;

public class CuentaNotFoundException extends RuntimeException {
    
	private static final long serialVersionUID = 1L;

	public CuentaNotFoundException(String message) {
        super(message);
    }
}
