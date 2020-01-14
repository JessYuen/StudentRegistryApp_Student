package edu.monash.fit2081.database_4.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

public class StudentsProvider extends ContentProvider {

    private static final int STUDENTS = 100;
    private static final int STUDENTS_ID = 200;
    private StudentsDbHelper mDbHelper;


    @Override
    public boolean onCreate() {
        // Your code here
        return true; //should return true if Content Provider successfully created
    }


    //*not used in this app
    @Override
    public String getType(Uri uri) {
        // Your code here
        return "";

    }


    //
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        // Your code here
        return null;
    }


    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // Your code here
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Your code here
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        // Your code here
        return 0;
    }
}
