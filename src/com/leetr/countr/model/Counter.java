package com.leetr.countr.model;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class Counter {
//    public static final String TABLE_NAME = "counter";
//
//    public static final String KEY_ID     = "_id";
//    public static final String KEY_TITLE  = "title";
//    public static final String KEY_COUNT  = "count";

    public static long add(SQLiteDatabase db, String title) {
//        ContentValues initialValues = new ContentValues();
//        initialValues.put(KEY_TITLE, title);
//
////        SQLiteDatabase db = dbHelper.getDB();
//        return db.insert(TABLE_NAME, null, initialValues);
        return 0;
    }

    public static boolean delete(SQLiteDatabase db, long rowId) {
//        SQLiteDatabase db = dbHelper.getDB();
//
//     return db.delete(TABLE_NAME, KEY_ID + "=" + rowId, null) > 0;
        return false;
    }


    public static boolean update(SQLiteDatabase db, long rowId, String title) {
//        ContentValues args = new ContentValues();
//        args.put(KEY_TITLE, title);
//        args.put(KEY_COUNT, 0);
//
////        SQLiteDatabase db = dbHelper.getDB();
//        return db.update(TABLE_NAME, args, KEY_ID + "=" + rowId, null) > 0;
        return false;
    }

    public static Cursor all(Context context) {
//        LeetrDbHelper dbHelper = new LeetrDbHelper(context);
//
//        SQLiteDatabase db = dbHelper.openDb();
//        return db.query(TABLE_NAME,
//                new String[]{KEY_ID, KEY_TITLE, KEY_COUNT}, null, null,
//                null, null, null);
        return null;
    }

    public static Counter getById(SQLiteDatabase db, long rowId)
            throws SQLException {
//        SQLiteDatabase db = dbHelper.getDB();
//        Cursor cursor = db.query(TABLE_NAME, new String[] { KEY_ID, KEY_TITLE,
//                KEY_COUNT }, KEY_ID + "=" + rowId, null, null, null, null);
//
//        if (cursor != null) {
//            cursor.moveToFirst();
//        }
//
//        if (cursor.getCount() > 0) {
//            return counterFromDbRecord(cursor);
//        }
        return null;
    }

    public static Counter counterFromCursorRow(Cursor cursor) {
        return new Counter(cursor.getLong(0), cursor.getString(1));
    }

    private long mId;
    private String mTitle;

    public Counter(long id, String title) {
        mId = id;
        mTitle = title;
    }

    public long getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public boolean save(SQLiteDatabase db) {
        boolean result = false;

        if (mId <= 0) {// new counter, needs to be inserted into db
            mId = Counter.add(db, getTitle());

            if (mId > -1) {
                result = true;
            }

        } else { // existent counter, needs to be updated
            result = Counter.update(db, getId(), getTitle());
        }

        return result;
    }
}
