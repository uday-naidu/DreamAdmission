package com.dreamadmission;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

import java.util.ArrayList;
import java.util.Calendar;

import com.dreamadmission.LocationHelper.LocationResult;





import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Toast;

public class TestexpandableActivity extends Activity {
	public static String[] latitude;
	public static String[] longitude;
	public static String[] colleges;
	public static String[] phoneno;
	
	Button btnShowLocation;
	
	private LocationControl locationControlTask;
    private boolean hasLocation=false;
    LocationHelper locHelper;
    protected Location currentLocation;
	

	private ExpandableListView expandableList = null;
	
	   

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.expand_main);
		locHelper = new LocationHelper();
        locHelper.getLocation(TestexpandableActivity.this, locationResult);
        locationControlTask = new LocationControl();
		
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		boolean sharedone = prefs.getBoolean("share", false);
        if(!sharedone) {
        	share();
        	SharedPreferences.Editor editor = prefs.edit();
	        editor.putBoolean("share", true);
	        editor.commit();
        }
		longitude = getResources().getStringArray(R.array.longitude_array);
    	latitude = getResources().getStringArray(R.array.latitude_array);
		//String[] colleges={"qwe","asd","tyu"};
		colleges=getResources().getStringArray(R.array.colleges_array);
		phoneno=getResources().getStringArray(R.array.College_phoneno);
		expandableList = (ExpandableListView) findViewById(R.id.expandableView);
		String[] college_address=getResources().getStringArray(R.array.College_address);
		ArrayList<Groupe> groupes = new ArrayList<Groupe>();

		for (int i = 0; i < colleges.length; i++) {
			Groupe groupe = new Groupe(colleges[i]);

			ArrayList<Objet> donnees = new ArrayList<Objet>();

			//for (int x = 0; x < 3; x++) {
				donnees.add(new Objet(groupe,college_address[i]));
				//donnees.add(new Objet(groupe, "phone.no:"));
				//donnees.add(new Objet(groupe, "phone2"));
			//}

			groupe.setObjets(donnees);

			groupes.add(groupe);
		}

		ELVAdapter adapter = new ELVAdapter(this, groupes);

		expandableList.setAdapter(adapter);
		
		
		/*btnShowLocation = (Button) findViewById(R.id.nearby);
		 // show location button click event
        btnShowLocation.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {		
				// create class object
		        gps = new GPSTracker(TestexpandableActivity.this);

				// check if GPS enabled		
		        if(gps.canGetLocation()){
		        	
		        	 lat = gps.getLatitude();
		        	 lng = gps.getLongitude();
		        	
		        	// \n is for new line
		        
		        	double mindist=100000.0,l=0.0,g=0.0;
		  		  int i,m=0;
		  		  for(i=0;i<colleges.length;i++)
		  		  {   Double c_lat=Double.parseDouble(latitude[i]);
		  		      Double c_lng=Double.parseDouble(longitude[i]);
		  		      
		  			  double min=sqrt(pow((c_lat-lat),2)+pow((c_lng-lng),2));
		  		       if (min<mindist)
		  		       {
		  		    	   l=c_lat;
		  		    	   g=c_lng;
		  		    	   mindist=min;
		  		    	   m=i;
		  		    	   
		  		       }}
		  		Toast.makeText(getApplicationContext(),"Nearest ARC is "+ colleges[m], Toast.LENGTH_LONG).show();	
		        
		  		  expandableList.smoothScrollToPosition(m);
		  		  
		  		  
		        }else{
		        	// can't get location
		        	// GPS or Network is not enabled
		        	// Ask user to enable GPS/network in settings
		        	gps.showSettingsAlert();
		        }
				
			}
		});*/
		
	}
	private class LocationControl extends AsyncTask<Context, Void, Void>
    {
        private final ProgressDialog dialog = new ProgressDialog(TestexpandableActivity.this);


        protected void onPreExecute()
        
        {    lockScreenOrientation();
            this.dialog.setMessage("Tap to cancel");
            this.dialog.setTitle("Getting current Location");
            this.dialog.setCancelable(true);  
            this.dialog.setButton(Dialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {             
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(which == Dialog.BUTTON_NEGATIVE) {
                        dialog.dismiss();
                        Toast.makeText(TestexpandableActivity.this, "canceled", Toast.LENGTH_SHORT).show();
                        unlockScreenOrientation();
                        locationControlTask.cancel(true);


  


                    }
                }
            });
            this.dialog.show();

        }

        protected Void doInBackground(Context... params)
        {
            //Wait 40 seconds to see if we can get a location from either network or GPS, otherwise stop
            Long t = Calendar.getInstance().getTimeInMillis();
            while (!hasLocation && Calendar.getInstance().getTimeInMillis() - t < 60000) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (locationControlTask.isCancelled()) break;
            };
            return null;
        }

        protected void onPostExecute(final Void unused)
        {
            if(this.dialog.isShowing())
            {
                this.dialog.dismiss();
                
            }

            if (currentLocation != null)
            {
               int m= useLocation(currentLocation.getLatitude(),currentLocation.getLongitude());
                //String text = "lat:"+currentLocation.getLatitude()+" long:"+currentLocation.getLongitude();
                Toast.makeText(getApplicationContext(),"Nearest ARC is "+ colleges[m], 3000).show();	
                
                
        		//expandableList.smoothScrollToPosition(m);
                expandableList.setSelection(m);
        		
                //myText.setText(text);
            }
            else
            {
                Toast.makeText(TestexpandableActivity.this, "location could not be found,make sure if internet is working", Toast.LENGTH_SHORT).show();

                //Couldn't find location, do something like show an alert dialog
            }
            unlockScreenOrientation();
        }
    }
    public LocationResult locationResult = new LocationResult()
    {  @Override
    	public void gotLocation(final Location location)
        {
            currentLocation = new Location(location);
            hasLocation = true;
        }
    };
    
    public int useLocation(double la,double ln )
    {
    	double mindist=100000.0,l=0.0,g=0.0;
		  int i,m=0;
		  for(i=0;i<colleges.length;i++)
		  {   Double c_lat=Double.parseDouble(latitude[i]);
		      Double c_lng=Double.parseDouble(longitude[i]);
		      
			  double min=sqrt(pow((c_lat-la),2)+pow((c_lng-ln),2));
		       if (min<mindist)
		       {
		    	   l=c_lat;
		    	   g=c_lng;
		    	   mindist=min;
		    	   m=i;
		    	   
		       }}
		  
		//Toast.makeText(getApplicationContext(),"Nearest ARC is "+ colleges[m], Toast.LENGTH_LONG).show();	
      
		//expandableList.smoothScrollToPosition(m);
    	
    	
    	
    	
    	
    return m;	
    }
    @Override
    protected void onStop() {
        locHelper.stopLocationUpdates();
        locationControlTask.cancel(true);
        super.onStop();
    } 
    private void lockScreenOrientation() {
        int currentOrientation = getResources().getConfiguration().orientation;
        if (currentOrientation == Configuration.ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
    }
    private void unlockScreenOrientation() {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
    }
    public void gpsopen(View v)
    {   
    	LocationManager locationManager3 = (LocationManager) getSystemService(LOCATION_SERVICE);

    	if (locationManager3.isProviderEnabled(LocationManager.GPS_PROVIDER)&&locationManager3.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
    		{ locHelper = new LocationHelper();
            locHelper.getLocation(TestexpandableActivity.this, locationResult);
            locationControlTask = new LocationControl();
    			
    			//Toast.makeText(this, "GPS is Enabled in your devide", Toast.LENGTH_SHORT).show();
    		new LocationControl().execute(this);}
    	}else{
    		buildAlertMessageNoGps();
    	}
    	}
    
private void buildAlertMessageNoGps() {
    final AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setMessage("GPS & LocationService is Required, do you want to enable it?")
           .setCancelable(false)
           .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
               public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                   startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
               }
           })
           .setNegativeButton("No", new DialogInterface.OnClickListener() {
               public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                    dialog.cancel();
               }
           });
    final AlertDialog alert = builder.create();
    alert.show();
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
	TestexpandableActivity.this.finish();
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
	public void share(){
		   Intent sharingIntent = new Intent(Intent.ACTION_SEND);
		   sharingIntent.setType("text/plain");
		   sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT,"Hi friends, Check out following android app for updates of the ongoing engineering admission process https://play.google.com/store/apps/details?id=com.dreamadmission");
		   sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Download Dream Admission");
		   startActivity(Intent.createChooser(sharingIntent, "Share using"));
	   }
}