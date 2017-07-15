package com.example.android.habittrackerdatabasestructure;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.habittrackerdatabasestructure.data.HabitContract.HabitEntry;
import com.example.android.habittrackerdatabasestructure.data.HabitDbHelper;

public class MainActivity extends AppCompatActivity {
    // To access our database, we instantiate our subclass of SQLiteOpenHelper
    // and pass the context, which is the current activity.
    private HabitDbHelper dbHelper = new HabitDbHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        displayDatabaseInfo();

        // Set an onClickListener on the insert dummy button to insert dummy dates into the database
        Button insertDummy = (Button) findViewById(R.id.insert_dummy);
        insertDummy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create and/or open a database to read from it
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                // No date because default date is added anyway
                values.put(HabitEntry.COLUMN_HABIT_NAME, "Napping");
                values.put(HabitEntry.COLUMN_HABIT_KIND, HabitEntry.HOBBY_HABIT);
                values.put(HabitEntry.COLUMN_HABIT_DURATION, 45);
                // Insert values into database
                db.insert(HabitEntry.TABLE_NAME, null, values);
                displayDatabaseInfo();
            }
        });

        HabitDbHelper dbHelper = new HabitDbHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
    }

    // Method to display the table content on the screen to make testing the db functions easier
    private void displayDatabaseInfo() {
        // Create and/or open a database to read from it
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        // A HabitDbHelper is instantiated to access the database
        HabitDbHelper dbHelper = new HabitDbHelper(this);
        // Create a String that tells the Cursor which columns it should take into account
        String[] projection = {
                HabitEntry._ID,
                HabitEntry.COLUMN_HABIT_DATE,
                HabitEntry.COLUMN_HABIT_NAME,
                HabitEntry.COLUMN_HABIT_KIND,
                HabitEntry.COLUMN_HABIT_DURATION,
        };
        // Create a new Cursor object
        Cursor cursor = db.query(
                HabitEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);

        TextView displayView = (TextView) findViewById(R.id.show_table);

        try {
            displayView.setText("The habit table contains " + cursor.getCount() + " habit(s).\n\n");
            // Create a header for the table that looks like this:
            // _id - <current date> - name - kind - duration
            displayView.append(HabitEntry._ID + " - " +
                    HabitEntry.COLUMN_HABIT_DATE + " - " +
                    HabitEntry.COLUMN_HABIT_NAME + " - " +
                    HabitEntry.COLUMN_HABIT_KIND + " - " +
                    HabitEntry.COLUMN_HABIT_DURATION + "\n");

            // Get the index of each column
            int idColumnIndex = cursor.getColumnIndex(HabitEntry._ID);
            int dateColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_DATE);
            int nameColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_NAME);
            int kindColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_KIND);
            int durationColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_DURATION);


            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Extract the values of the row the cursor is currently on
                int currentID = cursor.getInt(idColumnIndex);
                String currentDate = cursor.getString(dateColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                int currentKind = cursor.getInt(kindColumnIndex);
                int currentDuration = cursor.getInt(durationColumnIndex);
                // Display those values for each row in the cursor
                displayView.append(("\n" + currentID + " - " +
                        currentName + " - " +
                        currentDate + " - " +
                        currentKind + " - " +
                        currentDuration));
            }
        } finally {
            // Close the cursor to release all its resources and make it invalid
            cursor.close();
        }
    }

}