package com.dreamadmission;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class CollegeWebsites extends Activity{
	
	String collg[],curl[],collgurl;
	EditText inputSearch;
	int total=0;
	ArrayAdapter<String> adapter;
	@Override
	public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.collegewebsites);
	this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
	
	collg=new String[]
			{"Veermata Jijabai Technological Institute ( VJTI )","K J Somaiya College of Engineering ( KJSCE )",
			"Vivekanand Education Society’s Institute of Technology (VESIT)",
			"Vidyalankar Institute of Technology",
			"Ramrao Adik Institute of Technology (RAIT)",
			"Anjuman-I-Islam’s M.H. Saboo Siddik College of Engineering",
			"Datta Meghe College of Engineering",
			"Watumull Institute of Electronics Engineering and Computer Technology",
			"Xavier Institute of Engineering (XIE)",
			"Thadomal Shahani Engineering College (TSEC)",
			"Rizvi Education Society's Rizvi College of Engineering (RCOE)",
			"Fr. Conceicao Rodrigues College Of Engineering",
			"Dwarkadas J. Sanghvi College of Engineering",
			"Manjara Charitable Trust’s Rajiv Gandhi Institute of Technology (RGIT)",
			"Bharatiya Vidya Bhavan’s Sardar Patel College of Engineering (SPCE)",
			"Bharatiya Vidya Bhavan’s Sardar Patel Institute of Technology (SPIT)",
			"S.I.E.S. Graduate School of Technology",
			"Don Bosco Institute of Technology",
			"Atharva College of Engineering ( ACE )",
			"Thakur College of Engineering and Technology (TCET)",
			"St. Francis Institute of Technology(SFIT)",
			"Shree L.R. Tiwari College of Engineering",
			"Vidyavardhini‘s College of Engineering And Technology(VARTAK)",
			"VIVA Institute of Technology",
			"Aldel Education Trust’s St. John College of Engineering and Technology",
			"H.J. Theem Trust’s Theem College of Engineering",
			"Vidya Vikas Education Trust's Technical Campus",
			"K. J. Somaiya Institute of Engineering and Information Technology (KJSIEIT)",
			"Padmabhushan Vasantdada Patil Pratishthan’s College of Engineering",
			"Shivajirao S. Jondhale College of Engineering",
			"Lokmanya Tilak College of Engineering",
			"Konkan Gyanpeeth College of Engineering (KGCE)",
			"Leelavati Awhad Institute of Technology,Management Studies and Research",
			"Dilkap Research Institute Of Engineering and Management Studies",
			"Leela Education Society, G.V. Acharya Institute of Engineering and Technology",
			"Koti Vidya Charitable Trust's Smt. Alamuri Ratnamala Institute of Engineering and Technology",
			"Excelsior Education Society's K.C. College of Engineering",
			"B.R. Harne College of Engineering and Technology",
			"Vishvatmak Om Gurudev College of Engineering",
			"Shah And Anchor kutchhi Engineering College (SAKEC)",
			"Agnel Charities' Fr. Conceicao Rodrigues Institute of Technology",
			"Jawahar Eduction Society's A. C. Patil College of Engineering (ACPCE)",
			"Bharati Vidyapeeth College of Engineering",
			"Mahatma Gandhi Mission’s College of Engineering & Technology",
			"Smt. Indira Gandhi College of Engineering",
			"Terna Public Charitable Trust's Terna Engineering College",
			"M.E.S Pillai’s Institute of Information Technology",
			"Mahatma Education Society's Pillai's College of HOCL College of Engineering & Technology",
			"Saraswati Education Society's Saraswati College of Engineering",
			"Anjuman-I-Islam's Kalsekar Technical Campus, Panvel"};
	curl=new String[]{"http://www.vjti.ac.in/","http://www.somaiya.edu/kjsce/",
			"http://www.vesit.edu/",
			"http://www.vit.edu.in",
			"http://www.rait.ac.in/",
			"http://www.mhsscoe.org/",
			"http://www.dmce.edu/",
			"http://www.watumull.edu",
			"http://www.xavierengg.com/",
			"http://www.tsec.edu/",
			"http://eng.rizvi.edu.in/",
			"http://www.frcrce.ac.in/",
			"http://www.djscoe.org/",
			"http://www.mctrgit.ac.in",
			"http://www.spce.ac.in",
			"http://www.spit.ac.in",
			"http://www.siesgst.net",
			"http://www.dbit.in/",
			"http://www.atharvaeducation.com/",
			"http://www.thakureducation.org",
			"http://www.sfitengg.org/",
			"http://www.slrtce.in",
			"http://www.vcet.edu.in/",
			"http://www.viva-technology.org",
			"http://www.sjcet.co.in",
			"http://theemcoe.org/",
			"http://universalcollegeofengineering.edu.in",
			"http://www.somaiya.edu/kjsieit/",
			"http://www.pvppcoe.ac.in/",
			"http://www.shivajiraojondhalecoe.com/",
			"http://www.ltce.ltjss.net/",
			"http://www.kgce.org/",
			"http://www.lait.in/",
			"http://www.driems.in/",
			"http://www.leelaeducation.org/",
			"http://www.kvctarmiet.com/",
			"http://www.kccoe.com/",
			"http://www.brharnecet.org",
			"http://www.vishwatmakengg.com",
			"http://shahandanchor.com/",
			"http://www.fcrit.ac.in/",
			"http://www.acpce.org/",
			"http://www.bvcoenm.org.in",
			"http://www.mgmmumbai.ac.in/",
			"http://www.sigce.edu.in/",
			"http://www.terna.org/",
			"http://www.piit.ac.in/",
			"http://www.mes.ac.in",
			"http://www.sce.edu.in/",
			"http://www.aiktc.com/"};
	
	final ListView lv = (ListView)findViewById(R.id.list_view);
	lv.setCacheColorHint(Color.WHITE);
	inputSearch = (EditText) findViewById(R.id.inputSearch);
	adapter = new ArrayAdapter<String>(this,R.layout.simplerow,R.id.rowTextView,collg);
	lv.setAdapter(adapter);
	inputSearch.addTextChangedListener(new TextWatcher() {
        
        @Override
        public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
            // When user changed the Text
            CollegeWebsites.this.adapter.getFilter().filter(cs);   
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
	
		
		//Toast.makeText(getApplicationContext(), "Positon is "+position+" Id is "+id, Toast.LENGTH_LONG).show();
		//TextView text = (TextView) arg0.findViewById(R.id.rowTextView);
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
	CollegeWebsites.this.finish();
	Intent intent = new Intent(getApplicationContext(), Updates.class);
	startActivity(intent);
	}
	})
	.show();*/
		CollegeWebsites.this.finish();
		Intent intent = new Intent(getApplicationContext(), Updates.class);
		startActivity(intent);

	// Say that we've consumed the event
	return true;
	}

	return super.onKeyDown(keyCode, event);
	} 
	
}
