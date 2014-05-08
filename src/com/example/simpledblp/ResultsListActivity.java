package com.example.simpledblp;

import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.app.ListActivity;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
//import android.app.ListActivity;
import android.content.Intent;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.support.v4.app.NavUtils;
import android.view.View; 
import android.widget.ArrayAdapter; 
import android.widget.Toast; 


public class ResultsListActivity extends ListActivity {
	
	
	// All static variables
	static final String URL = "http://dblp.uni-trier.de/pers/xx/";
	// XML node keys
	static final String KEY_TITLE = "title"; // parent node
	static final String KEY_AUTHOR = "author";
	static final String KEY_JOURNAL = "journal";
	static final String KEY_YEAR = "year";
	static final String KEY_PAGES = "pages";
	static final String KEY_EE = "ee";
	static final String KEY_URL = "url";
	static final String KEY_BOOKTITLE = "booktitle";
	static final String KEY = "r";
	static final String KEY_PUB = "publication";
	
	private ArrayList<HashMap<String, String>> entries = new ArrayList<HashMap<String, String>>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_results_lists);
		// Show the Up button in the action bar.
		setupActionBar();
		Bundle intent = getIntent().getExtras();
		if (intent == null){
			return;
		}
		else{
		      String urlpt = intent.getString("authorUrlpt");
		
		     // ArrayList<HashMap<String, String>> entries = new ArrayList<HashMap<String, String>>();
		
		      XMLParser parser = new XMLParser();
		      String xml = parser.getXmlFromUrl(URL+urlpt);		//get xml
		      Document doc = parser.getDomElement(xml);			//get dom element
		      
		      NodeList nl = doc.getElementsByTagName(KEY);

		      //Now loop through all "r" nodes
		      for(int i =0; i <nl.getLength(); i++){
		    	  //create a new hashmap
		    	  HashMap<String, String> map = new HashMap<String, String>();
		    	  Element e = (Element) nl.item(i);
		    	  String name = e.getTagName();
		    	  //now add each child node to the HashMap key-> value
		    	  if(name.equals("r")){
//		    		  
		    		  map.put(KEY_TITLE, parser.getValue(e, KEY_TITLE));
		    		  map.put(KEY_AUTHOR, parser.getValue(e, KEY_AUTHOR));
		    		  map.put(KEY_YEAR, parser.getValue(e, KEY_YEAR));
		    		  map.put(KEY_PAGES, parser.getValue(e, KEY_PAGES));
		    		  map.put(KEY_EE, parser.getValue(e, KEY_EE));
		    		  map.put(KEY_URL, parser.getValue(e, KEY_URL));
		    		  map.put(KEY_JOURNAL, parser.getValue(e, KEY_JOURNAL));
		    		  map.put(KEY_BOOKTITLE, parser.getValue(e, KEY_BOOKTITLE));  
		    	  }
		    	
		    	  
		    	  //map.put(KEY_AUTHOR, authorList);
		    	  entries.add(map);
		      }
		     
		     
		     Typeface roboto=Typeface.createFromAsset(getAssets(),"fonts/RobotoCondensed-Light.ttf");

		     ListAdapter adapter = new SimpleAdapter(this, entries,
		    		 R.layout.list_entries,
		    		 new String[] {KEY_TITLE, KEY_AUTHOR, KEY_YEAR, KEY_PAGES, KEY_EE, KEY_URL,KEY_PUB}, new int[]{
		    		 R.id.name,R.id.author,R.id.year,R.id.pages, R.id.EE, R.id.URL, R.id.PUB});
		     
		     setListAdapter(adapter);
		     ListView lv = getListView();
		     
		     lv.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						// getting values from selected ListItem
						String name = ((TextView) view.findViewById(R.id.name)).getText().toString();
						String author = ((TextView) view.findViewById(R.id.author)).getText().toString();
						String year = ((TextView) view.findViewById(R.id.year)).getText().toString();
						String pages = ((TextView) view.findViewById(R.id.pages)).getText().toString();
						String EE = ((TextView) view.findViewById(R.id.EE)).getText().toString();
						String URL = ((TextView) view.findViewById(R.id.URL)).getText().toString();
						String PUB = ((TextView) view.findViewById(R.id.PUB)).getText().toString();
						
						 //Starting new intent
						Intent in = new Intent(getApplicationContext(), SingleMenuItemActivity.class);
						in.putExtra(KEY_TITLE, name);
						in.putExtra(KEY_AUTHOR, author);
						in.putExtra(KEY_YEAR, year);
						in.putExtra(KEY_PAGES, pages);
						in.putExtra(KEY_EE, EE);
						in.putExtra(KEY_URL, URL);
						in.putExtra(KEY_PUB, PUB);
						
						in.putExtra("values", entries);
						startActivity(in);

					}
				});
		     
		     }
		
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
