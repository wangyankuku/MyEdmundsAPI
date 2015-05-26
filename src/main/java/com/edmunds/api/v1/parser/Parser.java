/**
 * @author Yan
 * @date 2015-05-25
 * 
 * This class is used to parser the data from the JSON String returned from 
 * Edmund's API.
 */

package com.edmunds.api.v1.parser;

import org.json.JSONArray;
import org.json.JSONObject;

import com.edmunds.api.v1.models.HttpResponse;

public class Parser {
    /**
     * This method is to parser the String.
     * 
     * @param response
     *            The JSON string that will be parsed.
     * @param path
     *            The path where the value can be found along with.
     * @return String the returning value should be the value we are searching
     *         for. If it doesn't exist return empty string.
     */
    public static String parser(HttpResponse response, String path) {
	// build a JSON object
	JSONObject obj = new JSONObject(response.getResponseData());

	// split the path
	String[] keyNames = path.split("\\/");
	int len = keyNames.length;

	String res = "";

	// get the first result
	for (int i = 0; i < len; i++) {
	    String keyName = keyNames[i];
	    if (obj != null && obj.has(keyName)) {
		Object temp = obj.get(keyName);
		if (temp instanceof JSONObject) {
		    obj = (JSONObject) temp;
		} else if (temp instanceof JSONArray) {
		    if (((JSONArray) temp).length() > 0) {
			obj = ((JSONArray) temp).getJSONObject(0);
		    } else {
			break;
		    }
		} else {
		    res = String.valueOf(temp);
		    break;
		}

	    } else {
		break;
	    }
	}

	return res;
    }

}
