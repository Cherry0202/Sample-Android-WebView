package jp.ac.hal.webhogehoge;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

import org.json.JSONArray;
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
			throw e;
		}
	}

	JSONObject sendToRemoteDevice(String msg) throws IOException, JSONException {
		JsonObjectCreator jsonObjectCreator = new JsonObjectCreator();
		MessageWriter messageWriter = new MessageWriter(this.bluetoothSocket);
		Boolean jsonValue = messageWriter.sendMessage(msg);
		jsonObjectCreator.putObjectValues(ConstValue.RESULT.getConstValue(), jsonValue.toString());
		this.bluetoothSocket.close();
		return jsonObjectCreator.getJsonObject();
	}

	Object getDeviceArray() throws JSONException {
		Set<BluetoothDevice> pairedDevices = this.bluetoothAdapter.getBondedDevices();
		if (pairedDevices.size() > 0) {
			JSONArray jsonArray = new JSONArray();
			for (BluetoothDevice device : pairedDevices) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put(ConstValue.DEVICE_NAME.getConstValue(), device.getName());
				jsonObject.put(ConstValue.MAC_ADDRESS.getConstValue(), device.getAddress());
				jsonArray.put(jsonObject);
			}
			return jsonArray;
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(ConstValue.RESULT.getConstValue(), ConstValue.DEVICE_NOT_FOUND.getConstValue());
		return jsonObject;
	}
}