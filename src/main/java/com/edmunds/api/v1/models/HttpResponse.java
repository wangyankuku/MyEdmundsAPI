/**
 * @author Yan
 * @Date 2015-05-25
 * 
 * This bean class is used to save the Http ApiResponse Data.
 */

package com.edmunds.api.v1.models;

import java.util.Map;

public class HttpResponse {
    int responseCode;
    String responseData;
    ResponseType responseType;

    public HttpResponse() {

    }

    public HttpResponse(int responseCode, String responseData,
	    ResponseType responseType) {
	super();
	this.responseCode = responseCode;
	this.responseData = responseData;
	this.responseType = responseType;
    }

    public int getResponseCode() {
	return responseCode;
    }

    public void setResponseCode(int responseCode) {
	this.responseCode = responseCode;
    }

    public String getResponseData() {
	return responseData;
    }

    public void setResponseData(String responseData) {
	this.responseData = responseData;
    }

    public ResponseType getResponseType() {
	return responseType;
    }

    public void setResponseType(ResponseType responseType) {
	this.responseType = responseType;
    }

}
