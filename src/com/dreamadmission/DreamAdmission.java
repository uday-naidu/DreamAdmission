package com.dreamadmission;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class DreamAdmission extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dreamadmission);
		Button a = (Button)findViewById(R.id.buttonbounce);
		Animation anim = AnimationUtils.loadAnimation(this, R.anim.bounce);
		a.startAnimation(anim);
	
		 anim.setAnimationListener(new AnimationListener() {
             public void onAnimationStart(Animation animation) {}
             public void onAnimationRepeat(Animation animation) {}
             public void onAnimationEnd(Animation animation) {
            	 TextView t = (TextView)findViewById(R.id.accepted);
            	 t.setVisibility(View.VISIBLE);
          	   
             }
         });
		 Thread t = new Thread(){
		
	     public void run(){
	    	 try {
				sleep(3000);
				 Intent intent = new Intent(getApplicationContext(),CheckRegistration.class);
				 startActivity(intent);
				 finish();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
	     }
		 };
		t.start();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
