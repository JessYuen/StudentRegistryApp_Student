package edu.monash.fit2081.database_4.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.transition.Scene;

public class StudentsProvider extends ContentProvider {

    private static final int STUDENTS = 100;
    private static final int STUDENTS_ID = 200;
    private StudentsDbHelper mDbHelper;
    private static final UriMatcher sUriMatcher = createUriMatcher();

    private static UriMatcher createUriMatcher() {
        final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = SchemeClass.CONTENT_AUTHORITY;

        // sUriMatcher will return code 100 if uri is like authority/students
        uriMatcher.addURI(authority, SchemeClass.Student.PATH_VERSION, STUDENTS);

        // sUriMatcher will return code 200 if uri is like e.g. authority/students/7
        // (where 7 is id of row in students table)
        uriMatcher.addURI(authority, SchemeClass.Student.PATH_VERSION + "/#", STUDENTS_ID);

        return uriMatcher;
    }


    @Override
    public boolean onCreate() {
        // Your code here
        mDbHelper = new StudentsDbHelper(getContext());

        return true; //should return true if Content Provider successfully created
    }


    //*not used in this app
    @Override
    public String getType(Uri uri) {
        // Your code here
        switch ((sUriMatcher.match(uri))) {
            case STUDENTS:
                return SchemeClass.Student.CONTENT_TYPE;
            case STUDENTS_ID:
                return SchemeClass.Student.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalArgumentException("Uknown URI: " + uri);
        }
    }

    //
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        // Your code here

        // Use SQLitedQueryBuilder for querying db
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        // Set the table name
        queryBuilder.setTables(SchemeClass.Student.TABLE_NAME);

        // Record id
        String id;

        // Match Uri pattern
        int uriType = sUriMatcher.match(uri);

        switch (uriType) {
            case STUDENTS: // no trailing row id
                break;
            case STUDENTS_ID: // trailing row id
                selection = SchemeClass.Student.ID + " - ? ";
                id = uri.getLastPathSegment();
                selectionArgs = new String[] {id};
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        Cursor cursor = queryBuilder.query(
                db,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder);

        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }


    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // Your code here
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        int uriType = sUriMatcher.match(uri);
        long rowId;

        switch (uriType) {
            case STUDENTS:
                rowId = db.insertOrThrow(SchemeClass.Student.TABLE_NAME, null, values);
                getContext().getContentResolver().notifyChange(uri, null);
                return ContentUris.withAppendedId(SchemeClass.Student.CONTENT_URI, rowId);
            case STUDENTS_ID:
                throw new IllegalArgumentException("Invalid URI: " + uri);
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Your code here
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        int uriType = sUriMatcher.match(uri);
        int deletionCount = 0;

        switch (uriType) {
            case STUDENTS:
                deletionCount = db.delete(SchemeClass.Student.TABLE_NAME, selection, selectionArgs);
                break;
            case STUDENTS_ID:
                String id = uri.getLastPathSegment();
                deletionCount = db.delete(
                        SchemeClass.Student.TABLE_NAME,
                        SchemeClass.Student.ID + " = " + id + (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""),
                        selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);

        }

        return deletionCount;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        // Your code here
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        int uriType = sUriMatcher.match(uri);
        int updateCount = 0;

        switch (uriType) {
            case STUDENTS: // no trailing row id = more than 1 row may be selected
                updateCount = db.update(SchemeClass.Student.TABLE_NAME, values, selection, selectionArgs);
                break;
            case STUDENTS_ID: // trailing row is = only 1 row to be updated
                String id = uri.getLastPathSegment();
                updateCount = db.update(
                        SchemeClass.Student.TABLE_NAME,
                        values,
                        SchemeClass.Student.ID + " = " + id + (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""),
                        selectionArgs);
                break;
            default: // this content URI pattern is not recognised
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return updateCount;

        }
}
