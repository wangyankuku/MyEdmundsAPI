package com.edmunds.api.v1.models;

public class ApiResponse {
    double averageCustomerRating;
    String summary;
    Mileage MPG;
    String engineType;

    public ApiResponse() {

    }

    public ApiResponse(double averageCustomerRating, String summary, Mileage mPG,
	    String engineType) {
	super();
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

}
