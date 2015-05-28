/**
 * @author Yan
 * @Date 2015-05-27
 * 
 * This Exception class is thrown when the api key is not valid.
 */

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
