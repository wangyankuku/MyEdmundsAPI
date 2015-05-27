package com.edmunds.api.v1.cache;

import java.util.HashMap;
import java.util.Map;

import com.edmunds.api.v1.models.Vehicle;

public class Cache {
    private final int maxEntries = 50000;
    private final Map<String, Vehicle> map = new HashMap<String, Vehicle>();

    // Singleton Pattern
    private static Cache instance = null;

    // Cannot be instantiated by user
    private Cache() {
    }

    // Create the only one instance with thread safety
    public static synchronized Cache getInstance() {
	if (instance == null) {
	    instance = new Cache();
	}

	return instance;
    }

    public boolean hasVehicleWithVIN(String vin) {
	boolean res;

	synchronized (this.map) {
	    res = map.containsKey(vin);
	}

	return res;
    }

    public Vehicle getVehicleByVin(String vin) {
	Vehicle vehicle = null;

	synchronized (this.map) {
	    if (map.containsKey(vin)) {
		vehicle = map.get(vin);
	    }
	}

	return vehicle;
    }

    public void save(String vin, Vehicle vehicle) {
	synchronized (this.map) {
	    map.put(vin, vehicle);
	}
    }
    
    public int size() {
	int size = 0;
	
	synchronized (this.map) {
	    size = map.size();
	}
	
	return size;
    }
    
    public void cleanup() {
	synchronized (this.map) {
	    if (map.size() >= maxEntries) {
		map.clear();
	    }
	}
    }

}
