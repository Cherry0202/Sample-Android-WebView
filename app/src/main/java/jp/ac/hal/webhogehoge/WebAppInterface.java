package jp.ac.hal.webhogehoge;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
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
		Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
//		MainActivityから呼び出し
		jp.ac.hal.webhogehoge.MainActivity MA = new jp.ac.hal.webhogehoge.MainActivity();
		MA.sampleText();
	}
}