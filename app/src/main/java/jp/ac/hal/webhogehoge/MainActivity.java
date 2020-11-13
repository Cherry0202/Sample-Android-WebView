package jp.ac.hal.webhogehoge;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

	//    定数
// Bluetooth機能の有効化要求時の識別コード
	// メンバー変数
	//BTの設定
	private BluetoothAdapter mBluetoothAdapter; //BTアダプタ
	public BluetoothSocket btSocket; //BTソケット
	private Handler mHandler = new Handler();


	WebView myWebView = null;

	@SuppressLint({"SetJavaScriptEnabled", "JavascriptInterface"})
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ConnectDevice connectDevice = new ConnectDevice((BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE));
		HashMap sampleMap = connectDevice.returnDeviceArray();
//		以下 WebView関連

		// WebView呼び出し
		myWebView = findViewById(R.id.webView);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			WebView.setWebContentsDebuggingEnabled(true);
		}
		myWebView.setWebViewClient(new WebViewClient());
//        WebView内でのjsを許可
		myWebView.getSettings().setJavaScriptEnabled(true);
//       webAppInterfaceを使う
		myWebView.addJavascriptInterface(new ConnectDevice((BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE)), "Android");
		myWebView.loadUrl("file:///android_asset/index2.html");
		myWebView.loadUrl("javascript:inComingText('表示されます');");
//		inComingText(sampleMap, myWebView);
	}

	//　初回表示、ポーズからの復帰時
	@Override
	protected void onResume() {
		super.onResume();
//		requestBluetoothFeature();
	}

	//	jsのfunction呼び出し
	public void inComingText(final HashMap sampleMap, WebView myWebView) {
		Log.d("debug", "incomingText呼び出し");
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
}
