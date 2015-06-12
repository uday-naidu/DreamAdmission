package com.dreamadmission;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class CollegeWebView extends Activity{

	/*@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.college_web_view);
		Intent intent = getIntent();
		String collgurl = intent.getExtras().getString("collgurl");
		WebView wv = (WebView)findViewById(R.id.webview);
		wv.getSettings().setJavaScriptEnabled(true);
		wv.loadUrl(collgurl);
		
	}*/
	
	WebView webview;
	ProgressBar loadingProgressBar,loadingTitle;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.updates);
		webview = (WebView)findViewById(R.id.webview);
		webview.getSettings().setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
		Intent intent = getIntent();
		String collgurl = intent.getExtras().getString("collgurl");
		webview.getSettings().setJavaScriptEnabled(true);
		webview.loadUrl(collgurl);
		webview.setWebViewClient(new MyWebViewClient());
		 
		loadingProgressBar=(ProgressBar)findViewById(R.id.progressbar_Horizontal); 
		 
		webview.setWebChromeClient(new WebChromeClient() {

		 // this will be called on page loading progress

		 @Override

		 public void onProgressChanged(WebView view, int newProgress) {

		 super.onProgressChanged(view, newProgress);


		 loadingProgressBar.setProgress(newProgress);
		
		 //loadingTitle.setProgress(newProgress);
		 // hide the progress bar if the loading is complete
		 

		 if (newProgress == 100) {
		 loadingProgressBar.setVisibility(View.GONE);
		 
		 } else{
		 loadingProgressBar.setVisibility(View.VISIBLE);
		 
		 }

		 }

		 });	
	}	
	@Override
	 public boolean onKeyDown(int keyCode, KeyEvent event) {
	 
	 if(keyCode == KeyEvent.KEYCODE_BACK){
	 finish();
	 }
	 return super.onKeyDown(keyCode, event);
	 }

	 private class MyWebViewClient extends WebViewClient {

	@Override
	public boolean shouldOverrideUrlLoading(WebView view, String url) {

	view.loadUrl(url);
	return true;
	}
	}

}
