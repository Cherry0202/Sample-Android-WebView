package jp.ac.hal.webhogehoge;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothSocket;
import android.webkit.JavascriptInterface;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Set;
import java.util.UUID;

class ConnectDevice extends Thread {
	private static final String BT_UUID = "fea4154d-4184-46b4-98a5-7896af703591";
	private BluetoothAdapter bluetoothAdapter;
	private final BluetoothManager bluetoothManager;
	private BluetoothDevice bluetoothDevice;
	private BluetoothSocket bluetoothSocket;

	ConnectDevice(BluetoothManager bluetoothManager) {
		this.bluetoothManager = bluetoothManager;
		this.bluetoothAdapter = bluetoothManager.getAdapter();
	}

	public void run() {
		if (!this.bluetoothSocket.isConnected()) {
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
	}

	@JavascriptInterface
	public String connect(String msg, String macAddress) throws IOException {
		try {
			this.bluetoothDevice = bluetoothAdapter.getRemoteDevice(macAddress);
			this.bluetoothSocket = bluetoothDevice.createInsecureRfcommSocketToServiceRecord(UUID.fromString(BT_UUID));
		} catch (Exception e) {
			return e.toString();
		}
		this.start();
		MessageWriter messageWriter = new MessageWriter(this.bluetoothSocket = returnSocket());
		messageWriter.sendMessage(msg);
		this.bluetoothSocket.close();
		return "OK";
	}

	private BluetoothSocket returnSocket() {
		return this.bluetoothSocket;
	}

	private BluetoothAdapter returnBluetoothAdapter() {
		return this.bluetoothAdapter;
	}

	@JavascriptInterface
	public String returnDeviceArray() throws JSONException {
		Set<BluetoothDevice> pairedDevices = returnBluetoothAdapter().getBondedDevices();
		JsonArrayCreator jsonArrayCreator = new JsonArrayCreator();
		if (pairedDevices.size() > 0) {
			for (BluetoothDevice device : pairedDevices) {
				JSONObject jsonObject = jsonArrayCreator.returnJsonObject();
				jsonArrayCreator.objectPutter("device-name", device.getName());
				jsonArrayCreator.objectPutter("mac-address", device.getAddress());
				jsonArrayCreator.arrayPutter(jsonObject);
			}
			return jsonArrayCreator.returnJsonArray().toString();
		}
		return null;
	}
}