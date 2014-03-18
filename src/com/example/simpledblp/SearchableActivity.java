package com.example.simpledblp;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.support.v4.app.NavUtils;

public class SearchableActivity extends Activity {
	
	
	private String url = "http://dblp.uni-trier.de/search/author?xauthor=";
	private TextView authorSearch,authors;
	private HandleXML obj;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_searchable);
		authors = (TextView)findViewById(R.id.textView1);
		
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.searchable, menu);
		return true;
	}
	public void open(View view){
		  Bundle intent = getIntent().getExtras();
			if (intent == null){
				return;
			}
	      String searchValue = intent.getString("searchQuery");
	      //String url = authorSearch.getText().toString();
	      String finalUrl = url + searchValue;
	      authors.setText(finalUrl);
	      obj = new HandleXML(finalUrl);
	      obj.fetchXML();
	      while(obj.parsingComplete);
	      authors.setText(obj.getAuthors());
//	      temperature.setText(obj.getTemperature());
//	      humidity.setText(obj.getHumidity());
//	      pressure.setText(obj.getPressure());

	   }


}
