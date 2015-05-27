package com.edmunds.api.v1.resources;

import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

import com.edmunds.api.v1.exceptions.KeyNotFoundException;
import com.edmunds.api.v1.models.ApiResponse;
import com.edmunds.api.v1.models.HeaderParams;
import com.edmunds.api.v1.models.Vehicle;
import com.edmunds.api.v1.service.ApiService;

@Path("/vin")
public class ApiResource {

    ApiService service = new ApiService();

    @GET
    @Path("/{vin}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVehicleData(@BeanParam HeaderParams headers,
	    @PathParam("vin") String vin) {
	// @Context UriInfo uriInfo) throws Exception {
	// , @Context HttpHeaders headers) {
	// String path = uriInfo.getAbsolutePath().toString();
	// String res = null;
	//
	// if (headers.getRequestHeaders().containsKey("apiKey")) {
	// String apikey = headers.getRequestHeaders().getFirst("apiKey");
	// if (!apikey.equals(MessageResource.apiKey)) {
	// res = "APIKey is invalid";
	// } else {
	// res = "Payload accepted";
	// }
	// } else {
	// res = "Missing APIKey";
	// }
	//
	// return res;

	Vehicle vehicle = null;

	if (headers.getApiKey() == null) {
	    throw new KeyNotFoundException("Please enter your api key!");
	} else {
	    vehicle = service.getVehicleByVin(headers.getApiKey(), vin);
	}

	ApiResponse apiResponse = new ApiResponse(vehicle);

	return Response.status(Status.OK).entity(apiResponse).build();

    }
}
