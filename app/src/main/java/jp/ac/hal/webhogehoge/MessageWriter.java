package jp.ac.hal.webhogehoge;

import android.bluetooth.BluetoothSocket;
import android.util.Log;
import android.webkit.JavascriptInterface;

import java.io.IOException;
import java.io.OutputStream;

import static android.os.SystemClock.sleep;

class MessageWriter {
	private static final String TAG = "debug";
	private BluetoothSocket bluetoothSocket;
	private OutputStream outputStream;

	MessageWriter(BluetoothSocket bluetoothSocket) {
		this.bluetoothSocket = bluetoothSocket;
	}


	@JavascriptInterface
	public void sendMessage(String str) throws IOException {
		Log.d(TAG, str);
		if (!this.bluetoothSocket.isConnected()) {
			sleep(2000);
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
