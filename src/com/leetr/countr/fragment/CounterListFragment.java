package com.leetr.countr.fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.SupportActivity;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.SimpleCursorAdapter;
import com.leetr.R;
import com.leetr.core.db.LeetrDbHelper;
import com.leetr.countr.listener.OnCounterSelectedListener;
import com.leetr.countr.provider.CounterProvider;

/**
 * Created By: Denis Smirnov <denis@deesastudio.com>
 * <p/>
 * Date: 11-09-28
 * Time: 7:56 PM
 */
public class CounterListFragment extends ListFragment implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final int COUNTER_LIST_LOADER = 0x01;

    private LayoutInflater mInflater;
    private View mLayout;
    private LeetrDbHelper mDbHelper;
    private SimpleCursorAdapter mCursorAdapter;
    private OnCounterSelectedListener mOnCounterSelectedListener;

    @Override
    public void onAttach(SupportActivity activity) {
        super.onAttach(activity);

        mOnCounterSelectedListener = (OnCounterSelectedListener) activity;
    }

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

        getLoaderManager().initLoader(COUNTER_LIST_LOADER, null, this);

        initDataset();
    }

    protected void initDataset() {

        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mOnCounterSelectedListener.onCounterSelected(l);
//                ContentValues values = new ContentValues();
//                values.put(CounterProvider.CounterMetaData.COUNTER_NAME, "test" + l);
//
//                ContentResolver cr = getActivity().getContentResolver();
//                cr.insert(CounterProvider.CounterMetaData.CONTENT_URI, values);
            }
        });
        initAdapter(null);
    }

    private void initAdapter(Cursor cursor) {
        String[] source = new String[]{CounterProvider.CounterMetaData.COUNTER_NAME};
        int[] dest = new int[]{R.id.textCounterTitle};

        SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(mInflater.getContext(),
                R.layout.listitem_counter, cursor, source, dest);

        getListView().setAdapter(cursorAdapter);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        CursorLoader cursorLoader = new CursorLoader(getActivity(), CounterProvider.CounterMetaData.CONTENT_URI, null,
                null, null, null);

        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        initAdapter(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {
        initAdapter(null);
    }
}
