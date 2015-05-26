/**
 * @author Yan
 * @Date 2015-05-26
 * 
 * This bean class is used to save the MPG data.
 */

package com.edmunds.api.v1.models;

public class Mileage {
    private double highwayMPG;
    private double cityMPG;

    public Mileage() {

    }

    public Mileage(double highwayMPG, double cityMPG) {
	this.highwayMPG = highwayMPG;
	this.cityMPG = cityMPG;
    }

    public double getHighwayMPG() {
	return highwayMPG;
    }

    public void setHighwayMPG(double highwayMPG) {
	this.highwayMPG = highwayMPG;
    }

    public double getCityMPG() {
	return cityMPG;
    }

    public void setCityMPG(double cityMPG) {
	this.cityMPG = cityMPG;
    }

}
