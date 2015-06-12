package com.dreamadmission;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


public class CollgFbFanPage extends Activity{
	
	String collg[],fburl[],collgurl;
	EditText inputSearch;
	TextView title;
	int total=0;
	ArrayAdapter<String> adapter;
	@Override
	public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.title);
	title=(TextView)findViewById(R.id.title);
	title.setText("COLLEGES FAN PAGE");
	collg=new String[]{"K.J.Somaiya College of Engineering (KJSCE)",
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
	
	
	fburl= new String[]{"http://www.facebook.com/pages/K-J-Somaiya-College-of-Engineering-KJSCE/470915176330681",
			"http://www.facebook.com/pages/Vivekanand-Education-Societys-Institute-of-Technology-VESIT/459825364101189",
			"http://www.facebook.com/pages/Vidyalankar/204289919694828",
			"http://www.facebook.com/pages/Ramrao-Adik-Education-Societys-Ramrao-Adik-Institute-of-Technology-RAIT/339785396150296",
			"http://www.facebook.com/pages/Anjumaniislams-MH-Saboo-Siddik-College-of-Engineering/392377007547964",
			"http://www.facebook.com/pages/Datta-Meghe-College-of-Engineering/482060478535178",
			"http://www.facebook.com/pages/Watumull-Institute-of-Electronics-Engineering-and-Computer-Technology/313480092119754",
			"http://www.facebook.com/pages/Xavier-Institute-of-Engineering-XIE/302383129897989",
			"http://www.facebook.com/pages/Thadomal-Shahani-Engineering-College-TSEC/160807850767152",
			"http://www.facebook.com/pages/Rizvi-Education-Societys-Rizvi-College-of-Engineering-RCOE/117699355067017",
			"http://www.facebook.com/pages/Fr-Conceicao-Rodrigues-College-Of-Engineering/584898608198133",
			"http://www.facebook.com/pages/Dwarkadas-J-Sanghvi-College-of-Engineering/472279619515508",
			"http://www.facebook.com/pages/Rajiv-Gandhi-Institute-of-Technology-RGIT/254338178041064",
			"http://www.facebook.com/pages/Sardar-Patel-College-of-Engineering-SPCE/385126861608843",
			"http://www.facebook.com/pages/Sardar-Patel-Institute-of-Technology-SPIT/179690668862689",
			"http://www.facebook.com/pages/SIES-Graduate-School-of-Technology/181073818718573",
			"http://www.facebook.com/pages/Don-Bosco-Institute-of-Technology/148203638702527",
			"http://www.facebook.com/pages/Atharva-College-of-Engineering/245806902210959",
			"http://www.facebook.com/pages/Thakur-College-of-Engineering-and-Technology-TCET/613294255348434",
			"http://www.facebook.com/pages/St-Francis-Institute-of-Technology/378707242250435",
			"http://www.facebook.com/pages/Shree-LR-Tiwari-College-of-Engineering/639933756035300",
			"http://www.facebook.com/pages/Vidyavardhinis-College-of-Engineering-And-Technology/195674613922775",
			"http://www.facebook.com/pages/VIVA-Institute-of-Technology/384675928319861",
			"http://www.facebook.com/pages/Aldel-Education-Trusts-St-John-College-of-Engineering-and-Technology/640889935938988",
			"http://www.facebook.com/pages/HJ-Thim-Trusts-Theem-College-of-Engineering/469360923146396",
			"http://www.facebook.com/pages/Vidya-Vikas-Education-Trusts-Technical-Campus/361329970655715",
			"http://www.facebook.com/pages/K-J-Somaiya-Institute-of-Engineering-and-Information-Technology-Kjsieit/344284699033326",
			"http://www.facebook.com/pages/Padmabhushan-Vasantdada-Patil-Pratishthans-College-of-Engineering/144082569113653",
			"http://www.facebook.com/pages/Shivajirao-S-Jondhale-College-of-Engineering/273046666173185",
			"http://www.facebook.com/pages/Lokmanya-Tilak-College-of-Engineering/472742982810989",
			"http://www.facebook.com/pages/Konkan-Gyanpeeth-College-of-Engineering-KGCE/264566170350465",
			"http://www.facebook.com/pages/Leelavati-Awhad-Institute-of-TechnologyManagement-Studies-and-Research/192039594284256",
			"http://www.facebook.com/pages/Dilkap-Research-Institute-Of-Engineering-and-Management-Studies/405980156183818",
			"http://www.facebook.com/pages/GV-Acharya-Institute-of-Engineering-and-Technology/482782255133337",
			"http://www.facebook.com/pages/Koti-Vidya-Charitable-Trusts-Smt-Alamuri-Ratnamala-Institute/630413833638096",
			"http://www.facebook.com/pages/Excelsior-Education-Societys-KC-College-of-Engineering/156828521168330",
			"http://www.facebook.com/pages/BR-Harne-College-of-Engineering-and-Technology/151560185027523",
			"http://www.facebook.com/pages/Vishvatmak-Om-Gurudev-College-of-Engineering/167603426747060",
			"http://www.facebook.com/pages/Shah-And-Anchor-kutchhi-Engineering-College-SAKEC/587562697950401",
			"http://www.facebook.com/pages/Agnel-Charities-Fr-Conceicao-Rodrigues-Institute-of-Technology/589612707738956",
			"http://www.facebook.com/pages/Jawahar-Eduction-Societys-A-C-Patil-College-of-Engineering-ACPCE/652992178050847",
			"http://www.facebook.com/pages/Bharati-Vidyapeeth-College-of-Engineering/123463414528246",
			"http://www.facebook.com/pages/Mahatma-Gandhi-Missions-College-of-Engineering-Technology/428357823928064",
			"http://www.facebook.com/pages/Smt-Indira-Gandhi-College-of-Engineering/309944795807250",
			"http://www.facebook.com/pages/Terna-Public-Charitable-Trusts/424632037635595",
			"http://www.facebook.com/pages/MES-Pillais-Institute-of-Information-Technology/183770925115822",
			"http://www.facebook.com/pages/Pillais-College-of-HOCL-College-of-Engineering-Technology/185199581640227",
			"http://www.facebook.com/pages/Saraswati-Education-Societys-Saraswati-College-of-Engineering/1383431145201695",
			"http://www.facebook.com/pages/Anjumaniislams-Kalsekar-Technical-Campus-Panvel/449460181810922"};




	
	final ListView lv = (ListView)findViewById(R.id.list_view);
	inputSearch = (EditText) findViewById(R.id.inputSearch);
	adapter = new ArrayAdapter<String>(this,R.layout.simplerow,R.id.rowTextView,collg);
	lv.setAdapter(adapter);
	lv.setCacheColorHint(Color.WHITE);
	inputSearch.addTextChangedListener(new TextWatcher() {
        
        @Override
        public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
            // When user changed the Text
            CollgFbFanPage.this.adapter.getFilter().filter(cs);   
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
			collgurl = fburl[total];
			total=0;
			}
			else {
				collgurl = fburl[position];
			}
		Intent intent = new Intent(getApplicationContext(),CollegeWebView.class);
		intent.putExtra("collgurl", collgurl);
		startActivity(intent);
	}
	});
	}
	
}
