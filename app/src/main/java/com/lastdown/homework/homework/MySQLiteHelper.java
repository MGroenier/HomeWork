package com.lastdown.homework.homework;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Martijn on 22/09/2016.
 */

public class MySQLiteHelper extends SQLiteOpenHelper{

    // Database info
    private static final String DATABASE_NAME = "myhomeworktutorial.db";
    private static final int DATABASE_VERSION = 2;

    // Courses
    public static final String TABLE_COURSES = "courses";
    public static final String COLUMN_COURSE_ID = "course_id";
    public static final String COLUMN_COURSE = "course";

    // Assignments
    public static final String TABLE_ASSIGNMENTS = "assignments";
    public static final String COLUMN_ASSIGNMENT_ID = "assignment_id";
    public static final String COLUMN_ASSIGNMENT = "assignment";

    // Creating the table
    private static final String DATABASE_CREATE_ASSIGNMENTS =
            "CREATE TABLE " + TABLE_ASSIGNMENTS +
                    "(" +
                    COLUMN_ASSIGNMENT_ID + " integer primary key autoincrement, " +
                    COLUMN_ASSIGNMENT + " text not null, " +
                    COLUMN_COURSE_ID + " integer, " +
                    "FOREIGN KEY(" + COLUMN_COURSE_ID + ") REFERENCES " + TABLE_COURSES + "(" + COLUMN_COURSE_ID + ") ON DELETE CASCADE" +
                    ");";

    private static final String DATABASE_CREATE_COURSES =
            "CREATE TABLE " + TABLE_COURSES +
                    "(" +
                    COLUMN_COURSE_ID + " integer primary key autoincrement, " +
                    COLUMN_COURSE + " text not null" +
                    ");";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Execute the sql to create the table assignments
        db.execSQL(DATABASE_CREATE_ASSIGNMENTS);
        db.execSQL(DATABASE_CREATE_COURSES);
    }

    @Override

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // When the database gets upgraded you should handle the update to make sure there is no data loss.

        // This is the default code you put in the upgrade method, to delete the table and call the oncreate again.

        if(oldVersion == 1) {

            db.execSQL("DROP TABLE IF EXISTS " + TABLE_ASSIGNMENTS);

            onCreate(db);

        }

    }

}
