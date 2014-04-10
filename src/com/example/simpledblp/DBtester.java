package com.example.simpledblp;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
 
public class DBtester extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        //FIX****
        setContentView(R.layout.activity_bookmarks);
         
        DatabaseHandler db = new DatabaseHandler(this);
         
        /**
         * CRUD Operations
         * */
        // Inserting Contacts
        Log.d("Insert: ", "Inserting .."); 
        db.addBookmark(new BookmarkDB("Ravi", "Article 1"));        
        db.addBookmark(new BookmarkDB("Srinivas", "Article 2"));
        db.addBookmark(new BookmarkDB("Tommy", "Article 3"));
        db.addBookmark(new BookmarkDB("Karthik", "Article 4"));
         
        // Reading all contacts
        Log.d("Reading: ", "Reading all contacts.."); 
        List<BookmarkDB> contacts = db.getAllBookmarks();       
        String fullList = "";
        for (BookmarkDB cn : contacts) {
            String log = "Id: "+cn.getID()+" ,Name: " + cn.getAuthor() + " ,Phone: " + cn.getTitle() + '\n';
                // Writing Contacts to log
            Log.d("Name: ", log);
            fullList += log;     
        }
        TextView text = (TextView) findViewById(R.id.textView1);
        text.setText(fullList);
        

    }
}
