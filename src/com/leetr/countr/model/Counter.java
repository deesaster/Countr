package com.leetr.countr.model;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import com.leetr.countr.provider.CounterProvider;

public class Counter {
    public static final String EXTRA_ACTION = "com.leetr.countr.model.Counter.EXTRA_ACTION";
    public static final String EXTRA_ID = "com.leetr.countr.model.Counter.EXTRA_ID";
    public static final int ACTION_VIEW = 1;
    public static final int ACTION_EDIT = 2;
    public static final int ACTION_DELETE = 3;

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
        cursor.moveToFirst();
        Counter counter = new Counter(cursor.getLong(0), cursor.getString(1));
        cursor.close();
        return counter;
    }

    public static Counter withId(Context context, long id) {
        Uri uri = Uri.withAppendedPath(CounterProvider.CounterMetaData.CONTENT_URI, String.valueOf(id));

        ContentResolver cr = context.getContentResolver();
        Cursor c = cr.query(uri, null, null, null, null);

        return counterFromCursorRow(c);
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
