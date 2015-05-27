package com.edmunds.api.v1.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.edmunds.api.v1.models.ErrorMessage;

@Provider
public class InternalExceptionMapper implements
	ExceptionMapper<InternalException> {

    @Override
    public Response toResponse(InternalException e) {
	// TODO Auto-generated method stub
	ErrorMessage errorMessage = new ErrorMessage("INTERNAL_SERVER_ERROR",
		e.getMessage(), "http://developer.edmunds.com/");
	return Response.status(Status.INTERNAL_SERVER_ERROR)
		.entity(errorMessage).build();

    }

}
