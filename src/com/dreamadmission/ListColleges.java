package com.dreamadmission;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
 

public class ListColleges extends Activity  {
private JSONObject json;
private static final String TAG = null;
ArrayList<HashMap<String, String>> collegeList;
private ProgressDialog progressMessage;
JSONParser jParser = new JSONParser();
private static String url = "http://dreamadmission.jelastic.servint.net/send";
JSONArray arrayObj = null;
int total=0;
String curl[],collgurl;
String collg[];




@Override
public void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.collegewebsitesnew);
collegeList = new ArrayList<HashMap<String, String>>();
if(checkInternetConnection())
new LoadAllColleges().execute();
	else
	{
		/*Toast.makeText(getApplicationContext(), "No Internet", Toast.LENGTH_SHORT).show();
		Intent intent1 = new Intent(getApplicationContext(),CustomTabActivity.class);
		startActivity(intent1);*/
		showAlertDialog();
	}



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

public void showAlertDialog() {
	AlertDialog.Builder builder = new AlertDialog.Builder(this);
	builder.setTitle("Error!!");
	builder.setMessage("Please Check Your Internet Connection...")
	       .setCancelable(false)
	       .setPositiveButton("OK", new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog, int id) {
	        	  // Intent intent1 = new Intent(getApplicationContext(),CustomTabActivity.class);
	        	   //startActivity(intent1);
	           }
	       });
	AlertDialog alert = builder.create();
	alert.show();
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
ListColleges.this.finish();
Intent intent = new Intent(getApplicationContext(), Updates.class);
startActivity(intent);
}
})
.show();*/
	ListColleges.this.finish();
	Intent intent = new Intent(getApplicationContext(), Updates.class);
	startActivity(intent);

// Say that we've consumed the event
return true;
}

return super.onKeyDown(keyCode, event);
}


class LoadAllColleges extends AsyncTask<String, String, String> {
	EditText inputSearch; 
	ArrayAdapter<String> adapter;

@Override
protected void onPreExecute() {
super.onPreExecute();
lockScreenOrientation();
progressMessage = new ProgressDialog(ListColleges.this);
progressMessage.setMessage("Loading ...");
progressMessage.setIndeterminate(false);
progressMessage.setCancelable(true);
progressMessage.setButton(Dialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {             
    @Override
    public void onClick(DialogInterface dialog, int which) {
        if(which == Dialog.BUTTON_NEGATIVE) {
            dialog.dismiss();
            Toast.makeText(ListColleges.this, "canceled", Toast.LENGTH_SHORT).show();
            unlockScreenOrientation();
            
            
             }
    }
});
progressMessage.show();
}

protected String doInBackground(String... args) {
List<NameValuePair> params = new ArrayList<NameValuePair>();
json = jParser.makeHttpRequest(url, params);
System.out.println("i got json object"+json);
//Log.d("Colleges: ", json.toString());
//HashMap<String, String> map = new HashMap<String, String>();
if(json!=null){
try {
	 System.out.println("i am in try ");
	 System.out.println( json.length());
	 collg = new String[json.length()];
	 curl = new String[json.length()];
for(int i=0;i<json.length();i++){
String name = json.getString(""+i);
String coll=null,url=null;
for(int j=0;j<name.length();j++){
	if(name.charAt(j)=='$'){
		coll=name.substring(0,j);
		url=name.substring(j+1,name.length());
		break;
	}
}
collg[i]=coll;
curl[i]=url;
}
} catch (JSONException e) {
e.printStackTrace();
}}
else{
	this.cancel(true);
	progressMessage.dismiss();
	unlockScreenOrientation();
}
for(int i=0;i<collg.length;i++)
	System.out.println(collg[i]);

return null;
}
protected void onPostExecute(String file_url) {
progressMessage.dismiss();

unlockScreenOrientation();
if(collg.length==0)
	{ visible();
	Toast.makeText(getApplicationContext(),"CONNECTION TIMEOUT please refresh ", Toast.LENGTH_LONG).show();
	
	}

else
runOnUiThread(new Runnable() {
public void run() {
	Button refresh =(Button)findViewById(R.id.buttonrefesh);
	  refresh.setVisibility(View.GONE);	
	  EditText tt=(EditText)findViewById(R.id.inputSearch_colleges);
	  tt.setVisibility(View.VISIBLE);
final ListView lv = (ListView)findViewById(R.id.list_view_colleges);
lv.setCacheColorHint(Color.WHITE);
inputSearch = (EditText) findViewById(R.id.inputSearch_colleges);
adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.simplerow,R.id.rowTextView,collg);
lv.setAdapter(adapter);
inputSearch.addTextChangedListener(new TextWatcher() {
    
    @Override
    public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
        // When user changed the Text
        LoadAllColleges.this.adapter.getFilter().filter(cs);   
    }
     
    @Override
    public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
            int arg3) {
        // TODO Auto-generated method stub
         
    }
     
    @Override
    public void afterTextChanged(Editable arg0) {
        // TODO Auto-generated method stub                          
    }
});
lv.setOnItemClickListener(new OnItemClickListener()
{
public void onItemClick(AdapterView<?> arg0, View v, int position, long id)
{
/*AlertDialog.Builder adb = new AlertDialog.Builder(
ListColleges.this);
adb.setTitle("ListView OnClick");
adb.setMessage("Selected Item is = "+ lv.getItemAtPosition(position));
adb.setPositiveButton("Ok", null);
adb.show();   */
	if(inputSearch.getText().toString().length()!=0){
		TextView text = (TextView) lv.getChildAt(position);
		String collgname = text.getText().toString();
		for(int i=0;i<collg.length;i++)
		{
			if(!collgname.equalsIgnoreCase(collg[i]))
				total++;
			else
				break;
	
		}
		
		//Toast.makeText(getApplicationContext(),"total is "+(total) , Toast.LENGTH_LONG).show();
		//Toast.makeText(getApplicationContext(),collgname , Toast.LENGTH_LONG).show();
		collgurl = curl[total];
		total=0;
		}
		else {
			collgurl = curl[position];
		}
	Intent intent = new Intent(getApplicationContext(),WebViewDis.class);
	intent.putExtra("collgurl", collgurl);
	startActivity(intent);
}
});
}
});


}

 
}


/*@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
	// TODO Auto-generated method stub
	super.onListItemClick(l, v, position, id);
	Toast.makeText(getApplicationContext(), "Positon is "+position+" Id is "+id, Toast.LENGTH_LONG).show();
	String pos = Integer.toString(position);
	Intent intent = new Intent(getApplicationContext(),viewcollege.class);
	intent.putExtra("position", pos);
	startActivity(intent);	
	}*/
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
public void refresh(View v)
{
	if(checkInternetConnection())
		new LoadAllColleges().execute();
			else
			{
				/*Toast.makeText(getApplicationContext(), "No Internet", Toast.LENGTH_SHORT).show();
				Intent intent1 = new Intent(getApplicationContext(),CustomTabActivity.class);
				startActivity(intent1);*/
				showAlertDialog();
			}
	
	
}
public void visible()
{Button refresh =(Button)findViewById(R.id.buttonrefesh);
  refresh.setVisibility(View.VISIBLE);
	}
}
