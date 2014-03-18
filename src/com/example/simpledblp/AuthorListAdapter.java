package com.example.simpledblp;

import java.util.ArrayList;

import android.content.Context;
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
	
	public View getView(int position, View convertView, ViewGroup parent) {
		// assign the view we are converting to a local variable
			LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View v = inflater.inflate(R.layout.author_listing, null);
		

			TextView text = (TextView) v.findViewById(R.id.middleName);
			Typeface roboto=Typeface.createFromAsset(context.getAssets(),"fonts/RobotoCondensed-Light.ttf");
			text.setTypeface(roboto);
			text.setText(authors.get(position).getName());
			
			return v;
	}

}
