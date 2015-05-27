package com.edmunds.api.v1.models;

import javax.ws.rs.HeaderParam;

public class HeaderParams {
    @HeaderParam("apiKey")
    private String apiKey;

    public String getApiKey() {
	return apiKey;
    }

    public void setApiKey(String apiKey) {
	this.apiKey = apiKey;
    }

}
