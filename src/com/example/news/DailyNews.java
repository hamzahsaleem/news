package com.example.news;

import java.util.ArrayList;
import java.util.Timer;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

public class DailyNews extends Activity {

	LoadNews loadTask;

	RefreshNews rf;
	boolean searchBarOpened=false;
	Drawable iconsearch;
	Drawable iconclose;
	MenuItem SearchAction;
	 myAdapter adapter;
	 ArrayList<News> newsArray = new ArrayList<News>();
	 
	 ArrayList<String> urlList= new ArrayList<String>();
	 ArrayList<String> categoryList;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_daily_news);
		


		// Hide the status bar.
		View decorView = getWindow().getDecorView();
		int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
		decorView.setSystemUiVisibility(uiOptions);
		ActionBar actionBar = getActionBar();
		actionBar.hide();
		
		
		
		
		if(isNetworkAvailable()==true)
		{	
	
			iconsearch = getDrawable(R.drawable.search);
			 iconclose = getDrawable(R.drawable.clear);
			 
			
			ListView l1= (ListView) findViewById(R.id.listView1);
			TextView t1= (TextView) findViewById(R.id.textView1);
			TextView t2= (TextView) findViewById(R.id.textView2);
			ProgressBar p1=(ProgressBar) findViewById(R.id.progressBar1);
			
			adapter = new myAdapter(this,R.layout.listviewitem,R.layout.listviewitem2,newsArray);	
			   l1.setAdapter(adapter);

			
			RelativeLayout r1= (RelativeLayout) findViewById(R.id.relativelayout1);
			l1.setVisibility(View.INVISIBLE);
			
			r1.setBackgroundResource(R.drawable.newsbackground);
			
			
			DatabaseHandler db = new DatabaseHandler(this);
			categoryList=db.getAllCategories();
			
			
			if(categoryList.contains("World"))
			{
				urlList.add("http://rss.cnn.com/rss/edition_world.rss");
				urlList.add("http://feeds.abcnews.com/abcnews/internationalheadlines");
				urlList.add("http://feeds.huffingtonpost.com/c/35496/f/677102/index.rss");
				
				
			}
			 if(categoryList.contains("Education"))
			{
				
				urlList.add("http://feeds.huffingtonpost.com/c/35496/f/677061/index.rss");	
			}
			
			 if(categoryList.contains("Entertainment"))
			{
				
				urlList.add("http://rss.cnn.com/rss/edition_entertainment.rss");
				urlList.add("http://feeds.abcnews.com/abcnews/entertainmentheadlines");
				urlList.add("http://feeds.huffingtonpost.com/c/35496/f/677060/index.rss");
				
			}
			
			 if(categoryList.contains("Science"))
			{
				
			urlList.add("http://rss.cnn.com/rss/edition_space.rss");
			urlList.add("http://feeds.abcnews.com/abcnews/technologyheadlines");
			urlList.add("http://feeds.huffingtonpost.com/c/35496/f/677088/index.rss");
				
				
			}
			
			 if(categoryList.contains("Business"))
			{
				
						
			urlList.add("http://rss.cnn.com/rss/money_news_international.rss");
			urlList.add("http://feeds.abcnews.com/abcnews/moneyheadlines");
			urlList.add("http://feeds.huffingtonpost.com/c/35496/f/677048/index.rss");
					
				
			}
			
			 if(categoryList.contains("Politics"))
			{
						urlList.add("http://feeds.huffingtonpost.com/c/35496/f/677086/index.rss");
						urlList.add("http://feeds.abcnews.com/abcnews/politicsheadlines");
						
			}
				
				
			
			if(urlList.size()==0)
			{
				urlList.add("http://rss.cnn.com/rss/edition.rss");
				urlList.add("http://feeds.abcnews.com/abcnews/topstories");
				urlList.add("http://feeds.huffingtonpost.com/c/35496/f/677045/index.rss");
				
			}
			
			
			
			
			
		loadTask= (LoadNews) new LoadNews(this,newsArray,urlList,adapter,l1,r1,t1,t2,p1,actionBar).execute();
			

		
	    l1.setOnItemClickListener(new OnItemClickListener() {
	       

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				
				
				Intent intent = new Intent(getApplicationContext(),WebViewActivity.class);
				
				
				String newsLink=adapter.getData().get(position).getNewsLink();
				
				intent.putExtra("newsLink", newsLink);
				
				startActivity(intent);
				
				
				
				
				
			}
			
	    });
		
	    
		l1.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				adapter.getData().get(position).setSelected("2");
				adapter.notifyDataSetChanged();
				
				
				return true;
			}
		});
	    

		
		fetchNewsTask newsTask= new fetchNewsTask( this,urlList,adapter);
		
		Timer timer = new Timer("fetchNews");
		
		long delay =5*60*1000;
		
		timer.schedule(newsTask, delay, delay);
		
		
		
		} 
		
		else
		{
			
			
			Intent intent = new Intent(this,SavedNews.class);
			
			startActivity(intent);	
			finish();
		}
		
		
	}

	
	private boolean isNetworkAvailable() {
	    ConnectivityManager connectivityManager 
	          = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
	    return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.daily_news, menu);
		return true;
	}
	
	public void openBar()
	{
		// Set custom view on action bar.
		ActionBar actionBar = getActionBar();
	    actionBar.setDisplayShowCustomEnabled(true);
	    actionBar.setCustomView(R.layout.searchbox);

	    // Search edit text field setup.
	    EditText e1 = (EditText) actionBar.getCustomView()
	        .findViewById(R.id.editText1);
	    
	    e1.addTextChangedListener(new TextWatcher() {          
	        @Override
	        public void onTextChanged(CharSequence s, int start, int before, int count) {                                   
	               //here is your code
	        	
	        	CharSequence c1=s;
	        	
	                adapter.getFilter().filter(c1);

	        }                       
	        @Override
	        public void beforeTextChanged(CharSequence s, int start, int count,
	                int after) {
	            // TODO Auto-generated method stub                          
	        }                       
	        @Override
	        public void afterTextChanged(Editable s) {
	            // TODO Auto-generated method stub                          

	        }
			
	    });
	    
	    e1.requestFocus();

	    // Change search icon accordingly.
	    SearchAction.setIcon(iconclose);
	    searchBarOpened = true;

	    
	    
	    
		    
		
	}
	
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		SearchAction = menu.findItem(R.id.search);
		
		return super.onPrepareOptionsMenu(menu);
	}

	public void closeBar()
	{
		 // Remove custom view.
	    getActionBar().setDisplayShowCustomEnabled(false);

	    // Change search icon accordingly.
	    SearchAction.setIcon(iconsearch);
	    searchBarOpened = false;
	    
	    adapter.getFilter().filter("");
	    
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		
		int id = item.getItemId();
		
		if(id==R.id.search)
		{
		
			if(searchBarOpened)
			{
				closeBar();
			}
			else
			{
				openBar();
				
			}
			
			
			return true;
		}
		
		else if(id==R.id.refresh)
		{
			if(loadTask.getDownImageTask()!=null)
				loadTask.getDownImageTask().setCancel(true);
			
			
			Toast.makeText(this, "Refreshing", Toast.LENGTH_SHORT).show();
			
		rf=(RefreshNews) new RefreshNews( this,urlList ,adapter).execute();
			
			return true;
		}
		
		
		else if(id==R.id.listitems)
		{
			
			Intent intent = new Intent(this,SavedNews.class);
			
			startActivity(intent);
			
			
			return true;
		}
		
		else if (id == R.id.action_settings) {
		
			
			
			if(loadTask.getDownImageTask()!=null)
				loadTask.getDownImageTask().setCancel(true);
			
			Intent intent = new Intent(this,Greatpicks.class);
			
			
			startActivity(intent);
			
			return true;
		}
		
		
		
		
		
		return super.onOptionsItemSelected(item);
	}
}
