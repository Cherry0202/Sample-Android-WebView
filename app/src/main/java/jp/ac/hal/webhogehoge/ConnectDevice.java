package jp.ac.hal.webhogehoge;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Set;
import java.util.UUID;

class ConnectDevice extends Thread {
	private static final String BT_UUID = "fea4154d-4184-46b4-98a5-7896af703591";
	private BluetoothAdapter bluetoothAdapter;
	private BluetoothDevice bluetoothDevice;
	private BluetoothSocket bluetoothSocket;

	ConnectDevice(BluetoothAdapter bluetoothAdapter) {
		this.bluetoothAdapter = bluetoothAdapter;
	}

	public void run() {
		this.bluetoothAdapter.cancelDiscovery();
		try {
			this.bluetoothSocket.connect();
		} catch (IOException e) {
			try {
				this.bluetoothSocket.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	String connect(String macAddress) throws IOException {
		try {
			this.bluetoothDevice = this.bluetoothAdapter.getRemoteDevice(macAddress);
			this.bluetoothSocket = this.bluetoothDevice.createInsecureRfcommSocketToServiceRecord(UUID.fromString(BT_UUID));
		} catch (Exception e) {
			return e.toString();
		}
		return "OK";
	}

	String SendToRemoteDevice(String msg) throws IOException {
		MessageWriter messageWriter = new MessageWriter(this.bluetoothSocket = getSocket());
		String result = messageWriter.sendMessage(msg);
		this.bluetoothSocket.close();
		return result;
	}

	private BluetoothSocket getSocket() {
		return this.bluetoothSocket;
	}

	String getDeviceArray() throws JSONException {
		Set<BluetoothDevice> pairedDevices = this.bluetoothAdapter.getBondedDevices();
		JsonArrayCreator jsonArrayCreator = new JsonArrayCreator();
		if (pairedDevices.size() > 0) {
			for (BluetoothDevice device : pairedDevices) {
				JSONObject jsonObject = jsonArrayCreator.getJsonObject();
				jsonArrayCreator.objectPutter("deviceName", device.getName());
				jsonArrayCreator.objectPutter("macAddress", device.getAddress());
				jsonArrayCreator.arrayPutter(jsonObject);
			}
			return jsonArrayCreator.getJsonArray().toString();
		}
		return null;
	}
}