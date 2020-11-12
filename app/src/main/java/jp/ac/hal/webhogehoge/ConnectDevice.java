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
//		とりま
//		this.bluetoothSocket = returnSocket();
//		サブスレッドの終了条件、isConnectedでもよさそ
		if (!this.bluetoothSocket.isConnected()) {
			Log.d(TAG, String.valueOf("runだよ: " + this.bluetoothSocket.isConnected()));
			this.bluetoothAdapter.cancelDiscovery();
			try {
				this.bluetoothSocket.connect();
				Log.d(TAG, String.valueOf("run-connected: " + this.bluetoothSocket.isConnected()));
				//				ここのタイミングはOK
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

	void connect() throws IOException {
		try {
//			TODO frontとのやりとり追加　暫定
			this.macAddress = findDevice();
			this.bluetoothDevice = bluetoothAdapter.getRemoteDevice(macAddress);
			this.bluetoothSocket = bluetoothDevice.createInsecureRfcommSocketToServiceRecord(UUID.fromString(BT_UUID));
		} catch (Exception e) {
			throw e;
		}
	}

	private BluetoothSocket returnSocket() {
		return this.bluetoothSocket;
	}

	void send(String str) throws IOException, InterruptedException {
		if (!this.bluetoothSocket.isConnected()) {
			sleep(1100);
		}
		BluetoothSocket btSocket = returnSocket();
		outputStream = btSocket.getOutputStream();
		//文字列を送信する
		byte[] bytes;
		bytes = str.getBytes();
		try {
			outputStream.write(bytes);
			Log.d(TAG, "送信なう");
		} catch (IOException e) {
			throw e;
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
