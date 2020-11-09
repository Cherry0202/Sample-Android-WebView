package jp.ac.hal.webhogehoge;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

import java.io.IOException;
import java.util.Set;
import java.util.UUID;

//	//	以下bluetooth関連
class ConnectDevice {
	private static final String Mac = "14:91:38:A0:80:27";
	private static final String Device = "Fire Tablet1";
	private static final String TAG = "debug";
	private static final String TAG2 = "device";
	private static int status = 0;

	//	ペアリングしているデバイスがあるか
	private String findDevice() {
		Set<BluetoothDevice> pairedDevices = MainActivity.mBluetoothAdapter.getBondedDevices();

		if (pairedDevices.size() > 0) {
			// There are paired devices. Get the name and address of each paired device.
			for (BluetoothDevice device : pairedDevices) {
				Log.d(TAG, "device name: " + device.getName());
				String deviceName = device.getName();
				Log.d(TAG, "mac address:" + device.getAddress());
				if (device.getAddress().equals(Mac) && deviceName.equals(Device)) {
//					TODO Occulusのdeviceのだった時
					status = 1;
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


	ConnectDevice() {
		Log.d(TAG, "ConnectThread: 呼び出されたよ" + status);
		// Use a temporary object that is later assigned to mmSocket
		// because mmSocket is final.
		BluetoothSocket tmp = null;
		if (status == 0) {
			String deviceHardwareAddress = findDevice();
			if (deviceHardwareAddress != null) {
				Log.d(TAG, deviceHardwareAddress);
			}

			MainActivity.mBtDevice = MainActivity.mBluetoothAdapter.getRemoteDevice(deviceHardwareAddress);

			try {
				Log.d(TAG, "ConnectDevice: try!!");
				// Get a BluetoothSocket to connect with the given BluetoothDevice.
				// MY_UUID is the app's UUID string, also used in the server code.
				// uuid
				UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
				Log.d(TAG, String.valueOf(MY_UUID));
				tmp = MainActivity.mBtDevice.createRfcommSocketToServiceRecord(MY_UUID);
				Log.d(TAG, String.valueOf(tmp));
			} catch (IOException e) {
				Log.e(TAG, "Socket's create() method failed", e);
			}
			BluetoothSocket mmSocket = tmp;
			Log.d(TAG, "socket準備完了");
			MainActivity.mBluetoothAdapter.cancelDiscovery();
			Log.d(TAG, "run: runだよ");


			try {
				// Connect to the remote device through the socket. This call blocks
				// until it succeeds or throws an exception.
				Log.d(TAG, "run: ソケット接続try中");
				mmSocket.connect();
				try {
					Log.d(TAG, "出力用オブジェクトを呼び出し中");
					MainActivity.mOutput = mmSocket.getOutputStream();
					Log.d(TAG, "出力用オブジェクトを呼び出し");
				} catch (IOException e) {
					e.printStackTrace();
					Log.d(TAG, "run: " + e);
				}
				try {
					Log.d(TAG, "読み込み用オブジェクトを呼び出し中");
					MainActivity.mInput = mmSocket.getInputStream();
					Log.d(TAG, "読み込み用オブジェクトを呼び出し");
				} catch (IOException e) {
					e.printStackTrace();
					Log.d(TAG, "run: " + e);
				}


			} catch (IOException connectException) {
				// Unable to connect; close the socket and return.
				try {
					Log.d(TAG, "run: ソケット接続残念！" + connectException);
					mmSocket.close();
				} catch (IOException closeException) {
					Log.e(TAG, "Could not close the client socket", closeException);
				}
				return;
			}
			Log.d(TAG, "認証できたよ");
			status = 1;
		} else {
			Log.d(TAG, "認証できてるよ" + status);
		}
	}

	//		BluetoothSocket mmSocket
	void send() {
		//文字列を送信する
		byte[] bytes;
		String str = "Hello Server!!!";
		bytes = str.getBytes();
		try {
			MainActivity.mOutput.write(bytes);
			Log.d(TAG, "送信！");
		} catch (IOException e) {
			Log.d(TAG, "送信エラー:" + e);
			e.printStackTrace();
		}
	}
}
