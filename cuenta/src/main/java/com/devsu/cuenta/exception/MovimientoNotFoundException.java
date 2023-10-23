package com.devsu.cuenta.exception;

public class MovimientoNotFoundException extends RuntimeException {
    
	private static final long serialVersionUID = 1L;

	public MovimientoNotFoundException(String message) {
        super(message);
    }
}
