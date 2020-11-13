package jp.ac.hal.webhogehoge;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class JsonCreator {
	private JSONObject jsonObject;
	private JSONArray jsonArray;

	JsonCreator() {
		this.jsonObject = new JSONObject();
		this.jsonArray = new JSONArray();
	}

	void objectPutter(String key, String value) throws JSONException {
		this.jsonObject.put(key, value);
	}

	void arrayPutter(JSONObject jsonObject) {
		this.jsonArray.put(jsonObject);
	}

	JSONObject returnJsonObject() {
		return this.jsonObject;
	}

	JSONArray returnJsonArray() {
		return this.jsonArray;
	}


}
