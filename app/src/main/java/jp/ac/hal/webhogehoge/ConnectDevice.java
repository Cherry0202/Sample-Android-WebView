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

	void connect(String macAddress) throws IOException {
		try {
			this.bluetoothDevice = this.bluetoothAdapter.getRemoteDevice(macAddress);
			this.bluetoothSocket = this.bluetoothDevice.createInsecureRfcommSocketToServiceRecord(UUID.fromString(ConstValue.BT_UUID.getConstValue()));
		} catch (Exception e) {
			e.toString();
		}
	}

	JSONObject sendToRemoteDevice(String msg) throws IOException, JSONException {
		JsonArrayCreator jsonArrayCreator = new JsonArrayCreator();
		MessageWriter messageWriter = new MessageWriter(this.bluetoothSocket);
		Boolean jsonValue = messageWriter.sendMessage(msg);
		jsonArrayCreator.objectPutter(ConstValue.RESULT.getConstValue(), jsonValue.toString());
		this.bluetoothSocket.close();
		return jsonArrayCreator.getJsonObject();
	}

	Object getDeviceArray() throws JSONException {
		Set<BluetoothDevice> pairedDevices = this.bluetoothAdapter.getBondedDevices();
		JsonArrayCreator jsonArrayCreator = new JsonArrayCreator();
		if (pairedDevices.size() > 0) {
			for (BluetoothDevice device : pairedDevices) {
				JSONObject jsonObject = jsonArrayCreator.getJsonObject();
				jsonArrayCreator.objectPutter(ConstValue.DEVICE_NAME.getConstValue(), device.getName());
				jsonArrayCreator.objectPutter(ConstValue.MAC_ADDRESS.getConstValue(), device.getAddress());
				jsonArrayCreator.arrayPutter(jsonObject);
			}
			return jsonArrayCreator.getJsonArray();
		}
		jsonArrayCreator.objectPutter(ConstValue.RESULT.getConstValue(), ConstValue.DEVICE_NOT_FOUND.getConstValue());
		return jsonArrayCreator.getJsonObject();
	}
}