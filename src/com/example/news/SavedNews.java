package com.example.news;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class SavedNews extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_saved_news);
		
		
		
		DatabaseHandler db = new DatabaseHandler(this);
		
		
		final ArrayList<News> newsArray = db.getAllNews();
		

		FileInputStream fis;
		Bitmap  bitmap=null;
		
		
	  for(int i=0;i<newsArray.size();i++)
	  {
		 
		  
		try {
	  fis = openFileInput(newsArray.get(i).getNewsTitle());
	  bitmap = BitmapFactory.decodeStream(fis);
	  fis.close();
	 
	 newsArray.get(i).setNewsimage(bitmap);
	 
	 } catch (FileNotFoundException e) {
	  // TODO Auto-generated catch block
	  e.printStackTrace();
	 } catch (IOException e) {
	  // TODO Auto-generated catch block
	  e.printStackTrace();
	 }
		
		  
		
	  }
		
		
		
		
		myAdapter adapter = new myAdapter(this,R.layout.listviewitem,R.layout.listviewitem2,newsArray);	

		//new DownloadingImages(newsArray,adapter).execute();

		
	    ListView l1 = (ListView) findViewById(R.id.listView1);	
		
	
	    l1.setAdapter(adapter);
	
	

 
    l1.setOnItemClickListener(new OnItemClickListener() {
       

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			
			Intent intent = new Intent(getApplicationContext(),NewsDesc.class);
			
			ArrayList<News> news= new ArrayList<News>(); 
			news.add(newsArray.get(position));
			
			intent.putParcelableArrayListExtra("newsArray", news);
		
			
			
			startActivity(intent);
			
			
		}
		
    });
		
		
		
		
		
		
		
		
		
	}

	
	
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.saved_news, menu);
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
