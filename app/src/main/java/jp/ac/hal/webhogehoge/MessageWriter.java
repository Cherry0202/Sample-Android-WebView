package jp.ac.hal.webhogehoge;

import android.bluetooth.BluetoothSocket;
import android.os.SystemClock;

import java.io.IOException;

class MessageWriter {
	private BluetoothSocket bluetoothSocket;

	MessageWriter(BluetoothSocket bluetoothSocket) {
		this.bluetoothSocket = bluetoothSocket;
	}

	Boolean sendMessage(String str) throws IOException {
		long start = System.currentTimeMillis();
		while (!this.bluetoothSocket.isConnected()) {
			SystemClock.sleep(100);
			long end = System.currentTimeMillis();
			if ((end - start) > 7000) {
				return false;
			}
		}
		//文字列を送信する
		byte[] bytes;
		bytes = str.getBytes();
		try {
			this.bluetoothSocket.getOutputStream().write(bytes);
		} catch (IOException e) {
			throw e;
		}
		return true;
	}
}
