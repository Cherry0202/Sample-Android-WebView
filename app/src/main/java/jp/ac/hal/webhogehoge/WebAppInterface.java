package jp.ac.hal.webhogehoge;

import android.content.Context;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

public class WebAppInterface {
	private Context mContext;

	/**
	 * Instantiate the interface and set the context
	 */
	WebAppInterface(Context c) {
		mContext = c;
	}

	/**
	 * Show a toast from the web page
	 *
	 * @return
	 */
	@JavascriptInterface
	public String showToast(String toast) {
//		MainActivityから呼び出し
		MainActivity MA = new MainActivity();
		String test = MainActivity.sampleText();
		Log.d("data from client", "showToast: " + toast);
		Toast.makeText(mContext, test, Toast.LENGTH_SHORT).show();
		return test;
	}

	@JavascriptInterface
	public void sendSampleText() {
		Log.d("debug", "send sample text 呼び出しできたよ");
		MainActivity mainActivity = new MainActivity();
		mainActivity.sampleSend();
	}
}