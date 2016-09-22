package com.lastdown.homework.homework;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 1234;
    private ListView listView;
    private TextView emptyTextView;
    private List<Assignment> assignmentList;
    private AssignmentAdapter assignmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = (ListView) findViewById(R.id.main_list);
        emptyTextView = (TextView) findViewById(R.id.main_list_empty);

        listView.setEmptyView(emptyTextView);

        assignmentList = new ArrayList<Assignment>();
        assignmentAdapter = new AssignmentAdapter(this,R.layout.list_item_assignment,assignmentList);
        listView.setAdapter(assignmentAdapter);

        assignmentList.add(new Assignment("Create this application"));

        assignmentAdapter.notifyDataSetChanged();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

                Intent intent = new Intent(MainActivity.this, AddAssignmentActivity.class);
                startActivityForResult(intent, REQUEST_CODE);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Check if the result code is the right one
        if (resultCode == Activity.RESULT_OK) {

            //Check if the request code is correct
            if (requestCode == 1234) {

                Assignment assignment = (Assignment) data.getSerializableExtra("assignment");
                // now, do something with the received Assignment-object.

                assignmentList.add(assignment);
                assignmentAdapter.notifyDataSetChanged();

            }

        }

    }
}
