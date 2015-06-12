package com.dreamadmission;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

public class Social extends Activity {

	Button arc,college;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.social);
		arc=(Button)findViewById(R.id.arcfanpage);
		college=(Button)findViewById(R.id.collegefanpage);
		
	}
	public void arcfanpage(View v)
	{
		String fburl = "http://www.facebook.com/pages/ARC-Center/361758017280530";
		Intent intent = new Intent(getApplicationContext(),CollegeWebView.class);
		intent.putExtra("collgurl", fburl);
		startActivity(intent);
		
	}
	public void collgfanpage(View v)
	{
		Intent intent = new Intent(getApplicationContext(),CollgFbFanPage.class);
		startActivity(intent);
		
	}
	public void feedback(View v)
	{
		String fdurl = "http://www.facebook.com/pages/Feedback/126438464231072";
		Intent intent = new Intent(getApplicationContext(),CollegeWebView.class);
		intent.putExtra("collgurl", fdurl);
		startActivity(intent);
		
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	//Handle the back button
	if (keyCode == KeyEvent.KEYCODE_BACK) {
	//Ask the user if they want to quit
	/*new AlertDialog.Builder(this)
	.setIcon(android.R.drawable.ic_dialog_alert)
	.setTitle("Exit")
	.setMessage("Are you sure you want to exit?")
	.setNegativeButton(android.R.string.cancel, null)
	.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
	@Override
	public void onClick(DialogInterface dialog, int which){
	// Exit the activity
	Social.this.finish();
	Intent intent = new Intent(getApplicationContext(), Updates.class);
	startActivity(intent);
	}
	})
	.show();*/
		Social.this.finish();
		Intent intent = new Intent(getApplicationContext(), Updates.class);
		startActivity(intent);

	// Say that we've consumed the event
	return true;
	}

	return super.onKeyDown(keyCode, event);
	} 
	
}
