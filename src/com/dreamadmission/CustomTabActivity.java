package com.dreamadmission;

			//import android.app.Activity;
/*PROMO*/	import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;


 public class CustomTabActivity extends TabActivity {
	private static final String TAG = null;
	private TabHost mTabHost;
	//private Context _context;	
	private void setupTabHost() {
		mTabHost = (TabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup();
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		// construct the tabhost
		setContentView(R.layout.main);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.windowtitle);
		getWindow().getDecorView().setBackgroundColor(Color.WHITE);
		setupTabHost();
		//mTabHost.getTabWidget().setDividerDrawable(R.drawable.tab_divider);
		setupTab(new TextView(this), "DTE Updates", "Dte.class");
		setupTab(new TextView(this), "College Websites", "CollegeWebsites.class");
	//	setupTab(new TextView(this), "Admission Websites", "ListColleges.class");		
		setupTab(new TextView(this), "ARC Centers", "TestexpandableActivity.class");	
		setupTab(new TextView(this), "Facebook Pages", "Social.class");
		setupTab(new TextView(this), "Sponsors", "Updates.class");
	}


	private void setupTab(final View view, final String tag, final String className) {
				View tabview = createTabView(mTabHost.getContext(), tag);
				TabHost tabHost = getTabHost();    
				Resources res = getResources();
				TabHost.TabSpec spec;   
				Intent intent;
				intent = new Intent().setClass(this, CollegeWebsites.class);
				if (className.equals("Updates.class")) {
					intent = new Intent().setClass(this, Updates.class);}
				/*if (className.equals("ListColleges.class")) {
					intent = new Intent().setClass(this, ListColleges.class);}*/
				if (className.equals("TestexpandableActivity.class")) {
					intent = new Intent().setClass(this, TestexpandableActivity.class);}
				if (className.equals("CollegeWebsites.class")) {
					intent = new Intent().setClass(this, CollegeWebsites.class);}
				if (className.equals("Social.class")) {
					intent = new Intent().setClass(this, Social.class);}
				if (className.equals("Dte.class")) {
					intent = new Intent().setClass(this, Dte.class);}
				TabSpec setContent = mTabHost.newTabSpec(tag).setIndicator(tabview).setContent(intent);
				mTabHost.addTab(setContent);
				/*spec = tabHost.newTabSpec(tag).setIndicator("Music",res.getDrawable(R.drawable.icontest)).setContent(intent);
				tabHost.addTab(spec);*/
	}

	private  View createTabView(final Context context, final String text) {
		View view = LayoutInflater.from(context).inflate(R.layout.tabs_bg, null);
		ImageView iv = (ImageView)view.findViewById(R.id.icon);
		TextView tv = (TextView) view.findViewById(R.id.tabsText);
			if (text.equals("Sponsors")) 
				iv.setImageResource(R.drawable.sponsors_icon);
			if (text.equals("College Websites")) 
				iv.setImageResource(R.drawable.college_icon);
			if (text.equals("ARC Centers")) 
				iv.setImageResource(R.drawable.arc_icon);
			/*if (text.equals("Admission Websites")) 
				iv.setImageResource(R.drawable.adm_web_icon);*/
			if (text.equals("Facebook Pages")) 
				iv.setImageResource(R.drawable.social_icon);
			if (text.equals("DTE Updates")) 
				iv.setImageResource(R.drawable.dte_icon);
		tv.setText(text);

		return view;
	}
	
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.layout.menu, menu);
        return true;
    }
	
	@Override
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
	CustomTabActivity.this.finish();
	Intent intent = new Intent(getApplicationContext(), Updates.class);
	startActivity(intent);
	}
	})
	.show();

	// Say that we've consumed the event
	return true;
	}

	return super.onKeyDown(keyCode, event);
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
    	   //Toast.makeText(getApplicationContext(), "About Selected", Toast.LENGTH_SHORT).show();
           return true;
       case R.id.share:
    	   Intent sharingIntent = new Intent(Intent.ACTION_SEND);
		   sharingIntent.setType("text/plain");
		   sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Hi friends, Check out following android app for updates of the ongoing engineering admission process https://play.google.com/store/apps/details?id=com.dreamadmission");
		   sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Download Dream Admission");
		   startActivity(Intent.createChooser(sharingIntent, "Share using"));
		   return true;

       default:
           return super.onOptionsItemSelected(item);
       }
   }
   
}