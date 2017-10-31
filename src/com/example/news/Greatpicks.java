package com.example.news;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;


public class Greatpicks extends Activity {

	boolean edu;
	boolean sci;
	boolean busi;
	boolean ent;
	boolean wor;
	boolean pol;
	
	ArrayList<String> categoryList;
	
	DatabaseHandler db = new DatabaseHandler(this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_greatpicks);
	
	edu=false;
	sci=false;
	busi=false;
	ent=false;
	wor=false;
	pol=false;
	
	categoryList=db.getAllCategories();
	
	
	if(categoryList.contains("Education"))
	{edu=true;
	
	ImageButton i= (ImageButton) findViewById(R.id.imageButton1);
	
		i.setImageResource(R.drawable.btnedutick);
	
	}
	
	if(categoryList.contains("Science"))
	{	sci=true;
	
		ImageButton i2= (ImageButton) findViewById(R.id.imageButton3);

		i2.setImageResource(R.drawable.btnscitick);
		
	}
	
	if(categoryList.contains("Business"))
	{	busi=true;
	
		ImageButton i= (ImageButton) findViewById(R.id.imageButton4);
		i.setImageResource(R.drawable.btnbustick);
	
	
	}
	
	if(categoryList.contains("Entertainment"))
	{	ent=true;
		
    	ImageButton i1= (ImageButton) findViewById(R.id.imageButton2);
		i1.setImageResource(R.drawable.btnentertick);
	
	
	
	}
	if(categoryList.contains("World"))
	{	wor=true;
	
	ImageButton i= (ImageButton) findViewById(R.id.imageButton5);
		i.setImageResource(R.drawable.btnwortick);
	
	}
	
	if(categoryList.contains("Politics"))
	{	pol=true;
	ImageButton i1= (ImageButton) findViewById(R.id.imageButton7);
	i1.setImageResource(R.drawable.btnpoltick);
	}
	
	}
	

	
public void btnEdu(View V)
{
	
	ImageButton i= (ImageButton) findViewById(R.id.imageButton1);
	
	if(edu==false)
	{
		i.setImageResource(R.drawable.btnedutick);
			
		edu=true;
		
		db.addCategory("Education");
		
		
		
	}
	else
	{
		i.setImageResource(R.drawable.btnedu);
		
		edu=false;
		
		db.removeCategory("Education");
		
		
	}
	
	

}

public void btnEnter(View V)
{

	ImageButton i1= (ImageButton) findViewById(R.id.imageButton2);
	
	if(ent==false)
	{
		i1.setImageResource(R.drawable.btnentertick);
			
		ent=true;
		
		db.addCategory("Entertainment");
		
	}
	else
	{
		i1.setImageResource(R.drawable.btnenter);
		
		ent=false;
		db.removeCategory("Entertainment");
		
	}

}

public void btnScience(View V)
{

	ImageButton i2= (ImageButton) findViewById(R.id.imageButton3);
	
	if(sci==false)
	{

		i2.setImageResource(R.drawable.btnscitick);
		
		sci=true;
		
		db.addCategory("Science");
		
	}
	else
	{
		i2.setImageResource(R.drawable.btnsci);
		
		sci=false;
		db.removeCategory("Science");
			
	}
	

}

public void btnBusiness(View V)
{

	ImageButton i= (ImageButton) findViewById(R.id.imageButton4);
	
	if(busi==false)
	{
		i.setImageResource(R.drawable.btnbustick);
			
		busi=true;
		
		db.addCategory("Business");
		
	}
	else
	{
		i.setImageResource(R.drawable.btnbus);
		
		busi=false;
		db.removeCategory("Business");
		
	}

}

public void btnWorld(View V)
{
	

	ImageButton i= (ImageButton) findViewById(R.id.imageButton5);
	
	if(wor==false)
	{
		i.setImageResource(R.drawable.btnwortick);
			
		wor=true;
		db.addCategory("World");
		
	}
	else
	{
		i.setImageResource(R.drawable.btnwor);
		
		wor=false;
		db.removeCategory("World");
		
	}
}

public void btnDone(View V)
{
	
 Intent i= new Intent(this,DailyNews.class);
 i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
 startActivity(i);
 
	
}

public void btnPolitics	(View V)
{
ImageButton i= (ImageButton) findViewById(R.id.imageButton7);
	
	if(pol==false)
	{
		i.setImageResource(R.drawable.btnpoltick);
			
		pol=true;
		db.addCategory("Politics");
		
	}
	else
	{
		i.setImageResource(R.drawable.btnpol);
		
		pol=false;
		db.removeCategory("Politics");
		
	}
	
	 
		
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.greatpicks, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}

