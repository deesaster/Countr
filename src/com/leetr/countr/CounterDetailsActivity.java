package com.leetr.countr;

import android.os.Bundle;
import android.support.v4.app.ActionBar;
import android.support.v4.view.ViewPager;
import com.leetr.activity.LeetrActivity;
import com.leetr.adapter.TabsAdapter;
import com.leetr.countr.fragment.CounterDetailsFragment;
import com.leetr.countr.fragment.CounterHistoryFragment;
import com.leetr.countr.fragment.CounterStatsFragment;
import com.leetr.countr.listener.OnCounterItemSelectedListener;
import com.leetr.countr.listener.OnCounterRequestedListener;
import com.leetr.countr.model.Counter;

public class CounterDetailsActivity extends LeetrActivity
        implements OnCounterItemSelectedListener, OnCounterRequestedListener {


    private static final int CONTEXTMENU_DELETE = 1;

//    private long mCounterId;
//    private ListView mItemsListView;
//    private ViewFlipper mContentFlipper;
//    private DbHelper mDbHelper;

    private ViewPager mViewPager;
    private TabsAdapter mTabsAdapter;
    private Counter mCounter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_counter_details);

        Bundle args = null;

        if (getIntent() != null) {
            args = getIntent().getExtras();

            if (args.containsKey(Counter.EXTRA_ID)) {
                mCounter = Counter.withId(this, args.getLong(Counter.EXTRA_ID));
            }

        }

        final ActionBar ab = getSupportActionBar();
        ab.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mTabsAdapter = new TabsAdapter(this, getSupportActionBar(), mViewPager);
        mTabsAdapter.addTab(ab.newTab().setText("Details"), CounterDetailsFragment.class, args);
        mTabsAdapter.addTab(ab.newTab().setText("History"), CounterHistoryFragment.class, args);
        mTabsAdapter.addTab(ab.newTab().setText("Stats"), CounterStatsFragment.class, args);

        if (savedInstanceState != null) {
            getSupportActionBar().setSelectedNavigationItem(savedInstanceState.getInt("index"));
        }
//        Intent launchIntent = getIntent();
//        if (launchIntent.hasExtra(Counter.KEY_ID)) {
//            mCounterId = launchIntent.getExtras().getLong(Counter.KEY_ID);
//        }
//
//        initUiComponents();
//
//        mDbHelper = DbHelper.open(getApplicationContext());
//        populateList();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("index", getSupportActionBar().getSelectedNavigationIndex());
    }

//    protected void initTabs(ActionBar bar) {
//        bar.addTab(bar.newTab().setText("Details").setTabListener(this));
//        bar.addTab(bar.newTab().setText("History").setTabListener(this));
//    }

    @Override
    protected void onDestroy() {
//        if (mDbHelper != null) {
//            mDbHelper.close();
//        }
        super.onDestroy();
    }

    private void initUiComponents() {
//        Button incrementBtn = (Button) findViewById(R.id.incrementButton);
//        incrementBtn.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                increment(1);
//            }
//        });

//        mItemsListView = (ListView) findViewById(R.id.listItems);
//        mItemsListView
//                .setOnCreateContextMenuListener(new OnCreateContextMenuListener() {
//
//                    @Override
//                    public void onCreateContextMenu(ContextMenu menu, View v,
//                                                    ContextMenuInfo menuInfo) {
////                        AdapterContextMenuInfo info = (AdapterContextMenuInfo) menuInfo;
////                        CounterItem cItem = CounterItem.getById(mDbHelper, info.id);
//
//                        menu.add(0, CONTEXTMENU_DELETE, 0, R.string.delete);
//                    }
//                });

//        mContentFlipper = (ViewFlipper) findViewById(R.id.contentFlipper);

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
//        CounterItem.add(mDbHelper, mCounterId, value, 0, 0);
//        populateList();
    }

    private void populateList() {
//        Cursor c = CounterItem.itemsForCounter(mDbHelper, mCounterId);
//        startManagingCursor(c);
//
//        String[] source = new String[]{CounterItem.KEY_TIMESTAMP};
//        int[] dest = new int[]{R.id.textTimestamp};
//
//        SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(this,
//                R.layout.listitem_counteritem, c, source, dest);
//
//        mItemsListView.setAdapter(cursorAdapter);
    }


    @Override
    public void onCounterItemSelected(long id) {

    }

    @Override
    public Counter onCounterRequested(long id) {
        return mCounter;
    }
}
