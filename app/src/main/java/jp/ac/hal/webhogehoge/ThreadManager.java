package jp.ac.hal.webhogehoge;

import android.bluetooth.BluetoothAdapter;
import android.webkit.JavascriptInterface;

import org.json.JSONException;

import java.io.IOException;

public class ThreadManager {
	private BluetoothAdapter bluetoothAdapter;

	ThreadManager(BluetoothAdapter bluetoothAdapter) {
		this.bluetoothAdapter = bluetoothAdapter;
	}

	private BluetoothAdapter returnBTAdapter() {
		return this.bluetoothAdapter;
	}

	@JavascriptInterface
	public String threadMaker(String msg, String macAddress) throws IOException {
		ConnectDevice connectDevice = new ConnectDevice(returnBTAdapter());
		connectDevice.connect(macAddress);
		connectDevice.start();
		return connectDevice.SendToRemoteDevice(msg);
	}

	@JavascriptInterface
	public String findDevice() throws JSONException {
		ConnectDevice connectDevice = new ConnectDevice(returnBTAdapter());
		return connectDevice.returnDeviceArray();
	}

}
