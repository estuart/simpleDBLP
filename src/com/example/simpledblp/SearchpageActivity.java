package com.example.simpledblp;

import java.util.ArrayList;

import org.w3c.dom.Document;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


public class SearchpageActivity extends Activity {

	EditText authorSearchField;
	private String url = "http://dblp.uni-trier.de/search/author?xauthor=";
	private TextView authorSearch,authors;
	private HandleXML obj;
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_searchpage);
		
//		TextView myTextView=(TextView)findViewById(R.id.textView2);
//		Typeface roboto=Typeface.createFromAsset(getAssets(),"fonts/Roboto-Medium.ttf");
//		myTextView.setTypeface(roboto);
		
		authorSearchField = (EditText)findViewById(R.id.author);
		authorSearch = (TextView)findViewById(R.id.textView2);
//		title = (EditText)findViewById(R.id.title);
//		year = (EditText)findViewById(R.id.year);
//		venue = (EditText)findViewById(R.id.venue);
		
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.searchpage, menu);
		return true;
	}
	
	public void open(View view){
		  String searchValue = authorSearchField.getText().toString();
		  //System.out.println("searchValue: "+searchValue);
	      String finalUrl = url + searchValue;
	      //authors.setText(finalUrl);
	      obj = new HandleXML(finalUrl);
	      obj.fetchXML();
	      while(obj.parsingComplete);
	      //authorSearch.setText(obj.getAuthors());
	      ArrayList<Author> authLinks= obj.getAuthObj(); 
	      AuthorListAdapter adapter = new AuthorListAdapter(this, authLinks);
	      ListView lv = (ListView)findViewById(R.id.listView1);
	      lv.setAdapter(adapter);
	   
	}
}
