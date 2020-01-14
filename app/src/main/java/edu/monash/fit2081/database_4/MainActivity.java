package edu.monash.fit2081.database_4;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    AddNewStudent addNewStudent;
    ListStudents listStudents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int width, height;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        height = size.y;

        //now let the top FrameLayout's RelativeLayout parent size it
        FrameLayout frame = (FrameLayout) findViewById(R.id.fragment_top);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(width, (int) (height * 0.4));
        frame.setLayoutParams(lp);

        addNewStudent = new AddNewStudent();
        listStudents = new ListStudents();

        getSupportFragmentManager().beginTransaction()
                                   .add(R.id.fragment_top, addNewStudent, "newStudent")
                                   .addToBackStack("newStudent")
                                   .commit();

        getSupportFragmentManager().beginTransaction()
                                   .add(R.id.fragment_bottom, listStudents, "studentList")
                                   .addToBackStack("studentList")
                                   .commit();


    }
}
