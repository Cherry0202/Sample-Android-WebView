package jp.ac.hal.webhogehoge;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class JsonArrayCreator {
	private JSONArray jsonArray;
	private JSONObject jsonObject;

	JsonArrayCreator() {
		this.jsonArray = new JSONArray();
		this.jsonObject = new JSONObject();
	}

	void objectPutter(String key, String value) throws JSONException {
		this.jsonObject.put(key, value);
	}

	void arrayPutter(JSONObject jsonObject) {
		this.jsonArray.put(jsonObject);
	}

	void JsonObjectInit() {
		this.jsonObject = new JSONObject();
	}

	JSONObject getJsonObject() {
		return this.jsonObject;
	}

	JSONArray getJsonArray() {
		return this.jsonArray;
	}
}
