package com.example.news;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.util.Log;

public class HandleXML {

	private String title;
	private String link;
	private String description;
	private String category;
	private String url;
	private String photourl;
	private boolean parsingdone;
	private	XmlPullParserFactory pullparserfactoryobj;
	ArrayList<News> arr;
	
	
	
public HandleXML( String url) {
		
		this.url = url;
		title="";
		link="";
		description="";
		category="";
		photourl="";
		parsingdone=false;
		arr= new ArrayList<News>();
		
		
	}
	
	
	private void parseAndStoreXML(XmlPullParser parser)
	{
		
		int event;
		String text="";
		
		try{
			
			event=parser.getEventType();
			
			while(event!= XmlPullParser.END_DOCUMENT)
			{
				String tagName=parser.getName();
				
			if(event==XmlPullParser.START_TAG)	
			{
			if(tagName.equals("media:thumbnail"))
			{
				photourl=parser.getAttributeValue(null, "url");
				
				
			}
				
				
				
			}
				
			else if(event==XmlPullParser.TEXT)	
			{
				text=parser.getText();
				
			}	
			else if(event==XmlPullParser.END_TAG)	
			{
				if(tagName.equals("title"))
				{
				title =text;	
				}
				
				else if(tagName.equals("link"))
				{
					link=text;
					
				}
				
				else if(tagName.equals("description"))
				{
					description=text;
					
				}
				else if(tagName.equals("category"))
				{
					category=text;
					News news = new News(title,link,description,photourl,"abcNEWS",category);
					arr.add(news);
					
					if(arr.size()==11)
						break;
					
				}
				
				
				
			}
			
			event=parser.next();	
			
			
			}
			
			
			
		parsingdone=true;
			
			
		}
		
		catch(Exception e)
		{
			
			e.printStackTrace();
			
		}
		
		
		
		
		
		
		
	}
	
	
	private void parseAndStoreXMLCNN(XmlPullParser parser)
	{
		
		int event;
		String text="";
		
		try{
			
			event=parser.getEventType();
			
			while(event!= XmlPullParser.END_DOCUMENT)
			{
				String tagName=parser.getName();
				
			
				
			 if(event==XmlPullParser.TEXT)	
			{
				text=parser.getText();
				
			}	
			else if(event==XmlPullParser.END_TAG)	
			{
				if(tagName.equals("title"))
				{
				title =text;	
				}
				
				else if(tagName.equals("link"))
				{
					link=text;
					
				}
				
				else if(tagName.equals("description"))
				{
					
					
					int index=text.indexOf("<");
						if(index!=-1)	
						{
							description=text.substring(0, index);
						}
						else
							description=text;
							
			    }
					
				else if(tagName.equals("media:thumbnail"))
				{
					photourl=parser.getAttributeValue(null, "url");
					News news = new News(title,link,description,photourl,"CNN","Default");
					arr.add(news);
					if(arr.size()==11)
						break;
					
					
				}	
					
					
				}
				
				
			event=parser.next();	
			
			
			}
			
		parsingdone=true;
			
			
		}
		
		catch(Exception e)
		{
			
			e.printStackTrace();
			
		}
		
		
		
		
		
		
		
	}
	

	private void parseAndStoreXMLHughintonPost(XmlPullParser parser)
	{
		
		int event;
		String text="";
		
		try{
			
			event=parser.getEventType();
			
			while(event!= XmlPullParser.END_DOCUMENT)
			{
				String tagName=parser.getName();
				
			
				
			 if(event==XmlPullParser.TEXT)	
			{
				text=parser.getText();
				
			}	
			else if(event==XmlPullParser.END_TAG)	
			{
				if(tagName.equals("title"))
				{
					for (int i = 0;  i < text.length(); i++)
			        {
						int index2=text.indexOf("<");
						if(index2==-1)
							break;
						
						int end=text.substring(index2).indexOf(">");
						
						if(end==-1)
							break;
							
							end=end+index2+1;
							String remove=text.substring(index2, end);
							
							text = text.replace(remove,"");
		
							
			        }
					
					
					
					
					
				title =text;	
				}
				
				else if(tagName.equals("link"))
				{
					link=text;
					
				}
				
				else if(tagName.equals("description"))
				{
					
					photourl ="";
					String t1=text;
			
					int index=text.indexOf("http://images.huffingtonpost.com");
					if(index==-1)
						photourl ="";
					else
					
					{
					int end=text.substring(index).indexOf(".jpg");
					int end1=text.substring(index).indexOf(".png");
					int fend=-1;
					
					
					
					if(end!=-1)
					{
						if(end1==-1)
						{
							fend=end;
							
						}
						
						else
						{
							if(end<end1)
								fend=end;
							else
								fend=end1;
							
						}
						
					}
					
					else
					{
						
						if(end1!=-1)
							fend=end1;
						
						else
							
							fend=-1;
						
					}
					
					
					
					if(index!=-1 && fend!=-1)
					{
					
					fend=fend+index+4;
					photourl=text.substring(index, fend);
					
					}
					
					else
						photourl="";
					
					}
					
					
				String desc="";
				
					while(true)
					{
						int i=text.indexOf("<p");
						if(i==-1)
							break;
						int e=text.substring(i).indexOf("</p>");
						
						if(i!=-1 && e!=-1)
						{
							e=i+e;
							i=i+3;
							desc=desc+text.substring(i,e);
							text=text.substring(e+3);
							
							
							
						}
						else
							break;
						
						
						
					}		
					
		
					for (int i = 0;  i < desc.length(); i++)
			        {
						int index2=desc.indexOf("class=");
						if(index2==-1)
							break;
						
						int end=desc.substring(index2).indexOf(">");
						
						if(end==-1)
							break;
							
							end=end+index2+1;
							String remove=desc.substring(index2, end);
							
							desc = desc.replace(remove,"");
		
							
			        }
					
					
					
					
					
					
					
					if(desc.indexOf("-- This feed")==0 || desc.indexOf("style=")==0)
					{
						desc=t1;
					}
					
		
					while(desc.indexOf("&") !=-1)
					{
					desc=desc.replace("&", "");
					}
				

					for (int i = 0;  i < desc.length(); i++)
			        {
						int index1=desc.indexOf("#");
						if(index1==-1)
							break;
						
						int end=desc.substring(index1).indexOf(";");
						
						if(end==-1)
							break;
							
						
							end=end+index1+1;
							String remove=desc.substring(index1, end);
							
							desc = desc.replace(remove,"");
						
							
			        }
		
					
					for (int i = 0;  i < desc.length(); i++)
			        {
						int index2=desc.indexOf("<");
						if(index2==-1)
							break;
						
						int end=desc.substring(index2).indexOf(">");
						
						if(end==-1)
							break;
							
							end=end+index2+1;
							String remove=desc.substring(index2, end);
							
							desc = desc.replace(remove,"");
		
							
			        }
					
					
					for (int i = 0;  i < desc.length(); i++)
			        {
					
					
					int indexx=desc.indexOf("name=");
					if(indexx==-1)
						break;
					
						
						int endd=indexx+12;
						String remove=desc.substring(indexx, endd);
						
						desc = desc.replace(remove,"");
	
			        }
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					if(title.contains("Ep."))
						desc="This feed and its contents are property of Huffington Post.";
					
					
					
					
					
				description=desc;
				category="Default";
				
				News news = new News(title,link,description,photourl,"huffingtonpost",category);
				arr.add(news);
				
				if(arr.size()==11)
					break;
				
				
				}
					
					
				
			}
			
			event=parser.next();	
			
			
			}
			
			
			
		parsingdone=true;
			
			
		}
		
		catch(Exception e)
		{
			
			e.printStackTrace();
			
		}
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	public String getTitle() {
		return title;
	}
	public String getLink() {
		return link;
	}
	public String getDescription() {
		return description;
	}
	
	public boolean isParsingdone() {
		return parsingdone;
	}
	
	public ArrayList<News> fetchXml()
	{
		
		
		try{
			
			URL url1 = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
			
			conn.setReadTimeout(10000);
			conn.setConnectTimeout(15000);
			conn.setRequestMethod("GET");
			conn.setDoInput(true);
			
			conn.connect();
			InputStream inputStream=conn.getInputStream();
			
			
			pullparserfactoryobj = XmlPullParserFactory.newInstance();
			XmlPullParser pullparser = pullparserfactoryobj.newPullParser();
			pullparser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
			pullparser.setInput(inputStream, null);
			
			if(url.contains("abcnews"))
			parseAndStoreXML(pullparser);
			else if (url.contains("huffingtonpost"))
			parseAndStoreXMLHughintonPost(pullparser);
			else
			parseAndStoreXMLCNN(pullparser);
			
			inputStream.close();
		}
		
		catch(Exception e)
		{
			Log.e("MYAPP", e.toString());
			
		}
		
		return arr;
		
	}
	
	
	
	
	
	
	
}
