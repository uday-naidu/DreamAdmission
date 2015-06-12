package com.dreamadmission;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

public class CheckRegistration extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.checkreg);
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        boolean regComplete = prefs.getBoolean("registration", false);
        if(regComplete) {
			//Toast.makeText(getApplicationContext(),"You have registered", Toast.LENGTH_LONG).show();
			startActivity(new Intent(getApplicationContext(), CustomTabActivity.class));
			CheckRegistration.this.finish();
        } else {
           startActivity(new Intent(getApplicationContext(), Registration.class));
			/*SharedPreferences.Editor editor = prefs.edit();
	        editor.putBoolean("registration", true);
	        editor.commit();*/
			CheckRegistration.this.finish();
    }
    finish();
}
	

}
