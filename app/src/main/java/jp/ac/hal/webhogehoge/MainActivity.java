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
import android.os.Handler;
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
	private boolean isRepeat = true;
	//繰り返し間隔（ミリ秒）
	private final int REPEAT_INTERVAL = 1000;
	private static int status = 0;
	private String msg = "初期値";
	private Handler mHandler = new Handler();

	// メンバー変数
	//BTの設定
	public BluetoothAdapter mBluetoothAdapter; //BTアダプタ
	public static BluetoothDevice mBtDevice; //BTデバイス
	private BluetoothSocket mBtSocket; //BTソケット
	static OutputStream mOutput; //出力ストリーム
	static InputStream mInput; //読み込みストリーム


//	private ConnectDevice connectDevice;

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
		myWebView = findViewById(R.id.webView);
		myWebView.setWebViewClient(new WebViewClient());
//        WebView内でのjsを許可
		myWebView.getSettings().setJavaScriptEnabled(true);
//       webAppInterfaceを使う
		myWebView.addJavascriptInterface(new WebAppInterface(this), "Android");

		myWebView.loadUrl("file:///android_asset/index2.html");

		final ConnectDevice CD = new ConnectDevice();
//		デモ用送信
		CD.send();

		Runnable looper = new Runnable() {
			@Override
			public void run() {
//isRepeatがtrueなら処理を繰り返す
				while (isRepeat) {
					try {
						Thread.sleep(REPEAT_INTERVAL);
					} catch (InterruptedException e) {
						Log.e("looper", "InterruptedException");
					}
//繰り返し処理
					while (true) {
						Log.d(TAG, "受信待機中...");
						if (mInput != null) {
							// InputStreamのバッファを格納
							byte[] buffer = new byte[1024];
							// 取得したバッファのサイズを格納
							int bytes;
							// InputStreamの読み込み
							try {
								Log.d(TAG, "input-stream読み込み1");
								bytes = mInput.read(buffer);
								Log.d(TAG, "input-stream読み込み2");
								String msg = new String(buffer, 0, bytes);
								Log.d(TAG, "manageMyConnectedSocket: " + msg);
//								CD.mInput = null;
//								Log.d(TAG, "nullにしたよ");
							} catch (IOException e) {
								e.printStackTrace();
								Log.d(TAG, "読み込み失敗" + e);
								break;
							}
						} else {
							Log.d(TAG, "送られてないよ！");
							break;
						}
					}
				}
			}
		};

		//スレッド起動
		Thread thread = new Thread(looper);
		thread.start();
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
		send2(mOutput);
	}


	//	jsのfunction呼び出し
	public void inComingText(final String msgText) {
		Log.d(TAG, "incommingText: " + msgText);
		mHandler.post(new Runnable() {
			@Override
			public void run() {
				myWebView.loadUrl("javascript:inComingText('" + msgText + "')");
			}
		});
	}

	private void send2(OutputStream mOutput) {
		Log.d(TAG, "send2");
		//文字列を送信する
		byte[] bytes;
		String str = "Hello Server2!";
		bytes = str.getBytes();
		try {
			mOutput.write(bytes);
			Log.d(TAG, "送信！");
		} catch (IOException e) {
			Log.d(TAG, "送信エラー:" + e);
			e.printStackTrace();
		}
	}


	//	//	以下bluetooth関連
	public class ConnectDevice {
		private UUID MY_UUID;

		private static final String Mac = "14:91:38:A0:80:27";
		private static final String Device = "Fire Tablet1";

		//	ペアリングしているデバイスがあるか
		private String findDevice() {
			Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();

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
				finish();
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

				mBtDevice = mBluetoothAdapter.getRemoteDevice(deviceHardwareAddress);

				try {
					Log.d(TAG, "ConnectDevice: try!!");
					// Get a BluetoothSocket to connect with the given BluetoothDevice.
					// MY_UUID is the app's UUID string, also used in the server code.
					// uuid
					UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
					Log.d(TAG, String.valueOf(MY_UUID));
					tmp = mBtDevice.createRfcommSocketToServiceRecord(MY_UUID);
					Log.d(TAG, String.valueOf(tmp));
				} catch (IOException e) {
					Log.e(TAG, "Socket's create() method failed", e);
				}
				BluetoothSocket mmSocket = tmp;
				Log.d(TAG, "socket準備完了");
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
				status = 1;
			} else {
				Log.d(TAG, "認証できてるよ" + status);
			}
		}

		//		BluetoothSocket mmSocket
		private void send() {
			//文字列を送信する
			byte[] bytes;
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
						Log.d(TAG, "input-stream読み込み1");
						bytes = mInput.read(buffer);
						Log.d(TAG, "input-stream読み込み2");
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
	}
}
