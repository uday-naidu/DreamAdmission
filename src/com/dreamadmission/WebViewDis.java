package com.dreamadmission;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

public class WebViewDis extends Activity {
	ProgressBar loadingProgressBar,loadingTitle;
	WebView webview;
	String collgurl;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.updates1);
	    webview = (WebView) findViewById(R.id.webviewid);
	    Intent intent = getIntent();
		collgurl = intent.getExtras().getString("collgurl");
		webview.getSettings().setJavaScriptEnabled(true);
		webview.loadUrl(collgurl);
	//	webview.setWebViewClient(new MyWebViewClient());
		/*webview.setWebViewClient(new WebViewClient() {  
			  @Override
			  public void onPageStarted(WebView view, String url, Bitmap favicon){  
			    if (url.endsWith(".pdf")) { //NON-NLS  
			    Toast.makeText(MainActivity.this,"download",5000).show();
			    startActivity(new Intent(Intent.ACTION_VIEW, 
		    			    Uri.parse(url)));
			    
			    Log.v("", "onPageStarted url: " + url);
			      view.stopLoading();  
			      // DO SOMETHING  
			    }  
			  }  
		});*/
		/*webview.setDownloadListener(new DownloadListener() {
		        @Override
		        public void onDownloadStart(String url, String userAgent,
		                String contentDisposition, String mimetype,
		                long contentLength) {
		            // handle download, here we use brower to download, also you can try other approach.
		           /* Uri uri = Uri.parse(url);
		            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		            startActivity(intent);
		            startActivity(new Intent(Intent.ACTION_VIEW, 
		    			    Uri.parse(url)));
		        }
		    });*/
		  webview.setWebViewClient(new WebViewClient()
           {
               public void onLoadResource (WebView view, String url) 
               {
            	   int i = url.lastIndexOf('?');
            	   if(i!=-1)
            	       url = url.substring(0,i);
                  // Main.log(url);
                   if (url.endsWith(".pdf"))
                   {//Toast.makeText(WebViewDis.this,"please refresh if download does not start",5000).show();
                      // finish();
                	   startActivity(new Intent(Intent.ACTION_VIEW, 
   		    			    Uri.parse(url)));
                	   front();
                   }
                   else if (url.endsWith(".doc"))
                        {
	                	   //Toast.makeText(WebViewDis.this,"please refresh if download does not start",5000).show();
		                   // finish();
		             	   startActivity(new Intent(Intent.ACTION_VIEW, 
				    			    Uri.parse(url)));
		             	   front();
		                } 
                   else if (url.endsWith(".ppt"))
                   		{
                	   //Toast.makeText(WebViewDis.this,"please refresh if download does not start",5000).show();
                	   // finish();
                	   startActivity(new Intent(Intent.ACTION_VIEW, 
		    			    Uri.parse(url)));
                	   front();
                   		}
                   else if (url.endsWith(".xls"))
                   		{
                	   		//Toast.makeText(WebViewDis.this,"please refresh if download does not start",5000).show();
		                   // finish();
		             	   startActivity(new Intent(Intent.ACTION_VIEW, 
				    			    Uri.parse(url)));
		             	   front();
                   		} 
		           else if (url.endsWith(".txt"))
		                {
		        	   		//Toast.makeText(WebViewDis.this,"please refresh if download does not start",5000).show();
			              // finish();
			        	   startActivity(new Intent(Intent.ACTION_VIEW, 
				    			    Uri.parse(url)));
			        	   front();
			           }
	              else if (url.endsWith(".zip"))
		              {
			          	   //Toast.makeText(WebViewDis.this,"please refresh if download does not start",5000).show();
			                 // finish();
			           	   startActivity(new Intent(Intent.ACTION_VIEW, 
					    			    Uri.parse(url)));
			           	   front();
			          } 
		         else if (url.endsWith(".rar"))
			         {
			        	 //Toast.makeText(WebViewDis.this,"please refresh if download does not start",5000).show();
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
		
	}
	public void open(View v)
	{
		startActivity(new Intent(Intent.ACTION_VIEW, 
			    Uri.parse(collgurl)));
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
}}
public void download(View v)
{ /*String webUrl = webview.getUrl();
  webview.loadUrl(webUrl);*/
  front();
}
public void front()
{
	webview.loadUrl(collgurl);
}
}

