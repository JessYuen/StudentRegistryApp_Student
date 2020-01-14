package edu.monash.fit2081.database_4.provider;

import android.net.Uri;

public class SchemeClass {
    //Android symbolic name of the Content Provider (i.e. its "authority)), use package name by convention so that it's unique on the device
    public static final String CONTENT_AUTHORITY = "edu.monash.fit2081.db.provider.studentapp";

    //Content URIs will use the following as their base
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final class Student {
        //building a content URI for the students table
        //A path that points to the students table
        public static final String PATH_VERSION = "students";
        // Content Uri = Content Authority + Path (students in this case)
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_VERSION).build();

        //*not used in this app
        // Use MIME type prefix android.cursor.dir/ for returning multiple items  //*not used in this app
        public static final String CONTENT_TYPE =
                "vnd.android.cursor.dir/edu.monash.fit2081.db.provider";
        // Use MIME type prefix android.cursor.item/ for returning a single item //*not used in this app
        public static final String CONTENT_ITEM_TYPE =
                "vnd.android.cursor.item/edu.monash.fit2081.db.provider";

        //Table name
        public static final String TABLE_NAME = "students";

        //Table Column names
        public static final String ID = "_id"; //CursorAdapters will not work if this column with this name is not present
        public static final String STUDENT_NAME = "student_name";
        public static final String STUDENT_AGE = "student_age";
        public static final String STUDENT_UNIT = "student_unit";


        public Student() {
        }

        public static final String[] PROJECTION = new String[]{
                Student.ID,
                Student.STUDENT_NAME,
                Student.STUDENT_AGE,
                Student.STUDENT_UNIT
        };
    }

}
