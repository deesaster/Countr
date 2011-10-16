package com.leetr.countr;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.deesastudio.countr.models.Counter;
import com.deesastudio.countr.models.CounterItem;

public class DbHelper {

    private static final String TAG                      = "CounterDBAdapter";

    private static final String DB_NAME                  = "counter_db";
    private static final int    DB_VERSION               = 2;

    private static final String SQL_COUNTERS_CREATE      = "create table "
                                                                 + Counter.TABLE_NAME
                                                                 + "(_id integer primary key autoincrement, "
                                                                 + "title text not null, "
                                                                 + "count integer default(0));";

    private static final String SQL_COUNTER_ITEMS_CREATE = "create table "
                                                                 + CounterItem.TABLE_NAME
                                                                 + "(_id integer primary key autoincrement, "
                                                                 + "counter_id integer not null, "
                                                                 + "value real not null default(1.0),"
                                                                 + "tstamp default current_timestamp, "
                                                                 + "latitude TEXT,"
                                                                 + "longitude TEXT)";

    private DatabaseHelper      mDbHelper;
    private SQLiteDatabase      mDb;
    private final Context       mContext;

    public static DbHelper open(Context context) {
        return new DbHelper(context).open();
    }

    public DbHelper(Context context) {
        mContext = context;
    }

    public DbHelper open() throws SQLException {
        mDbHelper = new DatabaseHelper(mContext);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        mDbHelper.close();
    }

    public SQLiteDatabase getDB() {
        return mDb;
    }

    private class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_COUNTERS_CREATE);
            db.execSQL(SQL_COUNTER_ITEMS_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");

            if (oldVersion == 1 && newVersion == 2) {
                db.execSQL(SQL_COUNTER_ITEMS_CREATE);
            }
        }
    }
}
