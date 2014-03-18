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

public class AuthorListAdapter extends ArrayAdapter<String> {

	private ArrayList<String> authors;
	private Context context;
	
	public AuthorListAdapter(Context context, int resource, ArrayList<String> authors) {
		super(context, resource, authors);
		this.authors = authors;
		this.context = context;
	}
	
	public View getView(int position, View convertView, ViewGroup parent) {
		// assign the view we are converting to a local variable
		View v = convertView;

		// first check to see if the view is null. if so, we have to inflate it.
		// to inflate it basically means to render, or show, the view.
		if (v == null) {
			LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(R.layout.author_listing, null);
		}

		/*
		 * Recall that the variable position is sent in as an argument to this method.
		 * The variable simply refers to the position of the current object in the list. (The ArrayAdapter
		 * iterates through the list we sent it)
		 * 
		 * Therefore, i refers to the current Item object.
		 */
		String i = authors.get(position);

		if (i != null) {

			// This is how you obtain a reference to the TextViews.
			// These TextViews are created in the XML files we defined.
			TextView text = (TextView) v.findViewById(R.id.middleName);
			Typeface roboto=Typeface.createFromAsset(context.getAssets(),"fonts/RobotoCondensed-Light.ttf");
			text.setTypeface(roboto);

			// check to see if each individual textview is null.
			// if not, assign some text!
			if (text != null){
				text.setText(i);
			}
		}

		// the view must be returned to our activity
		return v;
	}

}
