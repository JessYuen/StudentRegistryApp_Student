package edu.monash.fit2081.database_4;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import edu.monash.fit2081.database_4.provider.SchemeClass;

public class ListStudents extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    LayoutInflater myInflater = null;
    ListView studentsListView;
    StudentCursorAdaptor studentCursorAdaptor;

    public ListStudents() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoaderManager.getInstance(this).initLoader(0,null,this);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        myInflater = inflater;
        View v = inflater.inflate(R.layout.student_list, container, false);
        studentsListView = (ListView) v.findViewById(R.id.student_listview);
        studentsListView.addHeaderView(myInflater.inflate(R.layout.student_list_header, null));


        studentCursorAdaptor = new StudentCursorAdaptor(getContext(), null); // null is an empty cursor
        studentsListView.setAdapter(studentCursorAdaptor);
        return v;

    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        CursorLoader cursorLoader = new CursorLoader(getActivity(),
                                                     SchemeClass.Student.CONTENT_URI,
                                                     SchemeClass.Student.PROJECTION,
                                                     null,
                                                     null,
                                                     null
        );
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        studentCursorAdaptor.swapCursor(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        super.onResume();
    }

}
