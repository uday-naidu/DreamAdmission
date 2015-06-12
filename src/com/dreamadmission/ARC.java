package com.dreamadmission;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dreamadmission.LocationHelper.LocationResult;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.Toast;

public class ARC extends Activity {
	
	Button btnShowLocation;
	String[] colleges;
	String[] longitude;
	String[] latitude;
	String[] college_address;
	double lng;
	double lat;
	ListView lstView;
	private final String PARENT = "package";
    private final String CHILD = "permission";
    private LocationControl locationControlTask;
    private boolean hasLocation = false;
    LocationHelper locHelper;
    protected Location currentLocation;
    public ExpandableListView listView;
	
	// GPSTracker class
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.arc);
        locHelper = new LocationHelper();
        locHelper.getLocation(ARC.this, locationResult);
        locationControlTask = new LocationControl();
        /*lstView = (ListView)findViewById(R.id.listView1);
    	lstView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
    	lstView.setTextFilterEnabled(true);*/
    	colleges =getResources().getStringArray(R.array.colleges_array);
    	longitude=getResources().getStringArray(R.array.longitude_array);
    	latitude=getResources().getStringArray(R.array.latitude_array);
    	college_address=getResources().getStringArray(R.array.College_address);
    	//ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.simplerow, colleges);


    			// Assign adapter to ListView
    	//lstView.setAdapter(adapter); 
    	//setListAdapter(new ArrayAdapter<String>(this,
    	//android.R.layout.simple_list_item_checked, colleges));
    	
    	 listView = (ExpandableListView) findViewById(R.id.list);
        List<Map<String, Object>> parentsList = new ArrayList<Map<String, Object>>();
        List<List<Map<String, Object>>> childrenList = new ArrayList<List<Map<String,Object>>>();
         
        PackageManager pm = getPackageManager();
        List<PackageInfo> packages = pm.getInstalledPackages(PackageManager.GET_PERMISSIONS);
        for(int i=0;i<colleges.length;i++){
            //String[] permissions = info.requestedPermissions;
            if(colleges != null){
                Map<String, Object> parentData = new HashMap<String, Object>();
                parentData.put(PARENT, colleges[i]);
                parentsList.add(parentData);
                 
                List<Map<String, Object>> childList = new ArrayList<Map<String,Object>>();
                for(int j=i;j<=i;j++){
                    Map<String, Object> childData = new HashMap<String, Object>();
                    childData.put(CHILD, college_address[i]);
                    childList.add(childData);
                }
                childrenList.add(childList);
            }
        }
        
        
       /* btnShowLocation = (Button) findViewById(R.id.btnShowLocation);
        
        // show location button click event
        btnShowLocation.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {		
				// create class object
		        gps = new GPSTracker(ARC.this);

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
		        
		  		  listView.smoothScrollToPosition(m);
		  		  
		  		  
		        }else{
		        	// can't get location
		        	// GPS or Network is not enabled
		        	// Ask user to enable GPS/network in settings
		        	gps.showSettingsAlert();
		        }
				
			}
		});*/
        SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(
                ARC.this,
                parentsList,
                R.layout.expandablelistview_group,
                new String[]{PARENT},
                new int[]{R.id.TextViewGroup},
                childrenList,
                R.layout.raw,
                new String[]{CHILD},
                new int[]{R.id.permission}
            );
         
        listView.setAdapter(adapter);
    
    }
    private class LocationControl extends AsyncTask<Context, Void, Void>
    {
        private final ProgressDialog dialog = new ProgressDialog(ARC.this);


        protected void onPreExecute()
        
        {    lockScreenOrientation();
            this.dialog.setMessage("Tap to cancel");
            this.dialog.setTitle("Searching");
            this.dialog.setCancelable(true);  
            this.dialog.setButton(Dialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {             
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(which == Dialog.BUTTON_NEGATIVE) {
                        dialog.dismiss();
                        Toast.makeText(ARC.this, "dialog canceled", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            this.dialog.show();

        }

        protected Void doInBackground(Context... params)
        {
            //Wait 40 seconds to see if we can get a location from either network or GPS, otherwise stop
            Long t = Calendar.getInstance().getTimeInMillis();
            while (!hasLocation && Calendar.getInstance().getTimeInMillis() - t < 40000) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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
                useLocation(currentLocation.getLatitude(),currentLocation.getLongitude());
                //String text = "lat:"+currentLocation.getLatitude()+" long:"+currentLocation.getLongitude();
                //myText.setText(text);
            }
            else
            {
                Toast.makeText(ARC.this, "location could not be found", Toast.LENGTH_SHORT).show();

                //Couldn't find location, do something like show an alert dialog
            }
            unlockScreenOrientation();
        }
    }
    public LocationResult locationResult = new LocationResult()
    {   @Override
        public void gotLocation(final Location location)
        {
            currentLocation = new Location(location);
            hasLocation = true;
        }
    };
    
    public void useLocation(double lat,double lng )
    {
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
      
		  listView.smoothScrollToPosition(m);
    	
    	
    	
    	
    	
    	
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
    	LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

    	if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)||locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
    	//Toast.makeText(this, "GPS is Enabled in your devide", Toast.LENGTH_SHORT).show();
    		new LocationControl().execute(this);
    	}else{
    		buildAlertMessageNoGps();
    	}
    	}
    
private void buildAlertMessageNoGps() {
    final AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setMessage(" GPS & LOCATIONSERVICE is Required, do you want to enable it?")
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
    
    
}
