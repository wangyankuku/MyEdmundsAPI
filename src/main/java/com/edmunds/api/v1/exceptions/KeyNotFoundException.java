package com.edmunds.api.v1.exceptions;

public class KeyNotFoundException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = -7265026570984836733L;

    public KeyNotFoundException(String message) {
	super(message);
    }

}
