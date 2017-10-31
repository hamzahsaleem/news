package com.example.news;


import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class News implements Parcelable{
	
	 String NewsTitle="Title";
	 String NewsDescription="Description";
	 String NewsPicture="Picture";
	 String NewsProvider="Provider";
	 String NewsLink="Link";
	 String NewsCategory="category";
	 String selected="1";
	 Bitmap NewsImage=null;
	 
	 
	 public News(Parcel source) {
		 NewsTitle=  source.readString();
		 NewsDescription=  source.readString();
		 NewsPicture=  source.readString();
		 NewsProvider=  source.readString();
		 NewsLink=  source.readString();
		 NewsCategory=  source.readString();
		 selected=source.readString();
		 
	    }
	 
	 
	public News(String newsTitle,String link, String newsDescription, String newsPicture,
			String newsProvider,String newsCategory) {
		
		NewsTitle = newsTitle;
		NewsDescription = newsDescription;
		NewsPicture = newsPicture;
		NewsProvider = newsProvider;
		NewsLink=link;
		NewsCategory=newsCategory;
		selected="1";
		
		
	}
	public String getNewsTitle() {
		return NewsTitle;
	}
	public void setNewsTitle(String newsTitle) {
		NewsTitle = newsTitle;
	}
	
	public String getSelected() {
		return selected;
	}
	public void setSelected(String s) {
		selected = s;
	}
	
	public Bitmap getNewsimage() {
		return NewsImage;
	}
	public void setNewsimage(Bitmap img) {
		NewsImage = img;
	}
	
	
	public String getNewsCategory() {
		return NewsCategory;
	}
	public void setNewsCategory(String newscategory) {
		NewsCategory = newscategory;
	}
	
	
	public String getNewsLink() {
		return NewsLink;
	}
	public void setNewsLink(String newsLink) {
		NewsLink = newsLink;
	}
	
	public String getNewsDescription() {
		return NewsDescription;
	}
	public void setNewsDescription(String newsDescription) {
		NewsDescription = newsDescription;
	}
	public String getNewsPicture() {
		return NewsPicture;
	}
	public void setNewsPicture(String newsPicture) {
		NewsPicture = newsPicture;
	}
	public String getNewsProvider() {
		return NewsProvider;
	}
	public void setNewsProvider(String newsProvider) {
		NewsProvider = newsProvider;
	}
	
	@Override
	public int describeContents() {
		return this.hashCode();
		
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(NewsTitle);
		dest.writeString(NewsDescription);
		dest.writeString(NewsPicture);
		dest.writeString(NewsProvider);
		dest.writeString(NewsLink);
		dest.writeString(NewsCategory);
		dest.writeString(selected);
		
		
		
		
	}
	 
	 
	public static final Parcelable.Creator CREATOR
    = new Parcelable.Creator() {
public News createFromParcel(Parcel in) {
    return new News(in);
}

public News[] newArray(int size) {
    return new News[size];
}
};
	 
	 
}
