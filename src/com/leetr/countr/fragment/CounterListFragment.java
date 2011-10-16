package com.leetr.countr.fragment;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleCursorAdapter;
import com.leetr.R;
import com.leetr.core.db.LeetrDbHelper;
import com.leetr.countr.provider.CounterProvider;

/**
 * Created By: Denis Smirnov <denis@deesastudio.com>
 * <p/>
 * Date: 11-09-28
 * Time: 7:56 PM
 */
public class CounterListFragment extends ListFragment {
    private LayoutInflater mInflater;
    private View mLayout;
    private LeetrDbHelper mDbHelper;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mInflater = inflater;
        mLayout = inflater.inflate(R.layout.leetr_list_layout, null, false);
        return mLayout;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mDbHelper = new LeetrDbHelper(mInflater.getContext());
        mDbHelper.openDb();

        initDataset();
    }

    protected void initDataset() {
//        SQLiteDatabase db = mDbHelper.getDb();
//        Cursor c = Counter.all(db);

        ContentValues values = new ContentValues();
        values.put(CounterProvider.MetaData.Counter.COUNTER_NAME, "test1");

        ContentResolver cr = getActivity().getContentResolver();
        cr.insert(CounterProvider.MetaData.Counter.CONTENT_URI, values);

        Cursor c = getActivity().managedQuery(CounterProvider.MetaData.Counter.CONTENT_URI, null, null, null, null);

//        int iName = c.getColumnIndex(CounterProvider.MetaData.Counter.COUNTER_NAME);

        String[] source = new String[]{CounterProvider.MetaData.Counter.COUNTER_NAME};
        int[] dest = new int[]{R.id.textCounterTitle};

        SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(mInflater.getContext(),
                R.layout.listitem_counter, c, source, dest);

        getListView().setAdapter(cursorAdapter);
    }
}
