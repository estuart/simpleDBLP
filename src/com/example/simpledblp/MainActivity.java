package com.example.simpledblp;



import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

private static final String TAG = "MAIN";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
//		TextView myTextView=(TextView)findViewById(R.id.textView2);
		Typeface roboto=Typeface.createFromAsset(getAssets(),"fonts/Roboto-Medium.ttf");
//		myTextView.setTypeface(roboto);
		
		
		final Button searchButton = (Button) findViewById(R.id.Search);
		final Button bookMarksButton = (Button) findViewById(R.id.Bookmarks);
		final Button settingsButton = (Button) findViewById(R.id.Settings);
		final Button helpButton = (Button) findViewById(R.id.Help);
		
		searchButton.setTypeface(roboto);
		bookMarksButton.setTypeface(roboto);
		settingsButton.setTypeface(roboto);
		helpButton.setTypeface(roboto);
		
		
        searchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent myIntent = new Intent(MainActivity.this, SearchpageActivity.class);
            	//myIntent.putExtra("searchQuery", MainActivity.this.mEdit.getText().toString()); //Optional parameters
            	MainActivity.this.startActivity(myIntent);
            	}
        });
        
        bookMarksButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent myIntent = new Intent(MainActivity.this, BookmarksActivity.class);
            	//myIntent.putExtra("searchQuery", MainActivity.this.mEdit.getText().toString()); //Optional parameters
            	MainActivity.this.startActivity(myIntent);
            	}
        });
        
        settingsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent myIntent = new Intent(MainActivity.this, SettingsActivity.class);
            	//myIntent.putExtra("searchQuery", MainActivity.this.mEdit.getText().toString()); //Optional parameters
            	MainActivity.this.startActivity(myIntent);
            	}
        });
        
        helpButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent myIntent = new Intent(MainActivity.this, HelpActivity.class);
            	//myIntent.putExtra("searchQuery", MainActivity.this.mEdit.getText().toString()); //Optional parameters
            	MainActivity.this.startActivity(myIntent);
            	}
        });
        
        
        
     }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
//	public void selfDestruct(View view) {
//	     // Kabloey
//	 }

}
