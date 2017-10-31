package com.example.news;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import android.content.Context;
import android.os.AsyncTask;



public class RefreshNews extends AsyncTask<Void, Void, Void> {

	
	Context con;
	boolean status;
	ArrayList<News> newsArray;
	myAdapter a;
	ArrayList<News> allNews;
	ArrayList<String> urlList;

	
	RefreshNews(Context c,ArrayList<String> urlList,myAdapter a)
	{
	
		con=c;
	status=false;
	this.newsArray=new ArrayList<News>();
	this.a=a;
	allNews = new ArrayList<News>();
	this.urlList=urlList;
	
	
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


	ArrayList<News> newsArray=getNews();
	
	if(newsArray.size()!=0)
	{
		
	
	a.setData(newsArray);
	a.notifyDataSetChanged();
	new DownloadingImages(newsArray,a).execute();

	
	}
	
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
