package com.dreamadmission;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.Window;

public class About extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.about);
	}
	@Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.layout.menu, menu);
        return true;
    }
 /*  @Override
   public boolean onOptionsItemSelected(MenuItem item)
   {

       switch (item.getItemId())
       {
       case R.id.about:
           // Single menu item is selected do something
           // Ex: launching new activity/screen or show alert message
           Intent intent = new Intent(getApplicationContext(),About.class);
           startActivity(intent);
           return true;

       case R.id.exit:
           System.exit(0);
           return true;

       case R.id.settings:
           Toast.makeText(getApplicationContext(), "Search is Selected", Toast.LENGTH_SHORT).show();
           return true;

       case R.id.help:
           Toast.makeText(getApplicationContext(), "Share is Selected", Toast.LENGTH_SHORT).show();
           return true;


       default:
           return super.onOptionsItemSelected(item);
       }
   }    */

}
