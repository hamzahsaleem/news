package com.example.news;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class LoadNews extends AsyncTask<Void, Void, Void> {


	Context con;
	boolean status;
	ArrayList<News> newsArray;
	ArrayList<News> allNews;
	ArrayList<String> urlList;
	myAdapter adapter;
	ListView l1;
	TextView t1;
	TextView t2;
	ProgressBar p1;
	RelativeLayout r;

	DownloadingImages d;
	ActionBar actionbar;
	
	LoadNews(Context c,ArrayList<News> allNews,ArrayList<String> urlList, myAdapter adapter,ListView l1,RelativeLayout r,TextView t1,TextView t2,ProgressBar p1,ActionBar actionbar)
	{
		con=c;
	status=false;
	this.newsArray=new ArrayList<News>();
	this.allNews = allNews;
	this.urlList=urlList;
	this.adapter=adapter;
	this.l1=l1;
	this.t1=t1;
	this.t2=t2;
	this.p1=p1;
	this.r=r;
	this.actionbar=actionbar;
	
	}
	
	
	
	@Override
	protected Void doInBackground(Void... params) {
	
		
		for(int i=0;i<urlList.size();i++)
		{
			
			
			
		HandleXML obj= new HandleXML(urlList.get(i));
		
		newsArray=obj.fetchXml();
	
		if(newsArray.size()!=0)
			newsArray.remove(0);
		
		for(int j=0;j<newsArray.size();j++)
		{
			allNews.add(newsArray.get(j));
			
		}
		
		
		}
		
		long seed = System.nanoTime();
		Collections.shuffle(allNews, new Random(seed));
		
		status=true;
		return null;
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
	}

	@Override
	protected void onPostExecute(Void result) {
	
	if(newsArray.size()==0)
	{
		Intent intent = new Intent(con,SavedNews.class);
		
		con.startActivity(intent);	
		
	}
	
	
	else
	{
		
		

	
		actionbar.show();
		
	
	r.setBackgroundResource(R.drawable.texback);
	
	t1.setVisibility(View.GONE);
	t2.setVisibility(View.GONE);
	p1.setVisibility(View.GONE);
	
	adapter.notifyDataSetChanged();
	 
	l1.setVisibility(View.VISIBLE);
	
	 
	d= (DownloadingImages) new DownloadingImages(allNews,adapter).execute();


	
	}
	
	}

	
	public DownloadingImages getDownImageTask()
	{
		return d;
		
	}
	
	
	@Override
	protected void onProgressUpdate(Void... values) {
		// TODO Auto-generated method stub
		super.onProgressUpdate(values);
	}

	public boolean getStat()
	{
		return status;
		
	}
	
	public ArrayList<News> getNews()
	{
		return allNews;
		
	}
	
}
