package com.leetr.countr.fragment;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.SupportActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.leetr.R;
import com.leetr.countr.listener.OnCounterItemSelectedListener;

/**
 * Created By: Denis Smirnov <denis@deesastudio.com>
 * <p/>
 * Date: 11-10-19
 * Time: 9:23 PM
 */
public class CounterHistoryFragment extends ListFragment {
    private OnCounterItemSelectedListener mOnCounterItemSelectedListener;
    private LayoutInflater mInflater;
    private View mLayout;

    /**
     * Called when a fragment is first attached to its activity.
     * {@link #onCreate(android.os.Bundle)} will be called after this.
     */
    @Override
    public void onAttach(SupportActivity activity) {
        super.onAttach(activity);

        mOnCounterItemSelectedListener = (OnCounterItemSelectedListener) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mInflater = inflater;
        mLayout = inflater.inflate(R.layout.leetr_list_layout, container, false);
        return mLayout;
    }
}
