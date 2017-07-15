package com.example.android.habittrackerdatabasestructure.data;

import android.provider.BaseColumns;

/**
 * Created by sr on 15.07.17.
 */

public class HabitContract {
    // Constructor
    // Left blank und set to private on purpose because no one should ever create an instance of HabitContract
    private HabitContract(){ }

    // Inner class where public variables for the column names and types are declared and initialised
    // so they can be accessed from everywhere else in the app

    public static abstract class HabitEntry implements BaseColumns {

        // Constants

        public static final String TABLE_NAME = "habits";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_HABIT_DATE = "date";
        public static final String COLUMN_HABIT_NAME = "habit";
        public static final String COLUMN_HABIT_KIND = "kind";
        public static final String COLUMN_HABIT_DURATION = "duration";

        // Possible values for habit kinds

        public static final int SPORT_HABIT = 0;
        public static final int SELFCARE_HABIT = 1;
        public static final int HOBBY_HABIT = 2;
        public static final int FOOD_HABIT = 3;
        public static final int WORK_HABIT = 4;

    }
}
