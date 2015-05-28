/**
 * @author Yan
 * @Date 2015-05-26
 * 
 * This bean class is used to save the Response Data 
 * that will be sent back to the user of this API.
 */

package com.edmunds.api.v1.models;

public class ApiResponse {
    String make;
    String model;
    String subModel;
    String year;
    double averageCustomerRating;
    String summary;
    Mileage MPG;
    String engineType;

    public ApiResponse() {

    }

    public ApiResponse(String make, String model, String subModel, String year,
	    double averageCustomerRating, String summary, Mileage mPG,
	    String engineType) {
	super();
	this.make = make;
	this.model = model;
	this.subModel = subModel;
	this.year = year;
	this.averageCustomerRating = averageCustomerRating;
	this.summary = summary;
	MPG = mPG;
	this.engineType = engineType;
    }

    public ApiResponse(Vehicle vehicle) {
	this.averageCustomerRating = vehicle.getAverageCustomerRating();
	this.summary = vehicle.getSummary();
	this.MPG = vehicle.getMPG();
	this.engineType = vehicle.getEngineType();
	this.make = vehicle.getMake();
	this.model = vehicle.getModel();
	this.subModel = vehicle.getSubmodel();
	this.year = vehicle.getYear();
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

    public String getSubModel() {
	return subModel;
    }

    public void setSubModel(String subModel) {
	this.subModel = subModel;
    }

    public String getYear() {
	return year;
    }

    public void setYear(String year) {
	this.year = year;
    }

}
