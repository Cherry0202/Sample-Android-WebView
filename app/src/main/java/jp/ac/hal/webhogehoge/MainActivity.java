package jp.ac.hal.webhogehoge;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

	//    定数
// Bluetooth機能の有効化要求時の識別コード
	private static final int REQUEST_ENABLE_BLUETOOTH = 1;

	// メンバー変数
	//BTの設定
	private BluetoothAdapter mBluetoothAdapter; //BTアダプタ
	private BluetoothDevice mBtDevice; //BTデバイス
	private BluetoothSocket mBtSocket; //BTソケット
	private OutputStream mOutput; //出力ストリーム
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


//		ペアリングしているデバイスがあるか
//		String deviceHardwareAddress = findDevice();
////		connectBluetoothDevice(deviceHardwareAddress);
		ConnectThread CT = new ConnectThread(mBtDevice);
		CT.run();

//		send();

//		以下 WebView関連

		// WebView呼び出し
		myWebView = (WebView) findViewById(R.id.webView);
		myWebView.setWebViewClient(new WebViewClient());
//        WebView内でのjsを許可
		myWebView.getSettings().setJavaScriptEnabled(true);
//       webAppInterfaceを使う
		myWebView.addJavascriptInterface(new WebAppInterface(this), "Android");
		myWebView.loadUrl("file:///android_asset/index2.html");
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
		Log.d("debug", "呼び出されたよ！！");
		String javaText;
		return javaText = "sampleText";
	}


	//	以下bluetooth関連
	private class ConnectThread extends Thread {
		private static final String TAG = "debug";
		private final BluetoothSocket mmSocket;
		private final BluetoothDevice mmDevice;

		//	ペアリングしているデバイスがあるか
		private String findDevice() {
			Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();

			if (pairedDevices.size() > 0) {
				// There are paired devices. Get the name and address of each paired device.
				for (BluetoothDevice device : pairedDevices) {
					String deviceName = device.getName();
					String deviceHardwareAddress = device.getAddress(); // MAC address
					Log.d("search-device", "device name:" + deviceName + "mac address:" + deviceHardwareAddress);
					return deviceHardwareAddress;
				}
			} else {
				Log.d("search-device", "pairing device is not found");
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
			assert deviceHardwareAddress != null;
			Log.d(TAG, deviceHardwareAddress);
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
			} catch (IOException connectException) {
				// Unable to connect; close the socket and return.
				try {
					Log.d(TAG, "run: ソケット接続残念！");
					mmSocket.close();
				} catch (IOException closeException) {
					Log.e(TAG, "Could not close the client socket", closeException);
				}
				return;
			}

			// TODO 送信処理を追加
			manageMyConnectedSocket(mmSocket);
		}

		private void manageMyConnectedSocket(BluetoothSocket mmSocket) {
			Log.d(TAG, "認証できたよ");
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
}