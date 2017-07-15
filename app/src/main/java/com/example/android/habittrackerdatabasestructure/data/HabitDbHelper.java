package com.example.android.habittrackerdatabasestructure.data;

/**
 * Created by sr on 15.07.17.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.habittrackerdatabasestructure.data.HabitContract.HabitEntry;

public class HabitDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = HabitDbHelper.class.getSimpleName();

    // Name of the database file
    private static final String DATABASE_NAME = "habit_tracker.db";

     // Database version, must be incremented if database schema is changed
    private static final int DATABASE_VERSION = 1;

    // Constructor for a new instance of HabitDbHelper
    public HabitDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Call when the databse is first created
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the habits table
        String SQL_CREATE_HABIT_TABLE =  "CREATE TABLE " + HabitEntry.TABLE_NAME + " ("
                + HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + HabitEntry.COLUMN_HABIT_DATE + " STRING NOT NULL DEFAULT CURRENT_DATE, "
                + HabitEntry.COLUMN_HABIT_NAME + " TEXT NOT NULL, "
                + HabitEntry.COLUMN_HABIT_KIND + " INTEGER NOT NULL, "
                + HabitEntry.COLUMN_HABIT_DURATION + " INTEGER NOT NULL);";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_HABIT_TABLE);
    }

    // Called when the database needs to be updated
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // The database is still at version 1, so there's nothing to do be done here.
    }

}
