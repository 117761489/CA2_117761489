package com.example.ca2_117761489;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    //code adapted from https://www.youtube.com/watch?v=hDSVInZ2JCs

    //Constraints to minimise typos and
    public static final String STAFF_TABLE = "STAFF_TABLE";
    public static final String COLUMN_STAFF_NAME = "STAFF_NAME";
    public static final String COLUMN_STAFF_AGE = "STAFF_AGE";
    public static final String COLUMN_CURRENT_STAFF = "CURRENT_STAFF";
    public static final String COLUMN_ID = "ID";


    public DatabaseHelper(@Nullable Context context) {
        super(context, "staff.db", null, 1);
    }

    //This is called the first time the database is accessed.
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + STAFF_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_STAFF_NAME + " TEXT, " + COLUMN_STAFF_AGE + " INT, " + COLUMN_CURRENT_STAFF + " BOOL)";


        db.execSQL(createTableStatement);
    }

    //Called when the version no. of the table is changed
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(StaffModel staffModel){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_STAFF_NAME, staffModel.getName());
        cv.put(COLUMN_STAFF_AGE, staffModel.getAge());
        cv.put(COLUMN_CURRENT_STAFF, staffModel.isCurrent());

        long insert = db.insert(STAFF_TABLE, null, cv);
        return insert != -1;

    }

    public boolean deleteOne(StaffModel staffModel){
        //Find Student model in the database and delete and return true.
        //If not found, return false

        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + STAFF_TABLE + " WHERE " +  COLUMN_ID + " = " + staffModel.getId();

        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()){
            return true;
        }
        else
            return true;
    }

    public boolean updateOne(StaffModel staffModel) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_STAFF_NAME, staffModel.getName());
        cv.put(COLUMN_STAFF_AGE, staffModel.getAge());
        cv.put(COLUMN_CURRENT_STAFF, staffModel.isCurrent());
        cv.put("ID", staffModel.getId());

        long update = db.update(STAFF_TABLE,cv,"ID =" + staffModel.getId(),null);
        if(update == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    public List<StaffModel> getEveryone() {
        List<StaffModel> returnList = new ArrayList<>();

        //Getting data from the database
        String queryString = "SELECT * FROM " + STAFF_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            //loop through the results and create customer objects. Add to return list
            do{
                int staffID = cursor.getInt(0);
                String staffName = cursor.getString(1);
                int staffAge = cursor.getInt(2);
                boolean staffActive = cursor.getInt(3) == 1;

                StaffModel newStaff = new StaffModel(staffID, staffName, staffAge, staffActive);
                returnList.add(newStaff);
            }
            while(cursor.moveToNext());

        }
        else{
            // Failure, do not add anything to the list
        }
        //close cursor and db when finished with it
        cursor.close();
        db.close();

        return returnList;
    }
}
