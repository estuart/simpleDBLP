package com.example.simpledblp;

public class BookmarkDB {
    
    //private variables
    int _id;
    String _author;
    String _title;
     
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
    public BookmarkDB(String author, String _title){
        this._author = author;
        this._title = _title;
    }
    // getting ID
    public int getID(){
        return this._id;
    }
     
    // setting id
    public void setID(int id){
        this._id = id;
    }
     
    // getting name
    public String getAuthor(){
        return this._author;
    }
     
    // setting name
    public void setAuthor(String author){
        this._author = author;
    }
     
    // getting phone number
    public String getTitle(){
        return this._title;
    }
     
    // setting phone number
    public void setTitle(String title){
        this._title = title;
    }
}