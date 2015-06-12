package com.dreamadmission;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TabHost;
 
public class Tabs extends TabActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newtab);
 
        Resources res = getResources(); // Resource object to get Drawables
        TabHost tabHost = getTabHost();  // The activity TabHost
        TabHost.TabSpec spec;  // Resusable TabSpec for each tab
        Intent intent;  // Reusable Intent for each tab
 
        // Creating an Intent to launch an Activity for the tab (to be reused)
        intent = new Intent().setClass(this, ListColleges.class);
 
        // Initializing a TabSpec for each tab and adding it to the TabHost
        spec = tabHost.newTabSpec("Colleges").setIndicator("Colleges",
                          res.getDrawable(R.drawable.college_tab))
                      .setContent(intent);
        tabHost.addTab(spec);
 
        // same for the other tabs
        intent = new Intent().setClass(this, ARC.class);
        spec = tabHost.newTabSpec("ARC").setIndicator("ARC",
                          res.getDrawable(R.drawable.arc_tab))
                      .setContent(intent);
        tabHost.addTab(spec);
 
        intent = new Intent().setClass(this, Updates.class);
        spec = tabHost.newTabSpec("Updates").setIndicator("Updates",
                          res.getDrawable(R.drawable.college_update_tab))
                      .setContent(intent);
        tabHost.addTab(spec);
 
        tabHost.setCurrentTab(2);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.layout.menu, menu);
        return true;
    }
   @Override
   public boolean onOptionsItemSelected(MenuItem item)
   {

       switch (item.getItemId())
       {
       case R.id.credits:
           // Single menu item is selected do something
           // Ex: launching new activity/screen or show alert message
    	   Intent intent = new Intent(getApplicationContext(),Credits.class);
           startActivity(intent);
           return true;

      /* case R.id.search:
    	   Toast.makeText(getApplicationContext(), "Search Selected", Toast.LENGTH_SHORT).show();
           return true;

       case R.id.settings:
           Toast.makeText(getApplicationContext(), "Settings Selected", Toast.LENGTH_SHORT).show();
           return true;

       case R.id.help:
           Toast.makeText(getApplicationContext(), "Help Selected", Toast.LENGTH_SHORT).show();
           return true;*/


       default:
           return super.onOptionsItemSelected(item);
       }
   }    
}
