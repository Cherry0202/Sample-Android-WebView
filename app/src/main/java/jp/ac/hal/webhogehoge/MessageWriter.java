package jp.ac.hal.webhogehoge;

import android.bluetooth.BluetoothSocket;
import android.os.SystemClock;

import java.io.IOException;
import java.io.OutputStream;

class MessageWriter {
	private BluetoothSocket bluetoothSocket;
	private OutputStream outputStream;

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
		this.outputStream = this.bluetoothSocket.getOutputStream();
		//文字列を送信する
		byte[] bytes;
		bytes = str.getBytes();
		try {
			this.outputStream.write(bytes);
		} catch (IOException e) {
			throw e;
		}
		return true;
	}
}
