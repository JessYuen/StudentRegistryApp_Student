package edu.monash.fit2081.database_4.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StudentsDbHelper extends SQLiteOpenHelper {

    private final static String DB_NAME = "studentsdb2.db";
    private final static int DB_VERSION = 1;

    private final static String STUDENT_TABLE_CREATE =
            "CREATE TABLE " +
            SchemeClass.Student.TABLE_NAME + " (" +
            SchemeClass.Student.ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
            SchemeClass.Student.STUDENT_NAME + " TEXT, " +
            SchemeClass.Student.STUDENT_UNIT + " TEXT," +
            SchemeClass.Student.STUDENT_AGE + " INTEGER);";


    public StudentsDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(STUDENT_TABLE_CREATE);
    }

    //couldn't afford to be this drastic in the real world
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + SchemeClass.Student.TABLE_NAME);
        onCreate(db);
    }
}
