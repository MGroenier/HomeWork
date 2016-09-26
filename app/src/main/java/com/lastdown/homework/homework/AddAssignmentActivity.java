package com.lastdown.homework.homework;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;

public class AddAssignmentActivity extends AppCompatActivity {

    private EditText editText;
    private DataSource datasource;
    private Spinner courseSpinner;
    private SimpleCursorAdapter simpleCursorAdapter;
    private ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assignment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        datasource = new DataSource(this);
        editText = (EditText) findViewById(R.id.add_assignment_assignment_edittext);
        courseSpinner = (Spinner) findViewById(R.id.add_assignment_course_spinner);
        courseSpinner.setEmptyView(findViewById(R.id.add_assignment_course_spinner_empty));
        imageButton = (ImageButton) findViewById(R.id.add_assignment_addcourse_btn);

        String[] columns = new String[]  { MySQLiteHelper.COLUMN_COURSE };

        int[] to = new int[] { android.R.id.text1 };

        simpleCursorAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_spinner_dropdown_item, datasource.getAllCoursesCursor(), columns, to, 0);

        courseSpinner.setAdapter(simpleCursorAdapter);

        imageButton.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(AddAssignmentActivity.this);

                builder.setTitle("Add Course");

                builder.setMessage("Add your course here");

                final EditText editText = new EditText(AddAssignmentActivity.this);

                builder.setView(editText);

                builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {

                    @Override

                    public void onClick(DialogInterface dialog, int which) {

                        datasource.createCourse(editText.getText().toString());

                        simpleCursorAdapter.changeCursor(datasource.getAllCoursesCursor());

                    }

                });

                builder.setNegativeButton("Cancel", null);

                builder.show();

            }

        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

                long assignmentId = datasource.createAssignment(editText.getText().toString(), simpleCursorAdapter.getItemId(courseSpinner.getSelectedItemPosition()));
                Intent resultIntent = new Intent();
                resultIntent.putExtra(MainActivity.EXTRA_ASSIGNMENT_ID, assignmentId);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();

            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
