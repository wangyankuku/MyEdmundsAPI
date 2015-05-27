package com.edmunds.api.v1.exceptions;

public class VehicleNotFoundException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = -5205245048625106110L;

    public VehicleNotFoundException(String message) {
	super(message);
    }
}
