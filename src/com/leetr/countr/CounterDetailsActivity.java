package com.leetr.countr;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnCreateContextMenuListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.ViewFlipper;
import com.deesastudio.countr.R;
import com.deesastudio.countr.models.Counter;
import com.deesastudio.countr.models.CounterItem;

public class CounterDetailsActivity extends Activity {

    private static final int CONTEXTMENU_DELETE = 1;

    private long mCounterId;
    private ListView mItemsListView;
    private ViewFlipper mContentFlipper;
    private DbHelper mDbHelper;

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

    private void initUiComponents() {
        Button incrementBtn = (Button) findViewById(R.id.incrementButton);
        incrementBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                increment(1);
            }
        });

        mItemsListView = (ListView) findViewById(R.id.listItems);
        mItemsListView
                .setOnCreateContextMenuListener(new OnCreateContextMenuListener() {

                    @Override
                    public void onCreateContextMenu(ContextMenu menu, View v,
                                                    ContextMenuInfo menuInfo) {
//                        AdapterContextMenuInfo info = (AdapterContextMenuInfo) menuInfo;
//                        CounterItem cItem = CounterItem.getById(mDbHelper, info.id);

                        menu.add(0, CONTEXTMENU_DELETE, 0, R.string.delete);
                    }
                });

        mContentFlipper = (ViewFlipper) findViewById(R.id.contentFlipper);

//        TabLayout tabLayout = (TabLayout) findViewById(R.id.contextTabLayout);
//        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//
//            @Override
//            public void onTabSelected(View v, int tabId) {
//                switch (tabId) {
//                    case R.id.actionTabBtn:
//                        mContentFlipper.setDisplayedChild(0);
//                        break;
//                    case R.id.historyTabBtn:
//                        mContentFlipper.setDisplayedChild(1);
//                        break;
//                    case R.id.statsTabBtn:
//                        Toast.makeText(getApplicationContext(), "test1", Toast.LENGTH_SHORT).show();
//                        break;
//                }
//            }
//        });

//        tabLayout.setSelectedTab(R.id.actionTabBtn);
    }

    private void increment(float value) {
        CounterItem.add(mDbHelper, mCounterId, value, 0, 0);
        populateList();
    }

    private void populateList() {
        Cursor c = CounterItem.itemsForCounter(mDbHelper, mCounterId);
        startManagingCursor(c);

        String[] source = new String[]{CounterItem.KEY_TIMESTAMP};
        int[] dest = new int[]{R.id.textTimestamp};

        SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(this,
                R.layout.listitem_counteritem, c, source, dest);

        mItemsListView.setAdapter(cursorAdapter);
    }
}
