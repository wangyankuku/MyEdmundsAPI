/**
 * @author Yan
 * @Date 2015-05-27
 * 
 * This Exception class is thrown when the vehicle 
 * with the provided vin is not found from the remote server.
 */

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
