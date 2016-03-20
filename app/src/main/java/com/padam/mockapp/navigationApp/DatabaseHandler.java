package com.padam.mockapp.navigationApp;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "db_navigationapp";

    // Contacts table name
    private static final String TABLE_NAVIGATIONApp = "navigationapp";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_MODE_CAR = "car";

    private static final String KEY_MODE_TRAIN = "train";

    private static final String KEY_LOCATION_LONGITUDE = "longitude";
    private static final String KEY_LOCATION_LATITUDE = "latitude";

    public DatabaseHandler(Activity activity) {

        super(activity, DATABASE_NAME, null, DATABASE_VERSION);
    }


    public DatabaseHandler(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    public void onCreate(SQLiteDatabase db) {
        String CREATE_NAVIGATION_TABLE = "CREATE TABLE " + TABLE_NAVIGATIONApp + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT," + KEY_MODE_CAR + " TEXT," + KEY_MODE_TRAIN + " TEXT," + KEY_LOCATION_LONGITUDE + " TEXT,"
                + KEY_LOCATION_LATITUDE + " TEXT" + ")";
        db.execSQL(CREATE_NAVIGATION_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAVIGATIONApp);

        // Create tables again
        onCreate(db);
    }

    public void addData(Beans bean) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, bean.getName()); // Contact Name
        values.put(KEY_MODE_CAR, bean.getMode_car());
        values.put(KEY_MODE_TRAIN, bean.getMode_train());
        values.put(KEY_LOCATION_LATITUDE, bean.getLocation_latitude());
        values.put(KEY_LOCATION_LONGITUDE, bean.getLocation_longitude());


        // Inserting Row
        db.insert(TABLE_NAVIGATIONApp, null, values);
        db.close(); // Closing database connection
    }


    public Beans getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_NAVIGATIONApp + " WHERE " + KEY_ID + " = " + id;

        Cursor c = db.rawQuery(selectQuery, null);


        if (c != null)
            c.moveToFirst();

        Beans b = new Beans();
        b.setId(c.getInt(c.getColumnIndex(KEY_ID)));//KEY_ID key for fetching id
        b.setName(c.getString(1));//KEY_BREAKFAST key for fetching isBreakfast
        b.setMode_car(c.getString(2));
        b.setMode_train(c.getString(3));//KEY_BREAKFAST key for fetching isBreakfast
        b.setLocation_latitude(c.getString(4));
        b.setLocation_longitude(c.getString(5));


        return b;
    }


       /* Cursor cursor = db.query(TABLE_NAVIGATIONApp, new String[]{KEY_ID,
                        KEY_NAME, KEY_MODE_CAR, KEY_MODE_TRAIN ,KEY_LOCATION_LATITUDE, KEY_LOCATION_LONGITUDE}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Beans data = new Beans(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));
        // return contact
        return data;
    }*/

    // Getting All Contacts
    public List<Beans> getAllData() {
        List<Beans> DataList = new ArrayList<Beans>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAVIGATIONApp;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Beans bean = new Beans();
                bean.setId(Integer.parseInt(cursor.getString(0)));
                bean.setName(cursor.getString(1));
                bean.setMode_car(cursor.getString(2));
                bean.setMode_train(cursor.getString(3));
                bean.setLocation_longitude(cursor.getString(4));
                bean.setLocation_latitude(cursor.getString(5));
                // Adding contact to list
                DataList.add(bean);
            } while (cursor.moveToNext());
        }


        return DataList;
    }


}