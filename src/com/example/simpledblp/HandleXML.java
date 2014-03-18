package com.example.simpledblp;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class HandleXML {

   private String authors = "";
   private String title = "";
   private String pages = "";
   private String year = "";
   private String volume = "";
   private String journal = "";
   private String number = "";
   //need this to create url for other types of search
   private String urlCode = "";
   private String urlString = null;
   private XmlPullParserFactory xmlFactoryObject;
   public volatile boolean parsingComplete = true;
   public HandleXML(String url){
      this.urlString = url;
   }
   public String getAuthors(){
      return authors;
   }
   public String getTitles(){
      return title;
   }
   public String getPages(){
      return pages;
   }
   public String getYear(){
      return year;
   }
   public String getVolume(){
      return volume;
   }
   public String getJournal(){
      return journal;
   }
   public String getNumber(){
      return number;
   }
   public String getUrlcode(){
      return urlCode;
   }

   public void parseXMLAndStoreIt(XmlPullParser myParser) {
      int event;
      String text=null;
      try {
         event = myParser.getEventType();
         System.out.println("Event:"+event);
         while (event != XmlPullParser.END_DOCUMENT) {
            String name=myParser.getName();
            if(myParser.getAttributeCount()==1){
            	   System.out.println(myParser.getAttributeValue(0));
            }
            switch (event){
               case XmlPullParser.START_TAG:
            	   break;
               case XmlPullParser.TEXT:
            	   if(myParser.getText() != "null"){
            		   System.out.println("Text: "+myParser.getText());
            	   text += myParser.getText();
            	   }
            	   break;

               case XmlPullParser.END_TAG:
                  if(name.equals("authors")){
                     authors = text;
                  }
                  else if(name.equals("title")){ 	
                     title = myParser.getAttributeValue(null,"value");
                  }
                  else if(name.equals("pages")){
                     pages = myParser.getAttributeValue(null,"value");
                  }
                  else if(name.equals("year")){
                     year = myParser.getAttributeValue(null,"value");
                  }
                  else if(name.equals("volume")){
                     volume = myParser.getAttributeValue(null,"value");
                  }
                   else if(name.equals("journal")){
                     journal = myParser.getAttributeValue(null,"value");
                  }
                   else if(name.equals("number")){
                     number = myParser.getAttributeValue(null,"value");
                  }
                   else if(name.equals("url")){
                     urlCode = myParser.getAttributeValue(null,"value");
                  }
                  else{
                  }
                  break;
                  }		 
                  event = myParser.nextToken(); 

              }
                 parsingComplete = false;
      } catch (Exception e) {
         e.printStackTrace();
      }

   }
   public void fetchXML(){
      Thread thread = new Thread(new Runnable(){
         @Override
         public void run() {
            try {
               URL url = new URL(urlString);
               HttpURLConnection conn = (HttpURLConnection) 
               url.openConnection();
                  conn.setReadTimeout(10000 /* milliseconds */);
                  conn.setConnectTimeout(15000 /* milliseconds */);
                  conn.setRequestMethod("GET");
                  conn.setDoInput(true);
                  conn.connect();
            InputStream stream = conn.getInputStream();

            xmlFactoryObject = XmlPullParserFactory.newInstance();
            XmlPullParser myparser = xmlFactoryObject.newPullParser();

            myparser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES
            , false);
            myparser.setInput(stream, null);
            parseXMLAndStoreIt(myparser);
            stream.close();
            } catch (Exception e) {
               e.printStackTrace();
            }
        }
    });

    thread.start(); 


   }

}