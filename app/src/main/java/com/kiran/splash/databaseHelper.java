package com.kiran.splash;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;

public class databaseHelper extends SQLiteOpenHelper {
    static String name="summerproject";
    static  int version=1;
    String createownertable="CREATE TABLE if not exists `user1` (\n" +
            "\t`id`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`username`\tTEXT,\n" +
            "\t`password`\tTEXT,\n" +
            "\t`email`\tTEXT,\n" +
            "\t`address`\tTEXT,\n" +
            "\t`phone`\tTEXT,\n" +
            "\t`gender`\tTEXT,\n" +
            "\t`contact`\tINTEGER,\n" +
            "\t`hostelid`\tINTEGER\n" +
            ")";
      //database name

    String createhosteltable="CREATE TABLE if not exists `hostels` (\n" +
            "\t`id`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`hostelname`\tTEXT,\n" +
            "\t`ownername`\tTEXT,\n" +
            "\t`address`\tTEXT,\n" +
            "\t`contact`\tINTEGER,\n" +
            "\t`fees`\tINTEGER,\n" +
            "\t`room`\tINTEGER\n" +
            ")";

    ownerinfo info=new ownerinfo();
    public databaseHelper(Context context){
        super(context,name,null,version);
getWritableDatabase().execSQL(createownertable);
getWritableDatabase().execSQL(createhosteltable);
    }
    public boolean isUserAlreadyExists(String username) {
        String sql = "Select count(*) from user1 where username='" + username + "'";
        SQLiteStatement statement = getReadableDatabase().compileStatement(sql);
        long l = statement.simpleQueryForLong();
        if (l == 0) {
            return false;
        }
        return true;
    }
    public boolean isLoginSuccessful(String username,String password) {
        String sql = "Select count(*) from user1 where" +
                " username='" + username + "' and password = '"+password+"'";
        SQLiteStatement statement = getReadableDatabase().compileStatement(sql);
        long l = statement.simpleQueryForLong();
        if (l == 1) {
            return true;
        }
        return false;
    }

    public void insertUser(ContentValues ContentValues) {
        if (!isUserAlreadyExists(ContentValues.getAsString("username")))
            getWritableDatabase().insert("user1", "", ContentValues);
        getWritableDatabase ().insert ("hostels","",ContentValues);
    }

    public  boolean isLoginvalid( String username,String password){
        String sql="select count(*) from user1 where username='"+username+"'"+"and password='"+password+"'";
        SQLiteStatement sqLiteStatement=getReadableDatabase ().compileStatement (sql);
        Long L=sqLiteStatement.simpleQueryForLong ();
        if(L==1)
            return  true;
        else
            return false;


    }
    public ArrayList<ownerinfo>gethostelList(){
        String sql="select * from hostels";
        ArrayList<ownerinfo> list=new ArrayList<> ();
       Cursor cursor= getReadableDatabase ().rawQuery (sql,null);
       while (cursor.moveToNext ()){
           cursor.getString (cursor.getColumnIndex ("id"));
           ownerinfo info=new ownerinfo ();
           info.id=(cursor.getString (cursor.getColumnIndex ("id")));
           info.hostelname=(cursor.getString (cursor.getColumnIndex ("hostelname")));
           info.ownername=(cursor.getString (cursor.getColumnIndex ("ownername")));
           info.contact=(cursor.getString (cursor.getColumnIndex ("contact")));
           info.address=(cursor.getString (cursor.getColumnIndex ("address")));
           info.fees=(cursor.getString (cursor.getColumnIndex ("fees")));
           info.room=(cursor.getString (cursor.getColumnIndex ("room")));
           list.add (info);
       }
       cursor.close ();
       return list;

    } public  ownerinfo gethostelInfo(String id){

        String sql="select * from hostels where id= "+id;
        ownerinfo info=new ownerinfo ();
             Cursor cursor= getReadableDatabase ().rawQuery (sql,null);
       while (cursor.moveToNext ()){
           cursor.getString (cursor.getColumnIndex ("id"));

           info.id=(cursor.getString (cursor.getColumnIndex ("id")));
           info.hostelname=(cursor.getString (cursor.getColumnIndex ("hostelname")));
           info.ownername=(cursor.getString (cursor.getColumnIndex ("ownername")));
           info.contact=(cursor.getString (cursor.getColumnIndex ("contact")));
           info.address=(cursor.getString (cursor.getColumnIndex ("address")));
           info.fees=(cursor.getString (cursor.getColumnIndex ("fees")));
           info.room=(cursor.getString (cursor.getColumnIndex ("room")));

       }
       cursor.close ();
       return info;

    }
    public void updateUser(ContentValues contentValues, String id) {
        getWritableDatabase().update("hostels", contentValues, "id=" + id, null);
    }
    public void deleteUser(String id) {
        getWritableDatabase().delete("user1", "id=" + id, null);
    }

    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(createownertable);
        sqLiteDatabase.execSQL (createhosteltable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
