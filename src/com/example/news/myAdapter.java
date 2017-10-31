package com.example.news;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Filter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;












import android.app.Activity;

public class myAdapter extends ArrayAdapter<News> {

	Context c;
	int layoutFile,layoutFile2;
	ArrayList<News> data,data1;
	
	ValueFilter valuefilter;
	
	public myAdapter(Context context, int resource,int resource2, ArrayList<News> myArray) {
		super(context, resource, myArray);
		
		c=context;
		layoutFile=resource;
		layoutFile2=resource2;
		data=myArray;
		data1=data;
		ValueFilter valuefilter;
		
	}

	
	public void setData(ArrayList<News> myArray)
	{
		data=myArray;
		data1=data;
		
	}
	
	public ArrayList<News> getData()
	{
		return data;
		
	}
	
	
	@Override
	public int getItemViewType(int position) {
	
		if(data.get(position).getSelected().equals("1"))
		return 0;
		
		
		return 1;
	}


	@Override
	public int getViewTypeCount() {
		
		return 2;
	}


	

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		
		View row=convertView;
		
		int type=getItemViewType(position);
		
		if(row== null)
		{
			LayoutInflater inflater = ((Activity)c).getLayoutInflater();
			if(type==0)
			row = inflater.inflate(layoutFile, parent, false);
			else
				row = inflater.inflate(layoutFile2, parent, false);
			
		}
		
		if(type==1)
			{
			
				
				ImageButton im1= (ImageButton) row.findViewById(R.id.imageButton1);

				
				im1.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						
						Intent intent = new Intent(Intent.ACTION_SEND);
						
						intent.putExtra(Intent.EXTRA_TEXT, data.get(position).getNewsLink());
						intent.setType("text/plain");
						c.startActivity(intent);
						
						Toast.makeText(getContext(), "News shared", Toast.LENGTH_SHORT).show();
						data.get(position).setSelected("1");
						notifyDataSetChanged();
						
					}
			         
			     });
				
				
				
				ImageButton im2= (ImageButton) row.findViewById(R.id.imageButton2);
				 
				im2.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						DatabaseHandler db = new DatabaseHandler(getContext());
						
						db.addNews(data.get(position));
						db.close();
	
						if(data.get(position).getNewsimage()!=null)
						{
						
						
						FileOutputStream fos;
						
						try{
							
							fos=c.openFileOutput(data.get(position).getNewsTitle(), Context.MODE_PRIVATE);
							data.get(position).getNewsimage().compress(Bitmap.CompressFormat.PNG, 100, fos);	  
							fos.close();
							
						}
						
						
						catch(FileNotFoundException e)
						{
							e.printStackTrace();
							
						} catch(IOException e)
						{
							e.printStackTrace();
							
						}
						
						}
						
						
						
						Toast.makeText(getContext(), "News saved", Toast.LENGTH_LONG).show();
						data.get(position).setSelected("1");
						notifyDataSetChanged();
						
					}
			         
			     });
					
				}
			
			
		TextView t1 = (TextView) row.findViewById(R.id.textView1);
		t1.setText(data.get(position).getNewsTitle());
		
		TextView t2 = (TextView) row.findViewById(R.id.textView2);
		t2.setText(data.get(position).getNewsDescription());
		
		TextView t3 = (TextView) row.findViewById(R.id.textView3);
		t3.setText(data.get(position).getNewsProvider());
		
		ImageView I2= (ImageView) row.findViewById(R.id.imageView2);
		
		if(data.get(position).getNewsProvider().equals("abcNEWS"))
		I2.setImageResource(R.drawable.abc);
		else if(data.get(position).getNewsProvider().equals("CNN"))
			I2.setImageResource(R.drawable.cnn);
		else
			I2.setImageResource(R.drawable.hughinton);
		
		
		ImageView I1= (ImageView) row.findViewById(R.id.imageView1);
		
		if(data.get(position).getNewsimage()!=null)
		{
			I1.setImageBitmap(data.get(position).getNewsimage());
			I1.setVisibility(View.VISIBLE);
			
		}
		
		else
		{
			
			I1.setVisibility(View.INVISIBLE);
		}

		
		
		return row;
	}

	
	
	@Override
	public int getCount() {

		return data.size();
	}

	

	@Override
	public Filter getFilter()
	{
		if(valuefilter==null)
		{
			valuefilter = new ValueFilter();
			
		}
		return valuefilter;
		
	}
	
	
	private class ValueFilter extends Filter{
		
		 @Override
	        protected FilterResults performFiltering(CharSequence constraint) {

		 FilterResults result = new FilterResults(); 
		 
		 if(constraint!=null && constraint.length()!=0)
		 {
			 ArrayList<News> arr = new ArrayList<News>();
			 
			 for(int i=0;i<data1.size();i++)
			 {
				 if((data1.get(i).getNewsTitle().toUpperCase()).contains(constraint.toString().toUpperCase()) || (data1.get(i).getNewsDescription().toUpperCase()).contains(constraint.toString().toUpperCase()))
				 {
					 News n1 = new News(data1.get(i).getNewsTitle(),data1.get(i).getNewsLink(),data1.get(i).getNewsDescription(),data1.get(i).getNewsPicture(),data1.get(i).getNewsProvider(),data1.get(i).getNewsCategory());
					 arr.add(n1);
					 
				 }
				 
			 }
			 

			 result.count=arr.size();
			 result.values=arr;
			 
			 
			 
		 }
		 
		 else
		 {
			 result.count=data1.size();
			 result.values=data1; 
			 
		 }
		 
		 
		
		 return result;
		 
		 }

		@Override
		protected void publishResults(CharSequence constraint,FilterResults results) {

				data= (ArrayList<News>) results.values;
				notifyDataSetChanged();
			
			
			
		}
		
		 
		 
	}
	
	
	
	
	
}
