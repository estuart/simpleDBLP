package com.example.simpledblp;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class ResultsActivity extends Activity {
	
	private String url = "http://dblp.uni-trier.de/pers/xk/";
	private String urlpt;
	private HandleDblpKey keyObj;
	private HandleXML resultsObj;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_results);
		Bundle intent = getIntent().getExtras();
		if (intent == null){
			return;
		}
		else{
		      String urlpt = intent.getString("authorUrlpt");
		      TextView myTextView=(TextView)findViewById(R.id.testUrlpt);
			  Typeface roboto=Typeface.createFromAsset(getAssets(),"fonts/Roboto-Medium.ttf");
			  myTextView.setTypeface(roboto);
			  myTextView.setText(urlpt);
			  
			  String finalUrl = url + urlpt;
			  ArrayList<String> dblpKey = getKey(finalUrl);
			  
			  for(String key : dblpKey){
				  //handle xml for either journal or conference entry
				  //have method that returns journal entries and another that
				  //returns conference entries
				  //make array adapters for journal entry and one for conference
				  //each entry should have a bookmark button that adds it to the db
			  }
//			  resultsObj = new HandleXML(finalUrl);
//		      resultsObj.fetchXML();
//		      while(resultsObj.parsingComplete);
//		      //authorSearch.setText(obj.getAuthors());
//		      ArrayList<Author> authLinks= resultsObj.getAuthObj(); 
//		      AuthorListAdapter adapter = new AuthorListAdapter(this, authLinks);
//		      ListView lv = (ListView)findViewById(R.id.listView1);
//		      lv.setAdapter(adapter);
		}
	}
	
	public ArrayList<String> getKey(String url2parse){
		
		keyObj = new HandleDblpKey(url2parse);
	    keyObj.fetchXML();
	    while(keyObj.parsingComplete);
	    //authorSearch.setText(obj.getAuthors());
		ArrayList<String> key = keyObj.getDblpKey();
		return key;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.results, menu);
		return true;
	}

}
