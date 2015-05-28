/**
 * @author Yan
 * @Date 2015-05-25
 * 
 * The ApiResource class defines the path, parameters and 
 * provides the interface that handles the request.
 */

package com.edmunds.api.v1.resources;

import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.edmunds.api.v1.exceptions.KeyNotFoundException;
import com.edmunds.api.v1.models.ApiResponse;
import com.edmunds.api.v1.models.HeaderParams;
import com.edmunds.api.v1.models.Vehicle;
import com.edmunds.api.v1.service.ApiService;

@Path("/vin")
public class ApiResource {
    // create the request handler
    ApiService service = new ApiService();

    /**
     * This method grabs the path parameters and header parameters from the
     * request. It checks the existence of the api key, and then call the
     * service to process this request.
     *
     * @param headers
     *            The bean that contains all the header parameters, which should
     *            contain the api key.
     *
     * @param vin
     *            Vehicle's vin from the path parameters.
     *
     * @return The Vehicle data in the cache or from the remote API.
     */

    @GET
    @Path("/{vin}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVehicleData(@BeanParam HeaderParams headers,
	    @PathParam("vin") String vin) {

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
