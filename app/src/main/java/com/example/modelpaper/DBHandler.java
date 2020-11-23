package com.example.modelpaper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {

    public DBHandler(@Nullable Context context) {
        super(context, "DBHandlerDBHandler.db", null, 1);
    }

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + UserProfile.Users.TABLE_NAME + " (" +
                    UserProfile.Users._ID + " INTEGER PRIMARY KEY," +
                    UserProfile.Users.COLUMN_USER_NAME + " TEXT," +
                    UserProfile.Users.COLUMN_DATE_OF_BIRTH + " TEXT," +
                    UserProfile.Users.COLUMN_PASSWORD + " TEXT," +
                    UserProfile.Users.COLUMN_GENDER + " TEXT)";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long addInfo(String UserName, String DateOfBirth,String Password, String Gender){
        SQLiteDatabase db = getWritableDatabase();

    // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put( UserProfile.Users.COLUMN_USER_NAME, UserName);
        values.put(UserProfile.Users.COLUMN_DATE_OF_BIRTH, DateOfBirth);
        values.put(UserProfile.Users.COLUMN_PASSWORD, Password);
        values.put(UserProfile.Users.COLUMN_GENDER, Gender);

    // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(UserProfile.Users.TABLE_NAME, null, values);
        return newRowId;
    }

    public boolean updateInfor(int id, String UserName, String DateOfBirth,String Password, String Gender){
        SQLiteDatabase db = getWritableDatabase();

    // New value for one column
        ContentValues values = new ContentValues();
        values.put( UserProfile.Users.COLUMN_USER_NAME, UserName);
        values.put(UserProfile.Users.COLUMN_DATE_OF_BIRTH, DateOfBirth);
        values.put(UserProfile.Users.COLUMN_PASSWORD, Password);
        values.put(UserProfile.Users.COLUMN_GENDER, Gender);

    // Which row to update, based on the title
        String selection = UserProfile.Users._ID + " LIKE ?";
        String[] selectionArgs = { String.valueOf(id) };

        int count = db.update(
                UserProfile.Users.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return true;
    }

    public void readAllInfo(){
        SQLiteDatabase db = getReadableDatabase();

    // Define a projection that specifies which columns from the database
    // you will actually use after this query.
        String[] projection = {
                UserProfile.Users._ID,
                UserProfile.Users.COLUMN_USER_NAME,
                UserProfile.Users.COLUMN_DATE_OF_BIRTH,
                UserProfile.Users.COLUMN_PASSWORD,
                UserProfile.Users.COLUMN_GENDER
        };

    // Filter results WHERE "title" = 'My Title'


    // How you want the results sorted in the resulting Cursor
        String sortOrder =
                UserProfile.Users._ID + " DESC";

        Cursor cursor = db.query(
                UserProfile.Users.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        List itemIds = new ArrayList<>();
        while(cursor.moveToNext()) {
            int itemId = cursor.getInt(cursor.getColumnIndexOrThrow(UserProfile.Users._ID));
            itemIds.add(itemId);
        }
        cursor.close();
    }

    public List readAllInfo(String userName){
        SQLiteDatabase db = getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                UserProfile.Users._ID,
                UserProfile.Users.COLUMN_USER_NAME,
                UserProfile.Users.COLUMN_DATE_OF_BIRTH,
                UserProfile.Users.COLUMN_PASSWORD,
                UserProfile.Users.COLUMN_GENDER
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = UserProfile.Users.COLUMN_USER_NAME + " = ?";
        String[] selectionArgs = { userName };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                UserProfile.Users._ID + " DESC";

        Cursor cursor = db.query(
                UserProfile.Users.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        List item = new ArrayList<>();
        while(cursor.moveToNext()) {
            int itemId = cursor.getInt(cursor.getColumnIndexOrThrow(UserProfile.Users._ID));
            String itemName = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLUMN_USER_NAME));
            String itemDOB = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLUMN_DATE_OF_BIRTH));
            String itemPassword = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLUMN_PASSWORD));
            String itemGender = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLUMN_GENDER));
            item.add(itemId);
            item.add(itemName);
            item.add(itemDOB);
            item.add(itemPassword);
            item.add(itemGender);
        }

        cursor.close();
        return item;
    }

    public boolean deleteInfo(String name){
        SQLiteDatabase db = getReadableDatabase();
        // Define 'where' part of query.
        String selection = UserProfile.Users.COLUMN_USER_NAME + " LIKE ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = { name };
        // Issue SQL statement.
        int deletedRows = db.delete(UserProfile.Users.TABLE_NAME, selection, selectionArgs);
        if(deletedRows<=0){
            return false;
        }else {
            return true;
        }

    }

}
