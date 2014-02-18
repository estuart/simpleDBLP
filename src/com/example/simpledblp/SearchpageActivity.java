package com.example.simpledblp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SearchpageActivity extends Activity {

	EditText author, title, year, venue;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_searchpage);
		
//		TextView myTextView=(TextView)findViewById(R.id.textView2);
//		Typeface roboto=Typeface.createFromAsset(getAssets(),"fonts/Roboto-Medium.ttf");
//		myTextView.setTypeface(roboto);
		
		author = (EditText)findViewById(R.id.author);
		title = (EditText)findViewById(R.id.title);
		year = (EditText)findViewById(R.id.year);
		venue = (EditText)findViewById(R.id.venue);
		
		
		final Button button = (Button) findViewById(R.id.searchButton);
		
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	//right now only working for author
            	Intent myIntent = new Intent(SearchpageActivity.this, SearchableActivity.class);
            	myIntent.putExtra("searchQuery", SearchpageActivity.this.author.getText().toString()); //Optional parameters
            	SearchpageActivity.this.startActivity(myIntent);
            	}
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.searchpage, menu);
		return true;
	}

}
