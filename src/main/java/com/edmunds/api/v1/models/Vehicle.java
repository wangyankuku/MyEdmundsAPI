/**
 * @author Yan
 * @Date 2015-05-25
 * 
 * This bean class is used to save the vehicle Response Data 
 * as well as the http response from the Edmunds API.
 */

package com.edmunds.api.v1.models;

import java.util.HashMap;
import java.util.Map;

public class Vehicle {
    String make;
    String model;
    String year;
    String submodel;
    double averageCustomerRating;
    String summary;
    Mileage MPG;
    String engineType;
    Map<ResponseType, HttpResponse> responses;

    public Vehicle() {
	responses = new HashMap<ResponseType, HttpResponse>();
    }

    public String getMake() {
	return make;
    }

    public void setMake(String make) {
	this.make = make;
    }

    public String getModel() {
	return model;
    }

    public void setModel(String model) {
	this.model = model;
    }

    public String getYear() {
	return year;
    }

    public void setYear(String year) {
	this.year = year;
    }

    public double getAverageCustomerRating() {
	return averageCustomerRating;
    }

    public void setAverageCustomerRating(double averageCustomerRating) {
	this.averageCustomerRating = averageCustomerRating;
    }

    public String getSummary() {
	return summary;
    }

    public void setSummary(String summary) {
	this.summary = summary;
    }

    public Mileage getMPG() {
	return MPG;
    }

    public void setMPG(Mileage mPG) {
	MPG = mPG;
    }

    public String getEngineType() {
	return engineType;
    }

    public void setEngineType(String engineType) {
	this.engineType = engineType;
    }

    public Map<ResponseType, HttpResponse> getResponses() {
	return responses;
    }

    public void setResponses(Map<ResponseType, HttpResponse> responses) {
	this.responses = responses;
    }

    public String getSubmodel() {
	return submodel;
    }

    public void setSubmodel(String submodel) {
	this.submodel = submodel;
    }

}
