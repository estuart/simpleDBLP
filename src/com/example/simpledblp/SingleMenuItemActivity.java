package com.example.simpledblp;



import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SingleMenuItemActivity  extends Activity {
	
	// XML node keys
	static final String KEY_TITLE = "title";
	static final String KEY_AUTHOR = "author";
	static final String KEY_YEAR = "year";
	static final String KEY_PAGES = "pages";
	static final String KEY_EE = "ee";
	static final String KEY_URL = "url";
	static final String KEY_PUB = "publication";
	
	private String urlDB;
	private String yearDB;
	
	private void setURLDB(String url){
		urlDB = url;
	}
	private String getURLDB(){
		return urlDB;
	}
	private void setYearDB(String year){
		yearDB = year;
	}
	private String getYearDB(){
		return yearDB;
	}
	
	DatabaseHandler db = new DatabaseHandler(this);
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_list_item);
        
        // getting intent data
        Intent in = getIntent();
        
        // Get XML values from previous intent
        final String title = in.getStringExtra(KEY_TITLE);
        final String author = in.getStringExtra(KEY_AUTHOR);
        final String year = in.getStringExtra(KEY_YEAR);
        final String pages = in.getStringExtra(KEY_PAGES);
        final String ee = in.getStringExtra(KEY_EE);
        final String url = in.getStringExtra(KEY_URL);
        final String pub = in.getStringExtra(KEY_PUB);
        
        setYearDB(year);
        setURLDB(url);
        
        
        // Displaying all values on the screen
        TextView lbltitle = (TextView) findViewById(R.id.title_label);
        TextView lblauthor = (TextView) findViewById(R.id.author_label);
        TextView lblyear = (TextView) findViewById(R.id.year_label);
        TextView lblpages = (TextView) findViewById(R.id.pages_label);
        TextView lblee = (TextView) findViewById(R.id.ee_label);
        TextView lblurl = (TextView) findViewById(R.id.url_label);
        TextView lblpub = (TextView) findViewById(R.id.pub_label);
        
        Typeface font0 = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Medium.ttf");
        lbltitle.setText(title);
        lblauthor.setTypeface(font0);
        lblauthor.setText("Authors: "+author);
        lblyear.setTypeface(font0);
        lblyear.setText("Year: "+year);
        lblpages.setText("Pages: "+pages);
        lblee.setText(ee);
        lblurl.setText("http://dblp.uni-trier.de/"+url);
        //lblpub.setText("http://dblp.uni-trier.de/"+pub);
        
        final Button button1 = (Button)findViewById(R.id.openInBrowser);
		button1.getBackground().setColorFilter(0xff2581c4, PorterDuff.Mode.MULTIPLY);
		Typeface font1 = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Medium.ttf");
		button1.setTypeface(font1);
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				Uri uri = Uri.parse("http://dblp.uni-trier.de/"+url);
				
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(intent);
			}
		});
		
		final Button button2 = (Button)findViewById(R.id.google);
		button2.getBackground().setColorFilter(0xff2581c4, PorterDuff.Mode.MULTIPLY);
		Typeface font2 = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Medium.ttf");
		button2.setTypeface(font2);
		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String urlz="";
				try {
					urlz = "https://www.google.com/search?q=" + URLEncoder.encode(title, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Uri uri = Uri.parse(urlz);
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(intent);
			}
		});
		final Button button3 = (Button)findViewById(R.id.googleScholar);
		button3.getBackground().setColorFilter(0xff2581c4, PorterDuff.Mode.MULTIPLY);
		Typeface font3 = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Medium.ttf");
		button3.setTypeface(font3);
		button3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String urlz="";
				try {
					urlz = "http://scholar.google.com/scholar?q=" + URLEncoder.encode(title, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Uri uri = Uri.parse(urlz);
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(intent);
			}
		});
		final Button button4 = (Button)findViewById(R.id.microsoftAcademic);
		button4.getBackground().setColorFilter(0xff2581c4, PorterDuff.Mode.MULTIPLY);
		Typeface font4 = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Medium.ttf");
		button4.setTypeface(font4);
		button4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String urlz="";
				try {
					urlz = "http://academic.research.microsoft.com/search?query=" + URLEncoder.encode(title, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Uri uri = Uri.parse(urlz);
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(intent);
			}
		});
		
		final Button button5 = (Button) findViewById(R.id.bookmark);
		button5.getBackground().setColorFilter(0xff2581c4, PorterDuff.Mode.MULTIPLY);
		Typeface font5 = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Medium.ttf");
		button5.setTypeface(font5);
		button5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				BookmarkDB entry = new BookmarkDB(author, title, getURLDB(), getYearDB());
				db.addBookmark(entry);
				Toast.makeText(getApplicationContext(), "Entry has been added to bookmarks!", Toast.LENGTH_SHORT).show();
				
			}
		});
    }
	
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.results_list, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
