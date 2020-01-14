package edu.monash.fit2081.database_4;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import edu.monash.fit2081.database_4.provider.SchemeClass;

public class AddNewStudent extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

      final   View v = inflater.inflate(R.layout.new_student_layout, container, false);

        Button addStudentBtn = v.findViewById(R.id.add_student_btn);
        addStudentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newStudent(v);
            }
        });
        return v;
    }

    private void newStudent(View v) {
        ContentResolver resolver = getActivity().getApplicationContext().getContentResolver();

        final String studentNameValue = ((EditText) v.findViewById(R.id.student_name)).getText().toString();
        final String studentAgeValue = ((EditText) v.findViewById(R.id.student_age)).getText().toString();
        final String studentUnitValue = ((EditText) v.findViewById(R.id.student_unit)).getText().toString();

        ContentValues contentValues = new ContentValues();
        contentValues.put(SchemeClass.Student.STUDENT_NAME, studentNameValue);
        contentValues.put(SchemeClass.Student.STUDENT_AGE, studentAgeValue);
        contentValues.put(SchemeClass.Student.STUDENT_UNIT, studentUnitValue);
        resolver.insert(SchemeClass.Student.CONTENT_URI, contentValues);


    }
}
