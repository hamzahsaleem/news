package com.example.news;


import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHandler extends SQLiteOpenHelper {

	private  static final String DBName="NewsDB";
	private  static final int DBVersion=1;
	private  static final String TableName="News";
	
	private  static final String NewsID="ID";
	private  static final String NewsTitle="Title";
	private  static final String NewsLink="Link";
	private  static final String NewsDescription="Description";
	private  static final String NewsPicture="Picture";
	private  static final String NewsProvider="Provider";
	private  static final String NewsCategory="Category";
	

	
	Context c;
	
	public DatabaseHandler(Context context) {
		super(context, DBName, null, DBVersion);
		
		c=context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		
		String Create_Table_News="CREATE TABLE "+TableName+"( "+
		NewsID+" INTEGER PRIMARY KEY, "+
		NewsTitle + " TEXT, "+
		NewsLink+ " TEXT, "+
		NewsDescription+ " TEXT, "+
		NewsPicture+ " TEXT, "+
		NewsProvider+ " TEXT, "+
		NewsCategory+ " TEXT)";
				
		
		String Create_Table_Category="CREATE TABLE Categories(CategoryName TEXT)";
		
		
		db.execSQL(Create_Table_News);
		db.execSQL(Create_Table_Category);
		
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	
	public void deleteDB()
	{
		c.deleteDatabase(DBName);
		
		
	}
	
	public void addCategory(String cat)
	{
		
SQLiteDatabase db= this.getWritableDatabase();
		
		ContentValues values= new ContentValues();
		
		values.put("CategoryName", cat);
		
		
		db.insert("Categories", null, values);
			
		
		db.close();
		
		
	}
	
	

	public void addNews(News n)
	{
		
		//ID++;
		
		SQLiteDatabase db= this.getWritableDatabase();
		
		ContentValues values= new ContentValues();
		//values.put(NewsID, ID);
		values.put(NewsTitle, n.getNewsTitle());
		values.put(NewsLink,n.getNewsLink());
		values.put(NewsDescription, n.getNewsDescription());
		values.put(NewsPicture, n.getNewsPicture());
		values.put(NewsProvider, n.getNewsProvider());
		values.put(NewsCategory, n.getNewsCategory());
	
			db.insert(TableName, null, values);
			
		
			
		//	Toast.makeText(c, "Error Inserting in table", Toast.LENGTH_SHORT).show();
			
	
		
		db.close();
		
		
	}
	
	public void EmptyList()
	{
	
			String deleteNewsQuerry= "DELETE FROM "+TableName;

			SQLiteDatabase db= this.getWritableDatabase();
			db.execSQL(deleteNewsQuerry);
		
		db.close();
		
		
		
	}
	
	public void removeCategory(String cat)
	{

		String[] categoryname={cat};
		
		SQLiteDatabase db= this.getWritableDatabase();
		db.delete("Categories", "CategoryName=?", categoryname);
	
	db.close();
		
		
	}
	
	
	
	public ArrayList<String> getAllCategories()
	{
		ArrayList<String> categories= new ArrayList<String>();

		String getCategoriesQuerry= "SELECT * FROM Categories";
		
		SQLiteDatabase db= this.getReadableDatabase();
		
		Cursor c = db.rawQuery(getCategoriesQuerry, null);
		
		if(c.moveToFirst())
		{
			while(true)
			{
				
				categories.add(c.getString(0));
				
				if(c.moveToNext()==false)
				break;
			}
			
		}
		db.close();
		return categories;
		
	}
	
	
	
	
	public ArrayList<News> getAllNews()
	{
		
		ArrayList<News> arr=new ArrayList<News>();
		
		String getNewsQuerry= "SELECT * FROM "+TableName;
		
		SQLiteDatabase db= this.getReadableDatabase();
		
		Cursor c = db.rawQuery(getNewsQuerry, null);
		
		if(c.moveToFirst())
		{
			while(true)
			{
				
				
				News n= new News(c.getString(1),c.getString(2),c.getString(3),c.getString(4),c.getString(5),c.getString(6));
				
				arr.add(n);
				
				if(c.moveToNext()==false)
					break;
				
			}
			
		}
		db.close();
		
		 
		
		return arr;
	}
	
	
	
}
