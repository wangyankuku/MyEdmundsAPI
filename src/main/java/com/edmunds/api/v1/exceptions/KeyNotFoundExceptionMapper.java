package com.edmunds.api.v1.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.edmunds.api.v1.models.ErrorMessage;

@Provider
public class KeyNotFoundExceptionMapper implements
	ExceptionMapper<KeyNotFoundException> {

    @Override
    public Response toResponse(KeyNotFoundException e) {
	// TODO Auto-generated method stub
	ErrorMessage errorMessage = new ErrorMessage("KEY_NOT_FOUND",
		e.getMessage(), "http://developer.edmunds.com/");
	return Response.status(Status.FORBIDDEN).entity(errorMessage).build();

    }

}
