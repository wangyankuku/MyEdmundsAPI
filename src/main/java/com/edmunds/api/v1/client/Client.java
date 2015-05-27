/**
 * @author Yan
 * @date 2015-05-25
 * 
 * This class is used as the client to connect to Edmunds API to get the Vehicle info.
 * After getting these data, the parser parses the response and obtain all the 
 * necessary ones. 
 * By the info collected from the Edmunds, I can send them back to the user of my API.
 */

package com.edmunds.api.v1.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import com.edmunds.api.v1.exceptions.InternalException;
import com.edmunds.api.v1.exceptions.InvalidKeyException;
import com.edmunds.api.v1.exceptions.VehicleNotFoundException;
import com.edmunds.api.v1.models.HttpResponse;
import com.edmunds.api.v1.models.Mileage;
import com.edmunds.api.v1.models.ResponseType;
import com.edmunds.api.v1.models.Vehicle;
import com.edmunds.api.v1.parser.Parser;

public class Client {

    String apiKey;

    public Client(String apiKey) {
	this.apiKey = apiKey;
    }

    /**
     * This method is used to get the vehicle details data including make,
     * model, year and a lot of other very useful data.
     * 
     * @param vin
     *            The car's vin.
     * @return HttpResponse A bean to save both response code and response body
     */
    private HttpResponse getFullVehicleDetailsByVIN(String vin)
	    throws Exception {

	StringBuilder sb = new StringBuilder();

	// add url and vin
	sb.append(Constants.CAR_DETAILS_URL);
	sb.append(URLEncoder.encode(vin, "UTF-8"));
	sb.append("?");

	// add format parameter
	sb.append(URLEncoder.encode("fmt", "UTF-8")).append("=")
		.append(URLEncoder.encode("json", "UTF-8"));
	sb.append("&");

	// add apiKey parameter
	sb.append(URLEncoder.encode("api_key", "UTF-8")).append("=")
		.append(URLEncoder.encode(this.apiKey, "UTF-8"));

	return getResponseFromURL(sb.toString(), ResponseType.CUSTOMER_RATING);

    }

    /**
     * This method is used to get a brief summary of the Edmunds Editor review
     * 
     * @param vehicle
     *            Vehicle bean that saves vehicle data from Edmunds. It provides
     *            data of make, model, submodel and year.
     * @return HttpResponse A bean to save both response code and response body
     */
    private HttpResponse getEdmundsCarRating(Vehicle vehicle) throws Exception {

	StringBuilder sb = new StringBuilder();

	// add parameters
	sb.append(Constants.EDMUNDS_RATING_URL);
	sb.append(URLEncoder.encode(vehicle.getMake(), "UTF-8"));
	sb.append("/").append(
		URLEncoder.encode(vehicle.getModel(), "UTF-8").toLowerCase());
	sb.append("/").append(
		URLEncoder.encode(vehicle.getYear(), "UTF-8").toLowerCase());
	sb.append("/")
		.append(URLEncoder.encode("grade", "UTF-8").toLowerCase());
	sb.append("?");

	// add submodel
	sb.append(URLEncoder.encode("submodel", "UTF-8"))
		.append("=")
		.append(URLEncoder.encode(vehicle.getSubmodel(), "UTF-8")
			.toLowerCase());
	sb.append("&");

	// add format parameter
	sb.append(URLEncoder.encode("fmt", "UTF-8")).append("=")
		.append(URLEncoder.encode("json", "UTF-8"));
	sb.append("&");

	// add apiKey parameter
	sb.append(URLEncoder.encode("api_key", "UTF-8")).append("=")
		.append(URLEncoder.encode(this.apiKey, "UTF-8"));

	return getResponseFromURL(sb.toString(), ResponseType.EDMUNDS_RATING);

    }

    /**
     * This method is used to get the average consumer rating for the vehicle.
     * 
     * @param vehicle
     *            Vehicle bean that saves vehicle data from Edmunds. It provides
     *            data of make, model and year.
     * @return HttpResponse A bean to save both response code and response body
     */
    private HttpResponse getCustomerCarRating(Vehicle vehicle) throws Exception {
	StringBuilder sb = new StringBuilder();

	// add parameters
	sb.append(Constants.CUSTOMER_RATING_URL);
	sb.append(URLEncoder.encode(vehicle.getMake(), "UTF-8").toLowerCase());
	sb.append("/").append(
		URLEncoder.encode(vehicle.getModel(), "UTF-8").toLowerCase());
	sb.append("/").append(
		URLEncoder.encode(vehicle.getYear(), "UTF-8").toLowerCase());
	sb.append("?");

	// add page number and page size
	sb.append(URLEncoder.encode("pagenum", "UTF-8")).append("=")
		.append(URLEncoder.encode("1", "UTF-8"));
	sb.append("&");
	sb.append(URLEncoder.encode("pagesize", "UTF-8")).append("=")
		.append(URLEncoder.encode("1", "UTF-8"));
	sb.append("&");

	// add format parameter
	sb.append(URLEncoder.encode("fmt", "UTF-8")).append("=")
		.append(URLEncoder.encode("json", "UTF-8"));
	sb.append("&");

	// add apiKey parameter
	sb.append(URLEncoder.encode("api_key", "UTF-8")).append("=")
		.append(URLEncoder.encode(this.apiKey, "UTF-8"));

	return getResponseFromURL(sb.toString(), ResponseType.CUSTOMER_RATING);
    }

    /**
     * This method is used to connect to the remote API by the given url addr.
     * 
     * @param url
     *            Url with parameters that will be connected to.
     * @param type
     *            Mark the response type for future reference
     * @return HttpResponse A bean to save both response code and response body
     */
    private HttpResponse getResponseFromURL(String url, ResponseType type)
	    throws Exception {

	StringBuilder responseData = new StringBuilder();

	// create url object
	URL obj = new URL(url);

	HttpURLConnection con = (HttpURLConnection) obj.openConnection();

	// method is GET
	con.setRequestMethod("GET");

	// add request header
	con.setRequestProperty("User-Agent", "Mozilla/5.0");

	int responseCode = con.getResponseCode();

	BufferedReader in = new BufferedReader(new InputStreamReader(
		con.getInputStream()));

	String inputLine;
	while ((inputLine = in.readLine()) != null) {
	    responseData.append(inputLine);
	}
	in.close();

	return new HttpResponse(responseCode, responseData.toString(), type);
    }

    /**
     * The method runs the method to connect to the API and parse the vehicle
     * data.
     * 
     * @param vin
     * @return Vehicle
     */
    public Vehicle generateVehicleDetails(String vin) throws Exception {
	Vehicle vehicle = new Vehicle();

	HttpResponse vehicle_details_response = getFullVehicleDetailsByVIN(vin);

	vehicle.getResponses().put(vehicle_details_response.getResponseType(),
		vehicle_details_response);

	String highwayMPG = Parser.parser(vehicle_details_response,
		VehicleDataPath.HIGHWAY_MPG_PATH);
	String cityMPG = Parser.parser(vehicle_details_response,
		VehicleDataPath.CITY_MPG_PATH);

	vehicle.setMPG(new Mileage(Double.parseDouble(highwayMPG), Double
		.parseDouble(cityMPG)));
	vehicle.setEngineType(Parser.parser(vehicle_details_response,
		VehicleDataPath.ENGINE_TYPE_PATH));
	vehicle.setMake(Parser.parser(vehicle_details_response,
		VehicleDataPath.NAME_PATH));
	vehicle.setModel(Parser.parser(vehicle_details_response,
		VehicleDataPath.MODE_PATH));
	vehicle.setYear(Parser.parser(vehicle_details_response,
		VehicleDataPath.YEAR_PATH));
	vehicle.setSubmodel(Parser.parser(vehicle_details_response,
		VehicleDataPath.SUBMODEL_PATH));

	return vehicle;
    }

    /**
     * The method runs the method to connect to the API and parse the edmunds
     * editor's summary review data and save them in the vehicle bean.
     * 
     * @param Vehicle
     */
    public void generateEdmundsRating(Vehicle vehicle) throws Exception {
	HttpResponse edmunds_rating_response = getEdmundsCarRating(vehicle);

	vehicle.getResponses().put(edmunds_rating_response.getResponseType(),
		edmunds_rating_response);
	vehicle.setSummary(Parser.parser(edmunds_rating_response,
		VehicleDataPath.EDMUNDS_RATING_PATH));
    }

    /**
     * The method runs the method to connect to the API and parse the average
     * customer rating date.
     * 
     * @param Vehicle
     */
    public void generateCustomerRating(Vehicle vehicle) throws Exception {
	HttpResponse customer_rating_response = getCustomerCarRating(vehicle);
	vehicle.getResponses().put(customer_rating_response.getResponseType(),
		customer_rating_response);
	String avg_customer_rating = Parser.parser(customer_rating_response,
		VehicleDataPath.AVG_CUSTOMER_RATING_PATH);
	vehicle.setAverageCustomerRating(Double
		.parseDouble(avg_customer_rating));
    }

    /**
     * The method runs all the other and generates the final vehicle bean.
     * 
     * @param vin
     * @return Vehicle
     */
    public Vehicle generateVehicleData(String vin) throws RuntimeException {
	Vehicle vehicle = null;

	try {

	    vehicle = generateVehicleDetails(vin);
	    generateEdmundsRating(vehicle);
	    generateCustomerRating(vehicle);

	    System.out.println("Vehicle highway MPG is '"
		    + vehicle.getMPG().getHighwayMPG() + "'");
	    System.out.println("Vehicle cityway MPG is '"
		    + vehicle.getMPG().getCityMPG() + "'");
	    System.out.println("Vehicle engine type is '"
		    + vehicle.getEngineType() + "'");
	    System.out.println("Vehicle make is '" + vehicle.getMake() + "'");
	    System.out.println("Vehicle model is '" + vehicle.getModel() + "'");
	    System.out.println("Vehicle submodel is '" + vehicle.getSubmodel()
		    + "'");
	    System.out.println("Vehicle year is '" + vehicle.getYear() + "'");
	    System.out.println("Vehicle summary is " + vehicle.getSummary());
	    System.out.println("Vehicle avg customer rating is '"
		    + vehicle.getAverageCustomerRating() + "'");

	} catch (Exception e) {
	    System.out.println(e.getMessage());

	    if (e.getMessage().contains("403")) {
		throw new InvalidKeyException(
			"The API key is not valid. Please check.");
	    } else if (e.getMessage().contains("400")) {
		throw new VehicleNotFoundException(
			"Vehicle is not found. Please check VIN.");
	    } else {
		throw new InternalException(
			"Internal error occured. Please try again later.");
	    }

	}

	return vehicle;
    }

}
