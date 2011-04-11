package com.deesastudio.countr;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class CounterDbHelper {
    public static final String KEY_TITLE = "title";
    public static final String KEY_COUNT = "count";
    public static final String KEY_ROWID = "_id";
    
    private static final String TAG = "CounterDBAdapter";
    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;
    
    private static final String DATABASE_CREATE = 
        "create table counters(_id integer primary key autoincrement, "
        + "title text not null, count integer default(0));";
    
    private static final String DB_NAME = "counter_db";
    private static final String DB_TABLE = "counters";
    private static final int DB_VERSION = 1;
    
    private final Context mContext;
    
    CounterDbHelper(Context context) {
        mContext = context;
    }
    
    public CounterDbHelper open() throws SQLException {
        mDbHelper = new DatabaseHelper(mContext);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }
    
    public void close() {
        mDbHelper.close();
    }
    
    public long createCounter(String title) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_TITLE, title);
        
        return mDb.insert(DB_TABLE, null, initialValues);
    }
    
    public boolean deleteCounter(long rowId) {
        return mDb.delete(DB_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
    }
    
    public Cursor fetchAllCounters() {
        return mDb.query(DB_TABLE, new String[] {KEY_ROWID, KEY_TITLE, KEY_COUNT},
                null, null, null, null, null);
    }
    
    public Cursor fetchCounter(long rowId) throws SQLException {
        Cursor cursor = mDb.query(DB_TABLE, new String[] {KEY_ROWID, KEY_TITLE, KEY_COUNT}, 
                KEY_ROWID + "=" + rowId, null, null, null, null);
        
        if (cursor != null) {
            cursor.moveToFirst();
        }
        
        return cursor;
    }
    
    public boolean updateCounter(long rowId, String title, int count) {
        ContentValues args = new ContentValues();
        args.put(KEY_TITLE, title);
        args.put(KEY_COUNT, count);

        return mDb.update(DB_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
    }
    
    private class DatabaseHelper extends SQLiteOpenHelper {
        
        DatabaseHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }
        
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS notes");
            onCreate(db);
        }
    }
}
