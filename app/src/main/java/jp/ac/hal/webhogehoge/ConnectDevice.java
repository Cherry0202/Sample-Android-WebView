package jp.ac.hal.webhogehoge;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothSocket;
import android.util.Log;
import android.webkit.JavascriptInterface;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

class ConnectDevice extends Thread {
	private static final String Mac = "14:91:38:A0:80:27";
	private static final String Device = "Fire Tablet1";
	private static final String TAG = "debug";
	private static final String TAG2 = "device";
	private static final String BT_UUID = "fea4154d-4184-46b4-98a5-7896af703591";
	private BluetoothAdapter bluetoothAdapter; //BTアダプタ
	private final BluetoothManager bluetoothManager;
	private BluetoothDevice bluetoothDevice;
	private BluetoothSocket bluetoothSocket;
	private String macAddress;
	private OutputStream outputStream;
	private boolean running = true;

	ConnectDevice(BluetoothManager bluetoothManager) {
		this.bluetoothManager = bluetoothManager;
		this.bluetoothAdapter = bluetoothManager.getAdapter();
	}

	public void run() {
		if (!this.bluetoothSocket.isConnected()) {
			Log.d(TAG, String.valueOf("runだよ: " + this.bluetoothSocket.isConnected()));
			this.bluetoothAdapter.cancelDiscovery();
			try {
				this.bluetoothSocket.connect();
				Log.d(TAG, String.valueOf("run-connected: " + this.bluetoothSocket.isConnected()));
			} catch (IOException e) {
				try {
					Log.d(TAG, "close()呼び出されたよ...");
					this.bluetoothSocket.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	@JavascriptInterface
	public void connect(String str) throws IOException {
		try {
//			TODO frontとのやりとり追加　暫定コード
			this.macAddress = findDevice();
			this.bluetoothDevice = bluetoothAdapter.getRemoteDevice(macAddress);
			this.bluetoothSocket = bluetoothDevice.createInsecureRfcommSocketToServiceRecord(UUID.fromString(BT_UUID));
		} catch (Exception e) {
			throw e;
		}
		this.start();
		MessageWriter messageWriter = new MessageWriter(this.bluetoothSocket = returnSocket());
		messageWriter.sendMessage(str);
		this.bluetoothSocket.close();
	}

	BluetoothSocket returnSocket() {
		return this.bluetoothSocket;
	}


	//	TODO デバイス接続タイミングが明瞭になってから変更予定
	//	ペアリングしているデバイスがあるか
	private String findDevice() {
		Set<BluetoothDevice> pairedDevices = this.bluetoothAdapter.getBondedDevices();

		Log.d(TAG2, Arrays.toString(pairedDevices.toArray()));
		if (pairedDevices.size() > 0) {
			for (BluetoothDevice device : pairedDevices) {
				Log.d(TAG2, "device name: " + device.getName());
				String deviceName = device.getName();
				Log.d(TAG2, "mac address:" + device.getAddress());
				if (device.getAddress().equals(Mac) && deviceName.equals(Device)) {
//					TODO Occulusのdeviceのだった時
					return device.getAddress();
				} else {
					Log.d(TAG, "違うデバイスです");
				}
			}
		} else {
			Log.d(TAG, "pairing device is not found");
		}
		return null;
	}

	HashMap<String, String> returnDeviceArray() {
		Set<BluetoothDevice> pairedDevices = this.bluetoothAdapter.getBondedDevices();
		HashMap<String, String> hashmap = new HashMap<String, String>();//[1]
		if (pairedDevices.size() > 0) {
			for (BluetoothDevice device : pairedDevices) {
				Log.d(TAG2, "device name: " + device.getName());
				String deviceName = device.getName();
				Log.d(TAG2, "mac address:" + device.getAddress());
				hashmap.put(device.getName(), device.getAddress());//[4]
			}
			return hashmap;
		}
		return null;
	}
}

//　TODO socket close 送信後