package com.deesastudio.countr.models;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.deesastudio.countr.DbHelper;

public class CounterItem {
    public static final String TABLE_NAME     = "counter_items";

    public static final String KEY_ID         = "_id";
    public static final String KEY_COUNTER_ID = "counter_id";
    public static final String KEY_VALUE      = "value";
    public static final String KEY_TIMESTAMP  = "tstamp";
    public static final String KEY_LATITUDE   = "latitude";
    public static final String KEY_LONGITUDE  = "longitude";

    public static long add(DbHelper dbHelper, long counterId, float value,
            long latitude, long longitude) {

        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_COUNTER_ID, counterId);
        initialValues.put(KEY_VALUE, value);
        initialValues.put(KEY_LATITUDE, latitude);
        initialValues.put(KEY_LONGITUDE, longitude);

        SQLiteDatabase db = dbHelper.getDB();

        return db.insert(TABLE_NAME, null, initialValues);
    }

    public static boolean delete(DbHelper dbHelper, long itemId) {
        SQLiteDatabase db = dbHelper.getDB();

        return db.delete(TABLE_NAME, KEY_ID + "=" + itemId, null) > 0;
    }

    public static Cursor itemsForCounter(DbHelper dbHelper, long counterId) {
        SQLiteDatabase db = dbHelper.getDB();

        return db.query(TABLE_NAME, new String[] { KEY_ID, KEY_VALUE,
                KEY_TIMESTAMP, KEY_LATITUDE, KEY_LONGITUDE }, KEY_COUNTER_ID
                + "=" + counterId, null, null, null, KEY_TIMESTAMP + " DESC");
    }
}
