package com.example.simpledblp;

import java.util.ArrayList;
import java.util.List;
 
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
 
public class DatabaseHandler extends SQLiteOpenHelper {
 
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "BookmarkDBLP";
 
    // BookmarkDBs table name
    private static final String TABLE_BOOKMARKS = "bookmarks";
 
    // BookmarkDBs Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_AUTHOR = "author";
    private static final String KEY_TITLE = "title";
 
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
 
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_BookmarkDBS_TABLE = "CREATE TABLE " + TABLE_BOOKMARKS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_AUTHOR + " TEXT,"
                + KEY_TITLE + " TEXT" + ")";
        db.execSQL(CREATE_BookmarkDBS_TABLE);
    }
 
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
    	oldVersion = 1;
    	newVersion = 2;
    	if(oldVersion<newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKMARKS);
        onCreate(db);
    	}
 
        // Create tables again
       
    }
 
    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */
 
    // Adding new BookmarkDB
    void addBookmark(BookmarkDB BookmarkDB) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_AUTHOR, BookmarkDB.getAuthor()); // BookmarkDB Name
        values.put(KEY_TITLE, BookmarkDB.getTitle()); // BookmarkDB Phone
 
        // Inserting Row
        db.insert(TABLE_BOOKMARKS, null, values);
        db.close(); // Closing database connection
    }
 
    // Getting single BookmarkDB
    BookmarkDB getBookmarkDB(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
 
        Cursor cursor = db.query(TABLE_BOOKMARKS, new String[] { KEY_ID,
                KEY_AUTHOR, KEY_TITLE }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
 
        BookmarkDB BookmarkDB = new BookmarkDB(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        // return BookmarkDB
        return BookmarkDB;
    }
     
    // Getting All BookmarkDBs
    public List<BookmarkDB> getAllBookmarks() {
        List<BookmarkDB> BookmarkDBList = new ArrayList<BookmarkDB>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_BOOKMARKS;
 
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                BookmarkDB BookmarkDB = new BookmarkDB();
                BookmarkDB.setID(Integer.parseInt(cursor.getString(0)));
                BookmarkDB.setAuthor(cursor.getString(1));
                BookmarkDB.setTitle(cursor.getString(2));
                // Adding BookmarkDB to list
                BookmarkDBList.add(BookmarkDB);
            } while (cursor.moveToNext());
        }
 
        // return BookmarkDB list
        return BookmarkDBList;
    }
 
    // Updating single BookmarkDB
    public int updateBookmarkDB(BookmarkDB BookmarkDB) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_AUTHOR, BookmarkDB.getAuthor());
        values.put(KEY_TITLE, BookmarkDB.getTitle());
 
        // updating row
        return db.update(TABLE_BOOKMARKS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(BookmarkDB.getID()) });
    }
 
    // Deleting single BookmarkDB
    public void deleteBookmarkDB(BookmarkDB BookmarkDB) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_BOOKMARKS, KEY_ID + " = ?",
                new String[] { String.valueOf(BookmarkDB.getID()) });
        db.close();
    }
 
 
    // Getting BookmarkDBs Count
    public int getBookmarkDBsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_BOOKMARKS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
 
        // return count
        return cursor.getCount();
    }
    
}