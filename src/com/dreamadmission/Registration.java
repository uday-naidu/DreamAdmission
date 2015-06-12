package com.dreamadmission;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
public class Registration extends Activity {
	private static final String TAG = null;
	public Dialog dialog;
	EditText name,phone,email,cet;
	TextView b;
	String n,p,m,c;
	Button reg;
	String response="";
	 String uname;
	 Boolean Internet=false;
	private ProgressDialog progressMessage;
	RegPost jParser = new RegPost();
	private static String url = "http://dreamadmission.jelastic.servint.net/user";
	JSONArray arrayObj = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.form);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.windowtitle);
		b=(TextView)findViewById(R.id.textView1);
		ScrollView sc=(ScrollView)findViewById(R.id.scrollview1);
		int n=1;
		if(n<0)//registered
		{   b.setVisibility(View.GONE);
		    sc.setVisibility(View.GONE);
			//calltab activity
			
		}
		else
		{ // a.setVisibility(View.GONE);
		
		Animation anim2 =AnimationUtils.loadAnimation(Registration.this,R.anim.alpha);
		
		AlphaAnimation anim1=new AlphaAnimation(100,0);
		anim1.setDuration(1500);
		anim1.setFillAfter(true);
	
			b.startAnimation(anim2);
			sc.startAnimation(anim2);
		}
		}	
	
	
	
		public void reg(View view){
			final CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox1);
			Button b=(Button)findViewById(R.id.register);
	         if (checkBox.isChecked()){
	        	 b.setVisibility(View.VISIBLE);	 
	         } 
	         else
	        	 b.setVisibility(View.GONE);	
	         
	         	name=(EditText)findViewById(R.id.etname);
		 		phone=(EditText)findViewById(R.id.etphone);
		 		//email=(EditText)findViewById(R.id.etemail);
		 		//cet=(EditText)findViewById(R.id.etcet);		
		 		reg= (Button)findViewById(R.id.register);
		 		n = name.getText().toString();
 		        c = "200";
 		        p = phone.getText().toString();
 		        m = "email";
 		        uname = ""+n+"$"+c+"$"+p+"$"+m+"$";
 		       if(checkInternetConnection())  	  
 		       {   
 		        if(n.length()==0)
	 		    {
			 		    Toast.makeText(getApplicationContext(), "Please fill the name", Toast.LENGTH_LONG).show();
			 		   
	 		    }
	 		   /* else if(m.length()==0)
	 		     {
	 		        	Toast.makeText(getApplicationContext(), "Please fill the email", Toast.LENGTH_LONG).show();
	 		        	b.setVisibility(View.GONE);
	 		     }*/
	 		    else if(p.length()==0)
	 		     {
	 		        	Toast.makeText(getApplicationContext(), "Please fill the phone number", Toast.LENGTH_LONG).show();
	 		        	/*if(checkBox.isChecked())
			 		    	b.setVisibility(View.GONE);*/

	 		     }
	 		    /*else if(c.length()==0)
	 		     {
	 		        	Toast.makeText(getApplicationContext(), "Please fill the cet score", Toast.LENGTH_LONG).show();
	 		        	b.setVisibility(View.GONE);
	 		     }*/
	 		   else if(p.length()<10 || p.length()>10)
	 		     {
	 		    	Toast.makeText(getApplicationContext(), "Please enter a valid phone number", Toast.LENGTH_LONG).show();
	 		    	/*if(checkBox.isChecked())
		 		    	b.setVisibility(View.GONE);*/

	 		     }
	 		    /*else if(Integer.parseInt(c)>=200)
	 		     {
	 		    	Toast.makeText(getApplicationContext(), "Please enter a valid CET score", Toast.LENGTH_LONG).show();
	 		    	b.setVisibility(View.GONE);
	 		     }*/
	 		    
 		       }
 		       else
 		       {
	 		    	 Toast.makeText(getApplicationContext(), "Please Check Your Internet Connection", Toast.LENGTH_LONG).show();
	 		    	 Intent intent = new Intent(getApplicationContext(), Registration.class);
	 		    	 startActivity(intent);
 		       }
		 		reg.setOnClickListener(new View.OnClickListener() {
		 			
		 			@Override
		 			public void onClick(View v) {
		 				// TODO Auto-generated method stub
		 				
		 		      if(checkInternetConnection())
		 		      {
		 		    	 Toast.makeText(getApplicationContext(), "This may take few minutes.Please Wait...",Toast.LENGTH_LONG ).show();
		 		    	 new LoadAllColleges().execute();
		 		      }
		 		       else
		 		    	  {
		 		    	  Toast.makeText(getApplicationContext(), "Please Check Your Internet Connection", Toast.LENGTH_LONG).show();
		 		    	  Intent intent = new Intent(getApplicationContext(), Registration.class);
		 		    	  startActivity(intent);
		 		    	  }

		 		    
		 			}});
		}
		
			
    public void show(View v){
    	 dialog = new Dialog(this);
		dialog.setContentView(R.layout.custom);
		dialog.setTitle("Terms and Conditions");
		Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
		dialogButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});		
		dialog.show();
    }

    
    
    public boolean checkInternetConnection() {

    	ConnectivityManager conMgr = (ConnectivityManager) getSystemService (Context.CONNECTIVITY_SERVICE);

    	// ARE WE CONNECTED TO THE NET

    	if (conMgr.getActiveNetworkInfo() != null

    	&& conMgr.getActiveNetworkInfo().isAvailable()

    	&& conMgr.getActiveNetworkInfo().isConnected()) {

    	return true;

    	} else {

    	Log.v(TAG, "Internet Connection Not Present");

    	return false;

    	}

    	}
       
    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
    
    
    
    
	class LoadAllColleges extends AsyncTask<String, String, String> {
			
			 
		ArrayAdapter<String> adapter;
		String collg[];
		Boolean flag=false;
		
		@Override
		protected void onPreExecute() {
		super.onPreExecute();
		/*ProgressDialog progressMessage = new ProgressDialog(Registration.this);
		progressMessage.setMessage("Please Wait...");
		progressMessage.setIndeterminate(false);
		progressMessage.setCancelable(false);
		progressMessage.show();*/
		}

		protected String doInBackground(String... args) {
			
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("uname", uname));
				jParser.makeHttpRequest(url, params);
				//System.out.println("i got json object"+json);
				//Log.d("Colleges: ", json.toString());
				//HashMap<String, String> map = new HashMap<String, String>();
				/*try {
					 //System.out.println("i am in try ");
					 //System.out.println( json.length());
				for(int i=0;i<json.length();i++){
				 response = json.getString(""+i);
				}
							
				} catch (JSONException e) {
				e.printStackTrace();
				}*/
				//progressMessage.dismiss();
				flag=true;
				return null;
			
		}
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
		    runOnUiThread(new Runnable() {
			  public void run() {
				if(flag)
				{
					Toast.makeText(getApplicationContext(), "Thank You For Registration", Toast.LENGTH_LONG).show();
					SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
					SharedPreferences.Editor editor = prefs.edit();
	 		        editor.putBoolean("registration", true);
	 		        editor.commit();
					Intent intent = new Intent(getApplicationContext(),CustomTabActivity.class);
					startActivity(intent);
					Registration.this.finish();
				}
				else
				{
					Toast.makeText(getApplicationContext(), "Registration Failed!", Toast.LENGTH_LONG).show();
					Intent intent = new Intent(getApplicationContext(),Registration.class);
					startActivity(intent);
				}
			
			  }
			  });  	
			
		}
	}
    
    
       
}
