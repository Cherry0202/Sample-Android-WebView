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

	void sendMessage(String str) throws IOException {
		if (!this.bluetoothSocket.isConnected()) {
			SystemClock.sleep(2000);
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
	}
}
