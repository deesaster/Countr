package com.deesastudio.countr;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.ViewFlipper;

import com.deesastudio.countr.models.Counter;
import com.deesastudio.countr.models.CounterItem;

public class CounterDetailsActivity extends Activity {
    private long        mCounterId;
    private ListView    mItemsListView;
    private ViewFlipper mContentFlipper;
    private DbHelper    mDbHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.counter_details);

        Intent launchIntent = getIntent();
        if (launchIntent.hasExtra(Counter.KEY_ID)) {
            mCounterId = launchIntent.getExtras().getLong(Counter.KEY_ID);
        }

        initUiComponents();

        mDbHelper = DbHelper.open(getApplicationContext());
        populateList();
    }

    @Override
    protected void onDestroy() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
        super.onDestroy();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {

        super.onStop();
    }

    private void initUiComponents() {
        Button incrementBtn = (Button) findViewById(R.id.incrementButton);
        incrementBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                increment(1);
            }
        });

        mItemsListView = (ListView) findViewById(R.id.listItems);
        
        mContentFlipper = (ViewFlipper) findViewById(R.id.contentFlipper);
        
        Button actionsBtn = (Button) findViewById(R.id.actionsButton);
        actionsBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                mContentFlipper.setDisplayedChild(0);
            }
        });
        
        Button historyBtn = (Button) findViewById(R.id.historyButton);
        historyBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                mContentFlipper.setDisplayedChild(1);
            }
        });
    }

    private void increment(float value) {
        CounterItem.add(mDbHelper, mCounterId, value, 0, 0);
        populateList();
    }

    private void populateList() {
        Cursor c = CounterItem.itemsForCounter(mDbHelper, mCounterId);
        startManagingCursor(c);

        String[] source = new String[] { CounterItem.KEY_TIMESTAMP };
        int[] dest = new int[] { R.id.textTimestamp };

        SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(this,
                R.layout.listitem_counteritem, c, source, dest);

        mItemsListView.setAdapter(cursorAdapter);
    }
}
