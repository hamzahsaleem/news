package com.example.news;
import java.util.ArrayList;
import java.util.TimerTask;

import android.content.Context;

public class fetchNewsTask extends TimerTask {

	ArrayList<String> urlList;
	Context c;
	myAdapter a;
	public fetchNewsTask(Context c,ArrayList<String> urlList,myAdapter a)
	{
		this.urlList=urlList;
		this.c=c;
		this.a=a;
	}
	
	
	@Override
	public void run() 
	{
		
				RefreshNews newsTask= (RefreshNews) new RefreshNews(c,urlList,a).execute();
		
	}
	
	

}
