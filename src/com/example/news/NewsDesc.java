package com.example.news;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class NewsDesc extends Activity {


	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news_desc);
		
		// Hide the status bar.
				View decorView = getWindow().getDecorView();
				int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
				decorView.setSystemUiVisibility(uiOptions);
				ActionBar actionBar = getActionBar();
				actionBar.hide();
		
		
		Intent intent =this.getIntent();
		
		ArrayList<News> data= intent.<News>getParcelableArrayListExtra("newsArray");
		
		
		TextView t1 = (TextView) findViewById(R.id.textView1);
		t1.setText(data.get(0).getNewsTitle());
		
		TextView t2 = (TextView) findViewById(R.id.textView2);
		t2.setText(data.get(0).getNewsDescription());
		
		TextView t4 = (TextView) findViewById(R.id.textView4);
		
		t4.setMovementMethod(LinkMovementMethod.getInstance());
		t4.setClickable(true);
		String text = "<a href='" + data.get(0).getNewsLink()+ "'> Details </a>";
		t4.setText(Html.fromHtml(text));
		
		
		ImageView I1= (ImageView) findViewById(R.id.imageView10);
		

		//new DownloadingImages(data,I1).execute();

		
		FileInputStream fis;
		Bitmap  bitmap=null;
		
	      try {
	  fis = openFileInput(data.get(0).getNewsTitle());
	  bitmap = BitmapFactory.decodeStream(fis);
	  fis.close();
	 
	 
	 
	 } catch (FileNotFoundException e) {
	  // TODO Auto-generated catch block
	  e.printStackTrace();
	 } catch (IOException e) {
	  // TODO Auto-generated catch block
	  e.printStackTrace();
	 }

	      if(bitmap!=null)
	      I1.setImageBitmap(bitmap);
	      else
	    	  I1.setImageResource(R.drawable.news);
	    
		
		
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.news_desc, menu);
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

