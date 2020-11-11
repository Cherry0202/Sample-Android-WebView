package jp.ac.hal.webhogehoge;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;

//	//	以下bluetooth関連
class ConnectDevice extends Thread {
	private static final String Mac = "14:91:38:A0:80:27";
	private static final String Device = "Fire Tablet1";
	private static final String TAG = "debug";
	private static final String TAG2 = "device";
	private static final String BT_UUID = "fea4154d-4184-46b4-98a5-7896af703591";
	private final BluetoothAdapter bluetoothAdapter; //BTアダプタ
	private BluetoothDevice bluetoothDevice;
	private BluetoothSocket bluetoothSocket;

	ConnectDevice(BluetoothManager bluetoothManager) {
		this.bluetoothAdapter = bluetoothManager.getAdapter();
	}

	public void run() {
		bluetoothAdapter.cancelDiscovery();
		try {
			bluetoothSocket.connect();
		} catch (IOException e) {
			try {
				bluetoothSocket.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	void connect() throws IOException {
		try {
//			TODO frontとのやりとり追加　暫定
			String macAddress = findDevice();
			bluetoothDevice = bluetoothAdapter.getRemoteDevice(macAddress);
			bluetoothSocket = bluetoothDevice.createInsecureRfcommSocketToServiceRecord(UUID.fromString(BT_UUID));
		} catch (Exception e) {
			throw e;
		}

//		bluetoothSocket.isConnected()あとで使いそ

//		非同期処理
		bluetoothSocket.getInputStream();
		bluetoothSocket.getOutputStream();
//		デモ用
	}

	private void send(BluetoothSocket bluetoothSocket, String str) throws IOException {
		OutputStream outputStream;
		if (bluetoothSocket != null) {
			outputStream = bluetoothSocket.getOutputStream();
		} else {
			return;
		}
		//文字列を送信する
		byte[] bytes;
		bytes = str.getBytes();
		try {
			outputStream.write(bytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//	TODO デバイス接続タイミングが明瞭になってから変更予定
	//	ペアリングしているデバイスがあるか
	private String findDevice() {
		Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();

		if (pairedDevices.size() > 0) {
			// There are paired devices. Get the name and address of each paired device.
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
}
