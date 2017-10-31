package com.example.news;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;


public class DownloadingImages extends AsyncTask<Void, Void, Void> {

	ArrayList<News> news;
	ImageView i;
	myAdapter adapter;
	Bitmap bitmap;
	boolean cancel;
	
	
	DownloadingImages(ArrayList<News> arr,myAdapter a)
	{
		
	news=arr;
	adapter=a;
	cancel=false;
	}
	
	
	DownloadingImages(ArrayList<News> arr,ImageView i)
	{
		
	news=arr;
	this.i=i;
	adapter=null;
	cancel=false;
	}
	
	boolean isCancel()
	{
		return cancel;
		
		
	}
	
	void setCancel(boolean a)
	{
		
		cancel=a;
	}
	
	
	
	@Override
	protected Void doInBackground(Void... params) {
	
	

		try {
			
		for(int i=0;i<news.size();i++)
		{
			
			if(isCancel())
				break;
			
			
			
			if(!news.get(i).getNewsPicture().equals(""))
			{
		//	bitmap = BitmapFactory.decodeStream((InputStream)new URL(news.get(i).getNewsPicture()).openConnection().getInputStream() );
			
			bitmap=BitmapFactory.decodeStream((InputStream)new URL(news.get(i).getNewsPicture()).openConnection().getInputStream() );
				
			if(bitmap!=null)
			{
				
			bitmap= Bitmap.createScaledBitmap(bitmap,200, 150, true);
			news.get(i).setNewsimage(bitmap);
			publishProgress();
			}
			}
			
			else
			{	bitmap=null;
			news.get(i).setNewsimage(bitmap);
			}
			
		}
			
		} 
		catch (MalformedURLException e) {
			  e.printStackTrace();
			} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
	
		return null;
		
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
	}

	@Override
	protected void onPostExecute(Void result) {

		
		
	}
	@Override
	protected void onProgressUpdate(Void... values) {
		
		if(adapter!=null)
		adapter.notifyDataSetChanged();
		else
			i.setImageBitmap(bitmap);
		
	}
	
}
