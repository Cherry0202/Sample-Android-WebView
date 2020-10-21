package jp.ac.hal.webhogehoge;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

	//    定数
// Bluetooth機能の有効化要求時の識別コード
	private static final int REQUEST_ENABLE_BLUETOOTH = 1;

	// メンバー変数
	private BluetoothAdapter mBluetoothAdapter;    // BluetoothAdapter : Bluetooth処理で必要


	WebView myWebView = null;

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


//      Bluetoothアダプタ取得
		BluetoothManager bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
		mBluetoothAdapter = bluetoothManager.getAdapter();
//		Android端末がBluetoothをサポートしていない
		if (null == mBluetoothAdapter) {
			Toast.makeText(this, R.string.bluetooth_is_not_supported, Toast.LENGTH_SHORT).show();
//          アプリ終了
			finish();
			return;
		}

		// WebView呼び出し
		myWebView = (WebView) findViewById(R.id.webView);
		myWebView.setWebViewClient(new WebViewClient());
//        webview内でのjsを許可
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

}
