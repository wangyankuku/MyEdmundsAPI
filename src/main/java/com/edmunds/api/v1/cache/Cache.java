/**
 * @author Yan
 * @date 2015-05-26
 * 
 * This is the cache class. It caches all the vehicle data in the memory.
 * The maxEntries defines the max number of the entries the cache can hold.
 * If the size of the cache reaches the max number, all the data inside will
 * be cleared up. The initial number is 50000, so if this API is called 
 * 200,000 times in a month. The cache saves data within about last 7 days.
 * Also, the cache cannot be created with new operator. There is only 1 cache 
 * object in the memory.
 * 
 */

package com.edmunds.api.v1.cache;

import java.util.HashMap;
import java.util.Map;

import com.edmunds.api.v1.models.Vehicle;

public class Cache {
    private final int maxEntries = 50000;
    // map is used as the data structure of the cache
    private final Map<String, Vehicle> map = new HashMap<String, Vehicle>();

    // Singleton Pattern
    private static Cache instance = null;

    // Cannot be instantiated by user
    private Cache() {
    }

    /**
     * Create the only one instance with thread safety
     *
     * @return The cache instance.
     */
    public static synchronized Cache getInstance() {
	if (instance == null) {
	    instance = new Cache();
	}

	return instance;
    }

    /**
     * Check if the vehicle with the vin is in the cache.
     *
     * @param vin
     *            Vehicle 's vin
     * @return Return true if the cache has this vehicle data with this vin.
     *         Otherwise return false.
     */
    public boolean hasVehicleWithVIN(String vin) {
	boolean res;

	synchronized (this.map) {
	    res = map.containsKey(vin);
	}

	return res;
    }

    /**
     * Get the vehicle data with the vin is in the cache.
     *
     * @param vin
     *            Vehicle 's vin
     * @return Return the vehicle's data if the vin in the cache. Otherwise
     *         return null.
     */
    public Vehicle getVehicleByVin(String vin) {
	Vehicle vehicle = null;

	synchronized (this.map) {
	    if (map.containsKey(vin)) {
		vehicle = map.get(vin);
	    }
	}

	return vehicle;
    }

    /**
     * Save the new Vehicle data or update the existing vehicle data in the
     * cache.
     *
     * @param vin
     *            Vehicle's vin
     * @param vehicle
     *            Vehicle's data
     * @return The cache instance.
     */
    public void save(String vin, Vehicle vehicle) {
	synchronized (this.map) {
	    map.put(vin, vehicle);
	}
    }

    /**
     * Get the size of the cache.
     *
     * @return The cache's size.
     */
    public int size() {
	int size = 0;

	synchronized (this.map) {
	    size = map.size();
	}

	return size;
    }

    /**
     * Remove all the data in the cache if it exceeds the max number of entries
     * inside allowed.
     */
    public void cleanup() {
	synchronized (this.map) {
	    if (map.size() >= maxEntries) {
		map.clear();
	    }
	}
    }

}
