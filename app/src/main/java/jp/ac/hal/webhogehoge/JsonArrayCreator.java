package jp.ac.hal.webhogehoge;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class JsonArrayCreator {
	private JSONObject jsonObject;
	private JSONArray jsonArray;

	JsonArrayCreator() {
		this.jsonArray = new JSONArray();
	}

	private void jsonCreator() {
		this.jsonObject = new JSONObject();
	}

	void objectPutter(String key, String value) throws JSONException {
		jsonObject.put(key, value);
	}

	void arrayPutter(JSONObject jsonObject) {
		this.jsonArray.put(jsonObject);
	}

	JSONObject returnJsonObject() {
		jsonCreator();
		return this.jsonObject;
	}

	JSONArray returnJsonArray() {
		return this.jsonArray;
	}


}