package com.dreamadmission;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Toast;

public class Logo extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_logo);
		Thread sp= null;
		Thread logotimer = new Thread(){
            public void run(){
                try{
                	sleep(1500);
                }
                catch(InterruptedException e){
                    e.printStackTrace();
                }
                finally{
                    finish();
                }
            }
        };
      
        logotimer.start();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        boolean regComplete = prefs.getBoolean("registration", false);
        if(regComplete) {
			Toast.makeText(getApplicationContext(),"You have registered", Toast.LENGTH_LONG).show();
			startActivity(new Intent(getApplicationContext(), Tabs.class));
        } else {
           startActivity(new Intent(getApplicationContext(), RegForm.class));
           SharedPreferences.Editor editor = prefs.edit();
           editor.putBoolean("registration", true);
           editor.commit();
    }
         
	}
}
	
