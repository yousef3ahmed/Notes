package com.example.notes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {
    private DBHelper dpHelper ;
    private Context context ;
    private SQLiteDatabase database ;

    // constructer
    public DBManager( Context c  ){
        context = c ;
    }

    public  DBManager open( ) throws SQLException {
        dpHelper = new DBHelper( context ) ;
        database = dpHelper.getWritableDatabase() ;
        return  this ;
    }

    public  void close( ){
        dpHelper.close();
    }

    public void insert( String name , String desc , int key_ ){
        ContentValues contentValues = new ContentValues(  );
        contentValues.put(DBHelper.SUBJECT , name);
        contentValues.put(DBHelper.DESC , desc);
        contentValues.put( DBHelper.KEY ,key_  );

        database.insert(DBHelper.TABLE_NAME , null , contentValues);
    }

    public  Cursor fetch( int _id ){
        String[] colums = new String[]{ DBHelper._ID , DBHelper.SUBJECT , DBHelper.DESC , DBHelper.KEY } ;
        Cursor cursor = database.query( DBHelper.TABLE_NAME ,
                colums,
                DBHelper.KEY +" = "+ _id ,
                null ,
                null ,
                null ,
                null);

        if( cursor != null )
            cursor.moveToFirst() ;

        return  cursor ;
    }


    public int update( long _id , String name  ,  String desc ){
        ContentValues contentValue = new ContentValues( );
        contentValue.put( DBHelper.SUBJECT , name );
        contentValue.put(DBHelper.DESC , desc);

        int res = database.update(DBHelper.TABLE_NAME,
                contentValue ,
                DBHelper._ID + " = " + _id , null) ;
        return  res ;
    }

    public void delet( long _id ){
        database.delete( DBHelper.TABLE_NAME , DBHelper._ID + " = " + _id , null ) ;
    }

}
