package jp.ac.hal.webhogehoge;

import org.json.JSONArray;
import org.json.JSONObject;

class JsonArrayCreator {
	private JSONArray jsonArray;

	JsonArrayCreator() {
		this.jsonArray = new JSONArray();
	}

	void arrayPutter(JSONObject jsonObject) {
		this.jsonArray.put(jsonObject);
	}


	JSONArray getJsonArray() {
		return this.jsonArray;
	}
}
