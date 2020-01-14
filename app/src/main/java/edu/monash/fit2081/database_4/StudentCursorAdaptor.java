package edu.monash.fit2081.database_4;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;

import edu.monash.fit2081.database_4.provider.SchemeClass;

public class StudentCursorAdaptor extends CursorAdapter {
    private Context myContext;

    StudentCursorAdaptor(Context context, Cursor c) {
        super(context, c, 0);
        myContext = context;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.student_row, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView studentNameView = view.findViewById(R.id.student_name_row);

        TextView studentAgeView = view.findViewById(R.id.student_age_row);
        TextView studentUnitView = view.findViewById(R.id.student_unit_row);
        final TextView studentIdView = view.findViewById(R.id.student_id_row);

        final String studentNameValue = cursor.getString(cursor.getColumnIndexOrThrow(SchemeClass.Student.STUDENT_NAME));
        final int studentAgeValue = cursor.getInt(cursor.getColumnIndexOrThrow(SchemeClass.Student.STUDENT_AGE));
        final String studentUnitValue = cursor.getString(cursor.getColumnIndexOrThrow(SchemeClass.Student.STUDENT_UNIT));
        final int StudentIdValue = cursor.getInt(cursor.getColumnIndexOrThrow(SchemeClass.Student.ID));

        studentNameView.setText(studentNameValue);
        studentAgeView.setText(String.valueOf(studentAgeValue));
        studentUnitView.setText(studentUnitValue);
        studentIdView.setText(String.valueOf(StudentIdValue));


        Button removeBtn = view.findViewById(R.id.btnRemove);
        removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteStudent(StudentIdValue);
            }
        });


    }

    private void deleteStudent(int id) {
        ContentResolver resolver = myContext.getApplicationContext().getContentResolver();
        resolver.delete(SchemeClass.Student.CONTENT_URI, SchemeClass.Student.ID + " = ? ",
                        new String[]{String.valueOf(id)});

    }
}
