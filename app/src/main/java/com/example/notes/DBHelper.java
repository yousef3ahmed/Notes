package com.example.notes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {


    public  static final  String DBNAME = "login.db"  ;

    public  static final  String TABLE_NAME = "COUNTRIES" ;
    // table columns for notes ;
    public  static final  String _ID = "_id" ;
    public  static final  String SUBJECT = "subject" ;
    public  static final  String DESC = "description" ;
    public  static final  String CREATE_TABLE = "create table " +
            TABLE_NAME + "(" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + SUBJECT
            +" TEXT NOT NULL, " + DESC + " TEXT);" ;


    public DBHelper( Context context) {
        super(context, "login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table users(username TEXT primary key ,password TEXT)");
        db.execSQL( CREATE_TABLE );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists users");
        db.execSQL( "drop table if exists " + TABLE_NAME  );
        onCreate( db ) ;
    }

    public Boolean insert( String username , String password  ) {
        SQLiteDatabase db = this.getWritableDatabase() ;
        ContentValues values = new ContentValues();

        values.put( "username" , username );
        values.put("password" , password);

        long res = db.insert( "users" , null , values ) ;
        if( res == -1 ) return false ;
        else return true ;
    }

    public Boolean checkusername( String username ){
        SQLiteDatabase db = this.getWritableDatabase() ;
        Cursor cursor = db.rawQuery("select * from users where username = ? " , new String[]{ username }) ;
        if ( cursor.getCount() > 0 )
            return true ;
        else return false ;
    }

    public  Boolean checkusernameANDpassword( String username , String password ){
        SQLiteDatabase db = this.getWritableDatabase() ;
        Cursor cursor = db.rawQuery("select * from users where username = ? and password = ? " , new String[]{ username , password }) ;
        if ( cursor.getCount() > 0 )
            return true ;
        else return false ;
    }






}
