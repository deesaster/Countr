package com.leetr.countr.model;

public class CounterItem {
//    public static final String TABLE_NAME     = "counter_items";
//
//    public static final String KEY_ID         = "_id";
//    public static final String KEY_COUNTER_ID = "counter_id";
//    public static final String KEY_VALUE      = "value";
//    public static final String KEY_TIMESTAMP  = "tstamp";
//    public static final String KEY_LATITUDE   = "latitude";
//    public static final String KEY_LONGITUDE  = "longitude";
//
//    public static long add(DbHelper dbHelper, long counterId, float value,
//            long latitude, long longitude) {
//
//        ContentValues initialValues = new ContentValues();
//        initialValues.put(KEY_COUNTER_ID, counterId);
//        initialValues.put(KEY_VALUE, value);
//        initialValues.put(KEY_LATITUDE, latitude);
//        initialValues.put(KEY_LONGITUDE, longitude);
//
//        SQLiteDatabase db = dbHelper.getDB();
//
//        return db.insert(TABLE_NAME, null, initialValues);
//    }
//
//    public static boolean delete(DbHelper dbHelper, long itemId) {
//        SQLiteDatabase db = dbHelper.getDB();
//
//        return db.delete(TABLE_NAME, KEY_ID + "=" + itemId, null) > 0;
//    }
//
//    public static Cursor itemsForCounter(DbHelper dbHelper, long counterId) {
//        SQLiteDatabase db = dbHelper.getDB();
//
//        return db.query(TABLE_NAME, new String[] { KEY_ID, KEY_VALUE,
//                KEY_TIMESTAMP, KEY_LATITUDE, KEY_LONGITUDE }, KEY_COUNTER_ID
//                + "=" + counterId, null, null, null, KEY_TIMESTAMP + " DESC");
//    }
//
//    public static CounterItem getById(DbHelper dbHelper, long rowId)
//            throws SQLException {
//        SQLiteDatabase db = dbHelper.getDB();
//        Cursor cursor = db.query(TABLE_NAME, new String[] { KEY_ID,
//                KEY_COUNTER_ID, KEY_VALUE, KEY_TIMESTAMP, KEY_LATITUDE,
//                KEY_LONGITUDE }, KEY_ID + "=" + rowId, null, null, null, null);
//
//        if (cursor != null) {
//            cursor.moveToFirst();
//        }
//
//        if (cursor.getCount() > 0) {
//            return itemFromDbRecord(cursor);
//        }
//        return null;
//    }
//
//    public static CounterItem itemFromDbRecord(Cursor cursor) {
//        CounterItem counterItem = new CounterItem();
//        counterItem.setId(cursor.getLong(0));
//        counterItem.setCounterId(cursor.getLong(1));
//        counterItem.setValue(cursor.getDouble(2));
//        counterItem.setTimestamp(cursor.getString(3));
//        counterItem.setLatitude(cursor.getString(4));
//        counterItem.setLongitude(cursor.getString(5));
//
//        return counterItem;
//    }

    private long   mId;
    private long   mCounterId;
    private double mValue;
    private String mTimestamp;
    private String mLatitude;
    private String mLongitude;

    public CounterItem() {

    }

    public void setId(long id) {
        mId = id;
    }

    public long getId() {
        return mId;
    }

    public void setCounterId(long counterId) {
        mCounterId = counterId;
    }

    public long getCounterId() {
        return mCounterId;
    }

    public void setValue(double value) {
        mValue = value;
    }

    public double getValue() {
        return mValue;
    }

    public void setLongitude(String longitude) {
        mLongitude = longitude;
    }

    public String getLongitude() {
        return mLongitude;
    }

    public void setLatitude(String latitude) {
        mLatitude = latitude;
    }

    public String getLatitude() {
        return mLatitude;
    }

    public void setTimestamp(String timestamp) {
        mTimestamp = timestamp;
    }

    public String getTimeString() {
        return mTimestamp;
    }
}
