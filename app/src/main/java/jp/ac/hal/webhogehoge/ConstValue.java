package jp.ac.hal.webhogehoge;

public enum ConstValue {
	DEVICE_NAME("deviceName"), MAC_ADDRESS("macAddress"), RESULT("result"), BT_UUID("fea4154d-4184-46b4-98a5-7896af703591");

	private final String string;

	ConstValue(String s) {
		this.string = s;
	}

	String getConstValue() {
		return string;
	}

}
