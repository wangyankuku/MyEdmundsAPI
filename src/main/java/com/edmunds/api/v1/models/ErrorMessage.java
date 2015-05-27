package com.edmunds.api.v1.models;

public class ErrorMessage {
    private String status;
    private String message;
    private String moreInfoUrl;

    public ErrorMessage() {

    }

    public ErrorMessage(String status, String message, String moreInfoUrl) {
	super();
	this.status = status;
	this.message = message;
	this.moreInfoUrl = moreInfoUrl;
    }

    public String getStatus() {
	return status;
    }

    public void setStatus(String status) {
	this.status = status;
    }

    public String getMessage() {
	return message;
    }

    public void setMessage(String message) {
	this.message = message;
    }

    public String getMoreInfoUrl() {
	return moreInfoUrl;
    }

    public void setMoreInfoUrl(String moreInfoUrl) {
	this.moreInfoUrl = moreInfoUrl;
    }

}
