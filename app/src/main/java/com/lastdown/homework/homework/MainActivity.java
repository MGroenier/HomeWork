package com.lastdown.homework.homework;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 1234;
    private ListView listView;
    private TextView emptyTextView;
    private List<Cursor> assignmentList;
    private AssignmentAdapter assignmentAdapter;
    private DataSource datasource;
    private SimpleCursorAdapter simpleCursorAdapter;

    public static final String EXTRA_ASSIGNMENT_ID = "extraAssignmentId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = (ListView) findViewById(R.id.main_list);
        emptyTextView = (TextView) findViewById(R.id.main_list_empty);

        registerForContextMenu(listView);

        listView.setEmptyView(emptyTextView);
        datasource = new DataSource(this);

        //String[] columns = new String[] { MySQLiteHelper.COLUMN_ASSIGNMENT };
        //int[] to = new int[] { R.id.text_view_assignment_title };
        //simpleCursorAdapter = new SimpleCursorAdapter(this, R.layout.list_item_assignment, datasource.getAllAssignmentsCursor(), columns, to, 0);

        String[] columns = new String[] { MySQLiteHelper.COLUMN_COURSE, MySQLiteHelper.COLUMN_ASSIGNMENT };

        int[] to = new int[] { R.id.list_item_1, R.id.list_item_2 };

        simpleCursorAdapter = new SimpleCursorAdapter(this, R.layout.list_item, datasource.getAllAssignmentsCursor(), columns, to, 0);

        listView.setAdapter(simpleCursorAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

                Intent intent = new Intent(MainActivity.this, AddAssignmentActivity.class);
                startActivityForResult(intent, 1);

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                intent.putExtra(EXTRA_ASSIGNMENT_ID, simpleCursorAdapter.getItemId(position));
                startActivityForResult(intent, 2);

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

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                simpleCursorAdapter.changeCursor(datasource.getAllAssignmentsCursor());
                updateAssignmentListView();
            }
        }
        if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                updateAssignmentListView();
            }
        }
    }

    @Override

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Select The Action");
        menu.add(0, v.getId(), 0, "Delete");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo itemInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        if (item.getTitle() == "Delete") {
            Toast.makeText(getApplicationContext(), "Assignment deleted", Toast.LENGTH_LONG).show();
            datasource.deleteAssignment(simpleCursorAdapter.getItemId(itemInfo.position));
            simpleCursorAdapter.changeCursor(datasource.getAllAssignmentsCursor());
        } else {
            return false;
        }
        return true;
    }

    public void updateAssignmentListView() {

//        assignmentList = datasource.getAllAssignments();
//        assignmentAdapter = new AssignmentAdapter(this, R.layout.list_item_assignment, assignmentList);

        String[] columns = new String[] { MySQLiteHelper.COLUMN_COURSE, MySQLiteHelper.COLUMN_ASSIGNMENT };
        int[] to = new int[] { R.id.list_item_1, R.id.list_item_2 };
        simpleCursorAdapter = new SimpleCursorAdapter(this, R.layout.list_item, datasource.getAllAssignmentsCursor(), columns, to, 0);
        listView.setAdapter(simpleCursorAdapter);
        simpleCursorAdapter.notifyDataSetChanged();

    }

}
