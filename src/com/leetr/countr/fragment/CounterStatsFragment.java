package com.leetr.countr.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.leetr.R;

/**
 * Created By: Denis Smirnov <denis@deesastudio.com>
 * <p/>
 * Date: 11-10-19
 * Time: 9:24 PM
 */
public class CounterStatsFragment extends Fragment {
    private LayoutInflater mInflater;
    private View mLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mInflater = inflater;
        mLayout = inflater.inflate(R.layout.fragment_counter_stats, container, false);
        return mLayout;
    }
}
