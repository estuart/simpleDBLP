package com.example.simpledblp;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.support.v4.app.NavUtils;

public class SearchableActivity extends Activity {
	
	private TextView myText = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_searchable);
		
		// Show the Up button in the action bar.
//		ActionBar actionBar = getActionBar();
//		actionBar.setDisplayHomeAsUpEnabled(true);
		
		Bundle intent = getIntent().getExtras();
			if (intent == null){
				return;
			}
		
		LinearLayout lView = (LinearLayout)findViewById(R.id.LinearLayout1);
		myText = new TextView(this);
		String searchValue = intent.getString("searchQuery");
		myText.setText(searchValue);
		lView.addView(myText);
		
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


}
