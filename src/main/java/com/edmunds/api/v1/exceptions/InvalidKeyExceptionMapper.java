/**
 * @author Yan
 * @Date 2015-05-27
 * 
 * This mapper class give the InvalidKeyException 
 * with specific message
 */

package com.edmunds.api.v1.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.edmunds.api.v1.models.ErrorMessage;

@Provider
public class InvalidKeyExceptionMapper implements
	ExceptionMapper<InvalidKeyException> {

    @Override
    public Response toResponse(InvalidKeyException e) {
	// TODO Auto-generated method stub
	ErrorMessage errorMessage = new ErrorMessage("INVALID_KEY_ERROR",
		e.getMessage(), "http://developer.edmunds.com/");
	return Response.status(Status.UNAUTHORIZED).entity(errorMessage)
		.build();

    }

}