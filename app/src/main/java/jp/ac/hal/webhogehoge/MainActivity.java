package jp.ac.hal.webhogehoge;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

	//    定数
// Bluetooth機能の有効化要求時の識別コード
	private static final int REQUEST_ENABLE_BLUETOOTH = 1;
	private static final String TAG = "debug";

	// メンバー変数
	//BTの設定
	private BluetoothAdapter mBluetoothAdapter; //BTアダプタ
	private BluetoothDevice mBtDevice; //BTデバイス
	private BluetoothSocket mBtSocket; //BTソケット
	private UUID MY_UUID; // uuid

	WebView myWebView = null;

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


//      Bluetoothアダプタ取得
		BluetoothManager bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
		mBluetoothAdapter = bluetoothManager.getAdapter();


		//端末がBluetoothに対応しているか
		if (BluetoothAdapter.getDefaultAdapter() == null) {
			Toast.makeText(this, "The device does not support Bluetooth.", Toast.LENGTH_SHORT).show();
			finish();
		}


//		以下 WebView関連

		// WebView呼び出し
		myWebView = (WebView) findViewById(R.id.webView);
		myWebView.setWebViewClient(new WebViewClient());
//        WebView内でのjsを許可
		myWebView.getSettings().setJavaScriptEnabled(true);
//       webAppInterfaceを使う
		myWebView.addJavascriptInterface(new WebAppInterface(this), "Android");

		myWebView.loadUrl("file:///android_asset/index2.html");

		ConnectThread CT = new ConnectThread(mBtDevice);
		CT.run();

	}

	//　初回表示、ポーズからの復帰時
	@Override
	protected void onResume() {
		super.onResume();

		requestBluetoothFeature();
	}

	//		Bluetooth機能の有効化を要求
	private void requestBluetoothFeature() {
		if (mBluetoothAdapter.isEnabled()) {
			return;
		}
		// デバイスのBluetooth機能が有効になっていないときは、有効化要求（ダイアログ表示）
		Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
		startActivityForResult(enableBtIntent, REQUEST_ENABLE_BLUETOOTH);
	}

	// 機能の有効化ダイアログの操作結果
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQUEST_ENABLE_BLUETOOTH) { // Bluetooth有効化要求
			if (Activity.RESULT_CANCELED == resultCode) {    // 有効にされなかった
				Toast.makeText(this, R.string.bluetooth_is_not_working, Toast.LENGTH_SHORT).show();
				finish();    // アプリ終了宣言
				return;
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	public static String sampleText() {
		Log.d(TAG, "呼び出されたよ！！");
		String javaText;
		return javaText = "sampleText";
	}

	public void sampleSend() {
		Log.d(TAG, "sampleSend");
		ConnectThread CT = new ConnectThread(mBtDevice);
		CT.send2();
	}


	//	以下bluetooth関連
	private class ConnectThread extends Thread {
		private final BluetoothSocket mmSocket;
		private final BluetoothDevice mmDevice;
		private InputStream mInput; //読み込みストリーム
		private OutputStream mOutput; //出力ストリーム

		//	ペアリングしているデバイスがあるか
		private String findDevice() {
			Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();

			if (pairedDevices.size() > 0) {
				// There are paired devices. Get the name and address of each paired device.
				for (BluetoothDevice device : pairedDevices) {
					Log.d(TAG, "device name: " + device.getName());
					String deviceName = device.getName();
					Log.d(TAG, "mac address:" + device.getAddress());
//					TODO Occulusのdeviceのだった時
					return device.getAddress();
				}
			} else {
				Log.d(TAG, "pairing device is not found");
				finish();
			}
			return null;
		}


		ConnectThread(BluetoothDevice device) {
			Log.d(TAG, "ConnectThread: 呼び出されたよ");
			// Use a temporary object that is later assigned to mmSocket
			// because mmSocket is final.
			BluetoothSocket tmp = null;
			String deviceHardwareAddress = findDevice();
			if (deviceHardwareAddress != null) {
				Log.d(TAG, deviceHardwareAddress);
			}
			mBtDevice = mBluetoothAdapter.getRemoteDevice(deviceHardwareAddress);
			mmDevice = device;

			try {
				Log.d(TAG, "ConnectThread: try!!");
				// Get a BluetoothSocket to connect with the given BluetoothDevice.
				// MY_UUID is the app's UUID string, also used in the server code.
				MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
				Log.d(TAG, String.valueOf(MY_UUID));
				tmp = mBtDevice.createRfcommSocketToServiceRecord(MY_UUID);
				Log.d(TAG, String.valueOf(tmp));
			} catch (IOException e) {
				Log.e(TAG, "Socket's create() method failed", e);
			}
			mmSocket = tmp;
			Log.d(TAG, "socket準備完了");
		}

		public void run() {
			// Cancel discovery because it otherwise slows down the connection.
			mBluetoothAdapter.cancelDiscovery();
			Log.d(TAG, "run: runだよ");

			try {
				// Connect to the remote device through the socket. This call blocks
				// until it succeeds or throws an exception.
				Log.d(TAG, "run: ソケット接続try中");
				mmSocket.connect();
				try {
					Log.d(TAG, "出力用オブジェクトを呼び出し中");
					mOutput = mmSocket.getOutputStream();
					Log.d(TAG, "出力用オブジェクトを呼び出し");
				} catch (IOException e) {
					e.printStackTrace();
					Log.d(TAG, "run: " + e);
				}
				try {
					Log.d(TAG, "読み込み用オブジェクトを呼び出し中");
					mInput = mmSocket.getInputStream();
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
//			送信処理
			send();
//			受信中...
			read(mInput);
		}

		//		BluetoothSocket mmSocket
		private void send() {
			//文字列を送信する
			byte[] bytes = {};
			String str = "Hello Server!!!";
			bytes = str.getBytes();
			try {
				mOutput.write(bytes);
				Log.d(TAG, "送信！");
			} catch (IOException e) {
				Log.d(TAG, "送信エラー:" + e);
				e.printStackTrace();
			}

		}

		private void send2() {
			Log.d(TAG, "send2");
			//文字列を送信する
			byte[] bytes = {};
			String str = "Hello World2!";
			bytes = str.getBytes();
			try {
				mOutput.write(bytes);
				Log.d(TAG, "送信！");
			} catch (IOException e) {
				Log.d(TAG, "送信エラー:" + e);
				e.printStackTrace();
			}
		}


		//			受信処理
		private void read(InputStream mInput) {
			while (true) {
				if (mInput != null) {
					Log.d(TAG, "受信待機中...");
					// InputStreamのバッファを格納
					byte[] buffer = new byte[1024];
					// 取得したバッファのサイズを格納
					int bytes;
					// InputStreamの読み込み
					try {
						bytes = mInput.read(buffer);
						Log.d(TAG, "input-stream読み込み");
						String msg = new String(buffer, 0, bytes);
						Log.d(TAG, "manageMyConnectedSocket: " + msg);
						mInput = null;
					} catch (IOException e) {
						e.printStackTrace();
						Log.d(TAG, "読み込み失敗" + e);
						break;
					}
				} else {
					break;
				}
			}

		}

		// Closes the client socket and causes the thread to finish.
		public void cancel() {
			try {
				mmSocket.close();
			} catch (IOException e) {
				Log.e(TAG, "Could not close the client socket", e);
			}
		}
	}

//	TODO 非同期受信

	//	非同期処理のクラス
	private class IncomingMessageTask extends AsyncTask<InputStream, Integer, String> {

		@Override
		protected String doInBackground(InputStream... mInput) {
			Log.d(TAG, "非同期受信待機中...");
			String msg = "あきおから受け取ったbyteを文字列変換させた値";
			return msg;
		}

		protected void onProgressUpdate(Integer... progress) {
		}

		protected void onPostExecute(String msg) {
//			ここでフロントに送信処理実行
		}
	}

}