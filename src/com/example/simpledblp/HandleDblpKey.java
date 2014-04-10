package com.example.simpledblp;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

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

import com.example.simpledblp.*;


public class HandleDblpKey {
   public static final String NEW_LINE = System.getProperty("line.separator");
   private String dblpKey = "";
   private String urlString;
   private ArrayList<String> keyList = new ArrayList<String>();
   private XmlPullParserFactory xmlFactoryObject;
   public volatile boolean parsingComplete = true;
   public HandleDblpKey(String url){
      this.urlString = url;
   }
 
   public ArrayList<String> getDblpKey(){
      return keyList;
   }

   public void parseXMLAndStoreIt(XmlPullParser myParser) {
      int event;
      String text=null;
      try {
         event = myParser.getEventType();
         while (event != XmlPullParser.END_DOCUMENT) {
            String name=myParser.getName();
            
            switch (event){
               case XmlPullParser.START_TAG:
            	   break;
               case XmlPullParser.TEXT:
            	   String test = myParser.getText();
            	   //if getText returns newline then skip this block
            	   if(!test.contains(NEW_LINE)){
            		   if(myParser.getText() == null){
            			   break;
            		   }
            		   else{
//            		   authorObj = new Author(myParser.getText(), urlpt);
//            		   authorInfo.add(authorObj);
            		   keyList.add(myParser.getText());
            		   //text += myParser.getText();
            		   }
            	   }
            	   break;

               case XmlPullParser.END_TAG:
                  if(name.equals("dblpkey")){
                     dblpKey = "test";
                  }
                  else{
                  }
                  break;
                  }
            	  
                  event = myParser.next(); 
              }
//DEBUG PRINT STATEMENT
         for(String key : keyList){
          	  System.out.println(key);
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