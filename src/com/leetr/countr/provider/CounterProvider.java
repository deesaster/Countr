package com.leetr.countr.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.provider.BaseColumns;
import android.text.TextUtils;
import com.leetr.core.db.LeetrDbHelper;

import java.util.HashMap;

/**
 * Created By: Denis Smirnov <denis@deesastudio.com>
 * <p/>
 * Date: 11-10-16
 * Time: 12:44 PM
 */
public class CounterProvider extends ContentProvider {
    public static final String Tag = "CounterProvider";

    private static HashMap<String, String> sCountersProjectionMap;

    static {
        sCountersProjectionMap = new HashMap<String, String>();
        sCountersProjectionMap.put(CounterMetaData._ID,
                CounterMetaData._ID);
        sCountersProjectionMap.put(CounterMetaData.COUNTER_NAME,
                CounterMetaData.COUNTER_NAME);
    }

    private static final UriMatcher sUriMatcher;
    private static final int COUNTER_COLLECTION_URI_INDICATOR = 1;
    private static final int SINGLE_COUNTER_URI_INDICATOR = 2;

    static {
        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        sUriMatcher.addURI(CounterMetaData.AUTHORITY, "counters", COUNTER_COLLECTION_URI_INDICATOR);
        sUriMatcher.addURI(CounterMetaData.AUTHORITY, "counters/#", SINGLE_COUNTER_URI_INDICATOR);
    }

    private LeetrDbHelper mDbHelper;

    @Override
    public boolean onCreate() {
        mDbHelper = new LeetrDbHelper(getContext());
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();

        switch (sUriMatcher.match(uri)) {
            case COUNTER_COLLECTION_URI_INDICATOR:
                builder.setTables(CounterMetaData.TABLE_NAME);
                builder.setProjectionMap(sCountersProjectionMap);
                break;
            case SINGLE_COUNTER_URI_INDICATOR:
                builder.setTables(CounterMetaData.TABLE_NAME);
                builder.setProjectionMap(sCountersProjectionMap);
                builder.appendWhere(CounterMetaData._ID + "=" + uri.getPathSegments().get(1));
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        String orderBy;
        if (TextUtils.isEmpty(sortOrder)) {
            orderBy = CounterMetaData.DEFAULT_SORT_ORDER;
        } else {
            orderBy = sortOrder;
        }

        SQLiteDatabase db = mDbHelper.openDb();
        Cursor c = builder.query(db, projection, selection, selectionArgs, null, null, orderBy);
        c.setNotificationUri(getContext().getContentResolver(), uri);

        return c;
    }

    @Override
    public String getType(Uri uri) {
        switch (sUriMatcher.match(uri)) {
            case COUNTER_COLLECTION_URI_INDICATOR:
                return CounterMetaData.CONTENT_TYPE;
            case SINGLE_COUNTER_URI_INDICATOR:
                return CounterMetaData.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        //validate uri
        if (sUriMatcher.match(uri) != COUNTER_COLLECTION_URI_INDICATOR) {
            throw new IllegalArgumentException("Unknown URI " + uri);
        }

        ContentValues values;
        if (contentValues != null) {
            values = new ContentValues(contentValues);
        } else {
            values = new ContentValues();
        }

        if (!values.containsKey(CounterMetaData.COUNTER_NAME)) {
            throw new RuntimeException("Failed to add counter, counter name required");
        }

        SQLiteDatabase db = mDbHelper.openDb();
        long rowId = db.insert(CounterMetaData.TABLE_NAME, CounterMetaData.TABLE_NAME, values);

        if (rowId > 0) {
            Uri insertedUri = ContentUris.withAppendedId(CounterMetaData.CONTENT_URI, rowId);
            getContext().getContentResolver().notifyChange(insertedUri, null);

            return insertedUri;
        }

        throw new RuntimeException("Failed to insert row into " + uri);
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }

    public static class CounterMetaData implements BaseColumns {
        public static final String AUTHORITY = "com.leetr.countr.provider.CounterProvider";

        public static final String TABLE_NAME = "counter";
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/counters");
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.leetr.countr.counter";
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.leetr.countr.counter";

        public static final String DEFAULT_SORT_ORDER = "sort_order ASC";

        //columns
        public static final String COUNTER_NAME = "name";

        private CounterMetaData() {
        }
    }
}
