package jp.ac.hal.webhogehoge;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

	private BluetoothManager bluetoothManager;
	private BluetoothAdapter bluetoothAdapter;


	WebView myWebView = null;

	@SuppressLint({"SetJavaScriptEnabled", "JavascriptInterface"})
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		this.bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
		this.bluetoothAdapter = bluetoothManager.getAdapter();

//		以下 WebView関連
		myWebView = findViewById(R.id.webView);
		WebView.setWebContentsDebuggingEnabled(true);
		myWebView.setWebViewClient(new WebViewClient());
//        WebView内でのjsを許可
		myWebView.getSettings().setJavaScriptEnabled(true);
//       webAppInterfaceを使う
		myWebView.addJavascriptInterface(new ThreadManager(this.bluetoothAdapter), "Android");
//		myWebView.loadUrl("file:///android_asset/index2.html");
		myWebView.loadUrl("file:///android_asset/dist/index.html");
	}

	//　初回表示、ポーズからの復帰時
	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	public void onDestroy() {
		myWebView.destroy();
		super.onDestroy();
	}
}
