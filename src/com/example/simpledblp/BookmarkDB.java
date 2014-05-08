package com.example.simpledblp;

public class BookmarkDB {
    
    //private variables
    int _id;
    String _author;
    String _title;
    String _year;
    String _url;
     
    // Empty constructor
    public BookmarkDB(){
         
    }
    // constructor
    public BookmarkDB(int id, String author, String _title){
        this._id = id;
        this._author = author;
        this._title = _title;
    }
     
    // constructor
    public BookmarkDB(String _author, String _title, String _url, String _year){
        this._author = _author;
        this._title = _title;
        this._url = _url;
        this._year = _year;
    }
    // getting ID
    public int getID(){
        return this._id;
    }
     
    // setting id
    public void setID(int id){
        this._id = id;
    }
     
    // getting authoer
    public String getAuthor(){
        return this._author;
    }
     
    // setting setting
    public void setAuthor(String author){
        this._author = author;
    }
     
    // getting title
    public String getTitle(){
        return this._title;
    }
     
    // setting title
    public void setTitle(String title){
        this._title = title;
    }
    public String getURL(){
        return this._url;
    }
     
    // setting url
    public void setURL(String url){
        this._url = url;
    }
    public void setYear(String year){
    	this._year = year;
    }
    public String getYear(){
    	return this._year;
    }
}