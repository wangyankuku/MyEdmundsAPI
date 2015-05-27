package com.edmunds.api.v1.service;

import com.edmunds.api.v1.client.Client;
import com.edmunds.api.v1.models.Vehicle;

public class ApiService {
    public Vehicle getVehicleByVin(String apiKey, String vin) {
	Client client = new Client(apiKey);
	return client.generateVehicleData(vin);
    }
}
