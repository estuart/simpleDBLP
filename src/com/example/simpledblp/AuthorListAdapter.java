package com.example.simpledblp;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AuthorListAdapter extends ArrayAdapter<Author> {

	private ArrayList<Author> authors;
	private Context context;
	
	public AuthorListAdapter(Context context, ArrayList<Author> authors) {
		super(context, R.layout.author_listing, authors);
		this.authors = authors;
		this.context = context;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
			View view = null;
		// assign the view we are converting to a local variable
			LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View v = inflater.inflate(R.layout.author_listing, null);
		

			TextView text = (TextView) v.findViewById(R.id.authorName);
			Typeface roboto=Typeface.createFromAsset(context.getAssets(),"fonts/RobotoCondensed-Light.ttf");
			text.setTypeface(roboto);
			text.setText(authors.get(position).getName());
			final String authorUrlpt = authors.get(position).getUrlpt();
			text.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent myIntent = new Intent(v.getContext(), ResultsActivity.class);
			    	myIntent.putExtra("authorUrlpt", authorUrlpt); //Optional parameters
			    	v.getContext().startActivity(myIntent);		
				}
			});
			
			return v;
	}

}
