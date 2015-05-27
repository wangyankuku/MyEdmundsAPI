package com.edmunds.api.v1.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.edmunds.api.v1.models.ErrorMessage;

@Provider
public class VehicleNotFoundExceptionMapper implements
	ExceptionMapper<VehicleNotFoundException> {

    @Override
    public Response toResponse(VehicleNotFoundException e) {
	ErrorMessage errorMessage = new ErrorMessage("VEHICLE_NOT_FOUND",
		e.getMessage(), "http://developer.edmunds.com/");
	return Response.status(Status.BAD_REQUEST).entity(errorMessage).build();
    }

}
