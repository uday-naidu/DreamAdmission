package com.dreamadmission;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Browser;
import android.view.KeyEvent;
import android.view.View;

public class Dte extends Activity {
	private double lat[]; 
	private double lng[];
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layouteee);
		lat=new double[]{19.062756,18.530145,19.961708,19.853059,20.946844,21.158392};
		lng=new double[]{72.854502,73.827203,73.831622,75.323726,77.782397,79.067330};
	}
	public void dteWebsite(View v)
	{
		String dteurl = "http://dte.org.in/fe2013/StaticPages/HomePage.aspx?did=381";
		Intent intent = new Intent(getApplicationContext(),WebViewDis.class);
		intent.putExtra("collgurl", dteurl);
		startActivity(intent);
	}
	public void cap1(View v)
	{
		String cap1url = "http://fileserver.mkcl.org/fe2012/OasisModules_Files/Files/54.pdf?did=645";
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(cap1url));
		startActivity(i);
	}
	public void cap2(View v)
	{
		String cap2url = "http://fileserver.mkcl.org/fe2012/OasisModules_Files/Files/42.pdf";
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(cap2url));
		startActivity(i);
	}
	public void capr1(View v)
	{
		String dteurl = "http://180.179.68.128/fe2013/AllotmentModule/frmCheckAllotmentStatusRound1.aspx?did=586";
		Intent intent = new Intent(getApplicationContext(),WebViewDis.class);
		intent.putExtra("collgurl", dteurl);
		startActivity(intent);
	}
	public void mockallot(View v)
	{
		String dteurl = "http://180.179.68.128/fe2013/AllotmentModule/frmCheckMockAllotmentStatus.aspx?did=582";
		Intent intent = new Intent(getApplicationContext(),WebViewDis.class);
		intent.putExtra("collgurl", dteurl);
		startActivity(intent);
	}
	public void meritlist(View v)
	{
		String dteurl = "http://180.179.68.128/fe2013/MeritModule/frmCheckFinalMeritStatus.aspx?did=561";
		Intent intent = new Intent(getApplicationContext(),WebViewDis.class);
		intent.putExtra("collgurl", dteurl);
		startActivity(intent);
	}
	public void map1(View v)
	{
		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:" +lat[0] +"," +lng[0] +"?q=" +lat[0] +","+lng[0]+"(" +"MUMBAIREGION"+")")); startActivity(intent);
	}
	public void map2(View v)
	{
		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:" +lat[1] +"," +lng[1] +"?q=" +lat[1] +","+lng[1]+"(" +"PUNEREGION"+")")); startActivity(intent);
	}
	public void map3(View v)
	{
		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:" +lat[2]+"," +lng[2] +"?q=" +lat[2] +","+lng[2]+"(" +"NASHIKREGION"+")")); startActivity(intent);
	}
	public void map4(View v)
	{
		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:" +lat[3] +"," +lng[3] +"?q=" +lat[3] +","+lng[3]+"(" +"AURANGABADREGION"+")")); startActivity(intent);
	}
	public void map5(View v)
	{
		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:" +lat[4] +"," +lng[4] +"?q=" +lat[4] +","+lng[4]+"(" +"AMRAVATIREGION"+")")); startActivity(intent);
	}
	public void map6(View v)
	{
		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:" +lat[5] +"," +lng[5] +"?q=" +lat[5] +","+lng[5]+"(" +"NAGPURREGION"+")")); startActivity(intent);
	}
	public void call1(View v)
	{
		Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:022-26474892"));
		startActivity(intent);
	}
	public void call2(View v)
	{
		Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:020-25656234"));
		startActivity(intent);
	}
	public void call3(View v)
	{
		Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:0253-2461479"));
		startActivity(intent);
	}
	public void call4(View v)
	{
		Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:0240-2334216"));
		startActivity(intent);
	}
	public void call5(View v)
	{
		Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:0721-2573027"));
		startActivity(intent);
	}
	public void call6(View v)
	{
		Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:0712-2565143"));
		startActivity(intent);
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
		Dte.this.finish();
		Intent intent = new Intent(getApplicationContext(), Updates.class);
		startActivity(intent);
	}
	})
	.show();*/
	
		Dte.this.finish();
		Intent intent = new Intent(getApplicationContext(), Updates.class);
		startActivity(intent);
	// Say that we've consumed the event
	return true;
	}

	return super.onKeyDown(keyCode, event);
	} 
	

	
	

}
