package com.dreamadmission;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

public class Updates extends Activity {

	WebView webview;
	ProgressBar loadingProgressBar,loadingTitle;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.updates);
		webview = (WebView)findViewById(R.id.webview);
		webview.getSettings().setJavaScriptEnabled(true);
		webview.loadUrl("http://dreamadmission.jelastic.servint.net/advertise.html");
		//webview.setWebViewClient(new MyWebViewClient());
		webview.setWebViewClient(new WebViewClient()
        {
            public void onLoadResource (WebView view, String url) 
            {
         	   int i = url.lastIndexOf('?');
         	   if(i!=-1)
         	       url = url.substring(0,i);
               // Main.log(url);
                if (url.endsWith(".pdf"))
                {Toast.makeText(Updates.this,"please refresh if download does not start",5000).show();
                   // finish();
             	   startActivity(new Intent(Intent.ACTION_VIEW, 
		    			    Uri.parse(url)));
             	  front();
                }
                else if (url.endsWith(".doc"))
                     {
	                	   Toast.makeText(Updates.this,"please refresh if download does not start",5000).show();
		                   // finish();
		             	   startActivity(new Intent(Intent.ACTION_VIEW, 
				    			    Uri.parse(url)));
		             	  front();
		                } 
                else if (url.endsWith(".ppt"))
                		{
             	   Toast.makeText(Updates.this,"please refresh if download does not start",5000).show();
             	   // finish();
             	   startActivity(new Intent(Intent.ACTION_VIEW, 
		    			    Uri.parse(url)));
             	  front();
                		}
                else if (url.endsWith(".xls"))
                		{
             	   		Toast.makeText(Updates.this,"please refresh if download does not start",5000).show();
		                   // finish();
		             	   startActivity(new Intent(Intent.ACTION_VIEW, 
				    			    Uri.parse(url)));
		             	  front();
                		} 
		           else if (url.endsWith(".txt"))
		                {
		        	   		Toast.makeText(Updates.this,"please refresh if download does not start",5000).show();
			              // finish();
			        	   startActivity(new Intent(Intent.ACTION_VIEW, 
				    			    Uri.parse(url)));
			        	   front();
			           }
	              else if (url.endsWith(".zip"))
		              {
			          	   Toast.makeText(Updates.this,"please refresh if download does not start",5000).show();
			                 // finish();
			           	   startActivity(new Intent(Intent.ACTION_VIEW, 
					    			    Uri.parse(url)));
			           	front();
			          } 
		         else if (url.endsWith(".rar"))
			         {
			        	 Toast.makeText(Updates.this,"please refresh if download does not start",5000).show();
				         // finish();
				   	   startActivity(new Intent(Intent.ACTION_VIEW, 
				  			    Uri.parse(url)));
				   	front();
			         }
             
	            else super.onLoadResource(view,url);
                
            }           
        }
        );

		 
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
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		//Handle the back button
		if (keyCode == KeyEvent.KEYCODE_BACK) {
		//Ask the user if they want to quit
		new AlertDialog.Builder(this)
		.setIcon(android.R.drawable.ic_dialog_alert)
		.setTitle("Exit")
		.setMessage("Are you sure you want to exit?")
		.setNegativeButton(android.R.string.cancel, null)
		.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
		@Override
		public void onClick(DialogInterface dialog, int which){
		// Exit the activity
			Updates.this.finish();
		}
		})
		.show();

		// Say that we've consumed the event
		return true;
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
	 public void front()
	 {
	 	webview.loadUrl("http://dreamadmission.jelastic.servint.net/advertise.html");
	 }
}
