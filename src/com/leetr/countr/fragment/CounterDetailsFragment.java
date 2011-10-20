package com.leetr.countr.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.SupportActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.leetr.countr.R;
import com.leetr.countr.listener.OnCounterRequestedListener;
import com.leetr.countr.model.Counter;

/**
 * Created By: Denis Smirnov <denis@deesastudio.com>
 * <p/>
 * Date: 11-10-19
 * Time: 9:23 PM
 */
public class CounterDetailsFragment extends Fragment {
    private LayoutInflater mInflater;
    private View mLayout;
    private Counter mCounter;
    private OnCounterRequestedListener mOnCounterRequestedListener;

    /**
     * Called when a fragment is first attached to its activity.
     * {@link #onCreate(android.os.Bundle)} will be called after this.
     */
    @Override
    public void onAttach(SupportActivity activity) {
        super.onAttach(activity);

        mOnCounterRequestedListener = (OnCounterRequestedListener) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mInflater = inflater;
        mLayout = inflater.inflate(R.layout.fragment_counter_details, container, false);
        return mLayout;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();

        if (args != null) {
            if (args.containsKey(Counter.EXTRA_ID)) {
                mCounter = mOnCounterRequestedListener.onCounterRequested(args.getLong(Counter.EXTRA_ID));
            }
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initUiElements();
    }

    protected void initUiElements() {
        TextView name = (TextView) mLayout.findViewById(R.id.name);
        name.setText(mCounter.getTitle());

        TextView count = (TextView) mLayout.findViewById(R.id.count);
        count.setText(String.valueOf(mCounter.getId()));
    }
}
