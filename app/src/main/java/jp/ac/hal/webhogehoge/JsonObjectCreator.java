package jp.ac.hal.webhogehoge;

import org.json.JSONException;
import org.json.JSONObject;

class JsonObjectCreator {
	private JSONObject jsonObject;

	JsonObjectCreator() {
		this.jsonObject = new JSONObject();
	}

	void putObjectValues(String key, String value) throws JSONException {
		this.jsonObject.put(key, value);
	}

	JSONObject getJsonObject() {
		return this.jsonObject;
	}

}
