package jp.ac.hal.webhogehoge;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

	//    定数
// Bluetooth機能の有効化要求時の識別コード
	private static final int REQUEST_ENABLE_BLUETOOTH = 1;
	private static final String TAG = "debug";
	private boolean isRepeat = true;
	//繰り返し間隔（ミリ秒）
	private final int REPEAT_INTERVAL = 1000;
	//	private static int status = 0;
	private String msg = "初期値";
	private Handler mHandler = new Handler();

	// メンバー変数
	//BTの設定
	public static BluetoothAdapter mBluetoothAdapter; //BTアダプタ
	//	public static BluetoothDevice bluetoothDevice; //BTデバイス
	private BluetoothSocket mBtSocket; //BTソケット
	static OutputStream mOutput; //出力ストリーム
	private BluetoothManager bluetoothManager;

//	private ConnectDevice connectDevice;

	WebView myWebView = null;

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

//      Bluetoothアダプタ取得
		ConnectDevice connectDevice = new ConnectDevice((BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE));
		try {
			connectDevice.connect();
		} catch (IOException e) {
			e.printStackTrace();
		}
		connectDevice.start();
		BluetoothSocket btSocket = connectDevice.returnSocket();
		MessageWriter messageWriter = new MessageWriter(btSocket);

//		test
		try {
			messageWriter.sendMessage("foofooofooo");
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
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
//		myWebView.loadUrl("file:///android_asset/dist2/index.html");

	}

	//　初回表示、ポーズからの復帰時
	@Override
	protected void onResume() {
		super.onResume();
//		requestBluetoothFeature();
	}

	//		//端末がBluetoothに対応しているか
//		if (BluetoothAdapter.getDefaultAdapter() == null) {
//			Toast.makeText(this, "The device does not support Bluetooth.", Toast.LENGTH_SHORT).show();
//			finish();
//		}


	//	//		Bluetooth機能の有効化を要求
//	private void requestBluetoothFeature() {
//		if (mBluetoothAdapter.isEnabled()) {
//			return;
//		}
//		// デバイスのBluetooth機能が有効になっていないときは、有効化要求（ダイアログ表示）
//		Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
//		startActivityForResult(enableBtIntent, REQUEST_ENABLE_BLUETOOTH);
//	}

	// 機能の有効化ダイアログの操作結果
//	@Override
//	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//		if (requestCode == REQUEST_ENABLE_BLUETOOTH) { // Bluetooth有効化要求
//			if (Activity.RESULT_CANCELED == resultCode) {    // 有効にされなかった
//				Toast.makeText(this, R.string.bluetooth_is_not_working, Toast.LENGTH_SHORT).show();
//				finish();    // アプリ終了宣言
//				return;
//			}
//		}
//		super.onActivityResult(requestCode, resultCode, data);
//	}

	public static String sampleText() {
		Log.d(TAG, "呼び出されたよ！！");
		String javaText;
		return javaText = "sampleText";
	}

	public void sampleSend() {
		Log.d(TAG, "sampleSend");
//		send2(mBtSocket);
	}

	//	jsのfunction呼び出し
	public void inComingText(final String msgText) {
		Log.d(TAG, "incomingText: " + msgText);
		mHandler.post(new Runnable() {
			@Override
			public void run() {
				myWebView.loadUrl("javascript:inComingText('" + msgText + "')");
			}
		});
	}

	private void send2(BluetoothSocket mBtSocket) throws IOException {
		Log.d(TAG, "send2");
		//文字列を送信する
		byte[] bytes;
		String str = "Hello Server2!";
		bytes = str.getBytes();
		mOutput = mBtSocket.getOutputStream();
		try {
			mOutput.write(bytes);
			Log.d(TAG, "送信！");
		} catch (IOException e) {
			Log.d(TAG, "送信エラー:" + e);
			e.printStackTrace();
		}
	}
}
