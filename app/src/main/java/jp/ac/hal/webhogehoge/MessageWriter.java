package jp.ac.hal.webhogehoge;

import android.bluetooth.BluetoothSocket;
import android.util.Log;

import java.io.IOException;
import java.io.OutputStream;

import static java.lang.Thread.sleep;

public class MessageWriter {
	private static final String TAG = "debug";
	private BluetoothSocket bluetoothSocket;
	private OutputStream outputStream;

	MessageWriter(BluetoothSocket bluetoothSocket) {
		this.bluetoothSocket = bluetoothSocket;
	}

	//	別クラスで書こう！
	void sendMessage(String str) throws IOException, InterruptedException {
		if (!this.bluetoothSocket.isConnected()) {
			Log.d(TAG, "コネクト中");
			sleep(2000);
		}
		this.outputStream = this.bluetoothSocket.getOutputStream();
		//文字列を送信する
		byte[] bytes;
		bytes = str.getBytes();
		try {
			this.outputStream.write(bytes);
			Log.d(TAG, "送信");
		} catch (IOException e) {
			throw e;
		}
	}
}
