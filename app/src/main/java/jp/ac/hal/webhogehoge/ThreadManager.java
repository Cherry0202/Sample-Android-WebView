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

	@JavascriptInterface
	public String threadMaker(String macAddress, String msg) throws IOException {
		ConnectDevice connectDevice = new ConnectDevice(this.bluetoothAdapter);
		connectDevice.connect(macAddress);
		connectDevice.start();
		return connectDevice.SendToRemoteDevice(msg);
	}

	@JavascriptInterface
	public String findDevice() throws JSONException {
		ConnectDevice connectDevice = new ConnectDevice(this.bluetoothAdapter);
		return connectDevice.returnDeviceArray();
	}

}
