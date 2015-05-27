package com.edmunds.api.v1.exceptions;

public class InvalidKeyException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = -5356987995861051470L;

    public InvalidKeyException(String message) {
	super(message);
    }

}
