package com.dreamadmission;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class RegForm extends Activity {

    Button register;
    EditText etname,etcet,etphone,etemail;
    TextView tv;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // load the layout
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.regform);   
        register =(Button)findViewById(R.id.bregister);
        etname = (EditText)findViewById(R.id.etname);
      //  etcet = (EditText)findViewById(R.id.etcet);
        etemail = (EditText)findViewById(R.id.etemail);
        etphone = (EditText)findViewById(R.id.etphone);
        //tv = (TextView)findViewById(R.id.tv);
       // imei = (Button)findViewById(R.id.bimei);
        register.setOnClickListener(new View.OnClickListener() {
			
						@Override
			public void onClick(View arg0) {
				
				send(arg0);
			}
		});

    }

    // this is the function that gets called when you click the button
    public void send(View v)
    {
        
    	Toast.makeText(getBaseContext(),"Thank You For Registering!!",Toast.LENGTH_LONG).show();
    	Intent intent = new Intent(getApplicationContext(),Tabs.class);
    	startActivity(intent);
    	
        
    }
    
 
    
}

