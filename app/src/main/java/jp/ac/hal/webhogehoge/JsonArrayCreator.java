package jp.ac.hal.webhogehoge;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class JsonArrayCreator {
	private JSONArray jsonArray;
	private final JSONObject JSON_OBJECT = new JSONObject();

	JsonArrayCreator() {
		this.jsonArray = new JSONArray();
	}

	void objectPutter(String key, String value) throws JSONException {
		JSON_OBJECT.put(key, value);
	}

	void arrayPutter(JSONObject jsonObject) {
		this.jsonArray.put(jsonObject);
	}

	JSONObject getJsonObject() {
		return this.JSON_OBJECT;
	}

	JSONArray getJsonArray() {
		return this.jsonArray;
	}
}
