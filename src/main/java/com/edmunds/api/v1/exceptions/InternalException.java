package com.edmunds.api.v1.exceptions;

public class InternalException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = -4639117354796719794L;

    public InternalException(String message) {
	super(message);
    }
}
