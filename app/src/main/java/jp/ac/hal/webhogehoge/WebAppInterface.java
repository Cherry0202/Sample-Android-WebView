package jp.ac.hal.webhogehoge;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

public class WebAppInterface {
	private BluetoothAdapter mBluetoothAdapter; //BTアダプタ
	private Context mContext;

	/**
	 * Instantiate the interface and set the context
	 */
	WebAppInterface(Context c) {
		mContext = c;
	}

	/**
	 * Show a toast from the web page
	 */
	@JavascriptInterface
	public void showToast(String toast) {
//		MainActivityから呼び出し
		jp.ac.hal.webhogehoge.MainActivity MA = new jp.ac.hal.webhogehoge.MainActivity();
		String test = MainActivity.sampleText();
		Log.d("data from client", "showToast: " + toast);
		Toast.makeText(mContext, test, Toast.LENGTH_SHORT).show();

	}
}