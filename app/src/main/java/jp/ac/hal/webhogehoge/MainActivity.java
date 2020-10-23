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
		String deviceHardwareAddress = findDevice();
		connectBluetoothDevice(deviceHardwareAddress);

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
		}
		return null;
	}

	private void connectBluetoothDevice(String deviceHardwareAddress) {
		mBtDevice = mBluetoothAdapter.getRemoteDevice(deviceHardwareAddress);
		try {
			// 接続に使用するプロファイルを指定
			MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
			mBtSocket = mBtDevice.createRfcommSocketToServiceRecord(MY_UUID);
			Log.d("debug", "connectBluetoothDevice: tryしてOK！");
		} catch (IOException e) {
			e.printStackTrace();
			Log.d("debug", "connectBluetoothDevice: out!!");
		}
		// ソケットを接続する
		try {
			Log.d("debug", "connectBluetoothDevice: ソケット接続tryしたよ");
//			TODO ここでエラー
			mBtSocket.connect();
			Log.d("debug", "connectBluetoothDevice: ソケット接続connectしたよ");
//			mOutput = mBtSocket.getOutputStream(); // 出力ストリームオブジェクトを得る
//			Log.d("debug", "connectBluetoothDevice: 出力ストリームオブジェクトを得る");
		} catch (IOException e) {
			e.printStackTrace();
			Log.d("debug", "connectBluetoothDevice:" + String.valueOf(e));
		}
	}

//	public void send() {
//		// TODO Auto-generated method stub
//		try {
//			Log.d("debug", "send: 出力ストリームtry");
//			mOutput.write('a');
//			Log.d("debug", "a送信いたよ");
//		} catch (IOException e) {
//			Log.d("debug", "send:" + String.valueOf(e));
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}
