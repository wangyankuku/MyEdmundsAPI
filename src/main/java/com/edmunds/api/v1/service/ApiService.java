package com.edmunds.api.v1.service;

import java.util.Calendar;

import com.edmunds.api.v1.cache.Cache;
import com.edmunds.api.v1.client.Client;
import com.edmunds.api.v1.models.Vehicle;

public class ApiService {
    public Vehicle getVehicleByVin(String apiKey, String vin) {
	Cache cache = Cache.getInstance();

	// set expiry time to 1 day. Can be changed according to the
	// requirements.
	long expirySeconds = 24 * 60 * 60;

	Vehicle vehicle = null;

	Client client = new Client(apiKey);

	if (cache.hasVehicleWithVIN(vin)) {
	    vehicle = cache.getVehicleByVin(vin);

	    // Compare apiKey with that in cache for this vin
	    // if apiKey matches
	    if (vehicle.getApiKey().equals(apiKey)) {

		Calendar now = Calendar.getInstance();
		long timediff = (now.getTimeInMillis() - vehicle
			.getCreatedData().getTimeInMillis()) / 1000;

		// if the data in the cache has expired, try to hit the remote
		// API
		if (timediff > expirySeconds) {
		    Vehicle updatedVehicle;

		    try {
			System.out
				.println("connecting to remote API for updating...");
			updatedVehicle = client.generateVehicleData(vin);
		    } catch (Exception e) {
			// got exception. Service not available now.
			// Just use data in cache
			updatedVehicle = null;
		    }

		    // if we got an update from remote API, just update the
		    // cache.
		    if (updatedVehicle != null) {
			System.out.println("Updating the vehicle data...");
			vehicle = updatedVehicle;
			cache.save(vin, vehicle);
		    }
		}
		// if not expired, pass
		else {
		    // do nothing and then return the vehicle in the cache
		}
	    }
	    // if apiKey not matches
	    else {
		vehicle = client.generateVehicleData(vin);
		System.out.println("Updating the vehicle data with diff apiKey...");
		cache.save(vin, vehicle);
	    }

	} else {
	    vehicle = client.generateVehicleData(vin);
	    // we add a new entry into the cache, so need to clean it up first
	    // if it reaches the max volume, remove all the entries.
	    cache.cleanup();
	    cache.save(vin, vehicle);
	}

	System.out.println("cache size is " + cache.size());

	return vehicle;
    }
}
