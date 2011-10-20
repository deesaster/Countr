package com.leetr.countr;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActionBar;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import com.leetr.R;
import com.leetr.activity.LeetrActivity;
import com.leetr.countr.fragment.CounterListFragment;
import com.leetr.countr.listener.OnCounterSelectedListener;
import com.leetr.countr.model.Counter;

public class CounterListActivity extends LeetrActivity implements OnCounterSelectedListener, ActionBar.TabListener {
    public static final String COUNTER_ID = "CounterId";

    private static final int NEW_COUNTER_REQUEST_CODE = 1;

    private static final int CONTEXTMENU_DELETE = 1;
    private static final int CONTEXTMENU_EDIT = 2;

    private DbHelper mDbHelper;
    private ListView mCounterListView;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.leetr_app_layout);

//        final ActionBar ab = getSupportActionBar();


        // set defaults for logo & home up
//        ab.setDisplayHomeAsUpEnabled(false);
//        ab.setDisplayUseLogoEnabled(false);

        if (savedInstanceState == null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.middleFragment, new CounterListFragment());
            ft.commit();
        }
//        setContentView(R.layout.counter_list);
//
//        mDbHelper = new DbHelper(this);
//        mDbHelper.open();
//
//        initUiComponents();
//        populateList();
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (resultCode == Activity.RESULT_OK) {
//            switch (requestCode) {
//                case NEW_COUNTER_REQUEST_CODE:
//                    if (data != null && data.hasExtra(Counter.KEY_TITLE)) {
//                        Counter.add(mDbHelper,
//                                data.getExtras().getString(Counter.KEY_TITLE));
//                    }
//                    populateList();
//                    break;
//            }
//        }
    }

    private void initUiComponents() {
//        mCounterListView = (ListView) findViewById(R.id.listCounters);
//        mCounterListView.setOnItemClickListener(new OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View v,
//                                    int position, long id) {
//                launchCounterDetailsForRow(id);
//            }
//        });
//
//        mCounterListView
//                .setOnCreateContextMenuListener(new OnCreateContextMenuListener() {
//
//                    @Override
//                    public void onCreateContextMenu(ContextMenu menu, View v,
//                                                    ContextMenuInfo menuInfo) {
//
//                        AdapterContextMenuInfo info = (AdapterContextMenuInfo) menuInfo;
//                        Counter selectedCounter = Counter.getById(mDbHelper,
//                                info.id);
//                        menu.setHeaderTitle(selectedCounter.getTitle());
//
//                        menu.add(0, CONTEXTMENU_DELETE, 0, R.string.delete);
//                        menu.add(0, CONTEXTMENU_EDIT, 0, R.string.edit);
//                    }
//                });
//
//        ImageView btnNewCounter = (ImageView) findViewById(R.id.btnAddNewCounter);
//        btnNewCounter.setVisibility(View.VISIBLE);
//        btnNewCounter.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Intent newCounterIntent = new Intent(CounterListActivity.this,
//                        EditCounterActivity.class);
//                startActivityForResult(newCounterIntent,
//                        NEW_COUNTER_REQUEST_CODE);
//            }
//        });
    }

    protected void showAddCounter() {
        Intent intent = new Intent(this, CounterDetailsActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Add")
                .setOnMenuItemClickListener(new android.support.v4.view.MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(android.support.v4.view.MenuItem item) {
                        showAddCounter();
                        return true;
                    }
                })
                .setIcon(R.drawable.header_add_button)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case CONTEXTMENU_DELETE:
//                AdapterContextMenuInfo menuInfo = (AdapterContextMenuInfo) item
//                        .getMenuInfo();
//                Counter.delete(mDbHelper, menuInfo.id);
//                populateList();
//                break;
//            default:
//                return super.onContextItemSelected(item);
//        }
        return true;
    }

    // private Cursor getCounterByRowId(long rowId) {
    // return mDbHelper.fetchCounter(rowId);
    // }

    private void populateList() {

//        Cursor c = Counter.all(mDbHelper);
//        startManagingCursor(c);
//
//        String[] source = new String[]{Counter.KEY_TITLE};
//        int[] dest = new int[]{R.id.textCounterTitle};
//
//        SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(this,
//                R.layout.listitem_counter, c, source, dest);
//
//        mCounterListView.setAdapter(cursorAdapter);
    }

    private void launchCounterDetailsForRow(long rowId) {
//        Intent detailsIntent = new Intent(this, CounterDetailsActivity.class);
//
//        detailsIntent.putExtra(Counter.KEY_ID, rowId);
//        startActivity(detailsIntent);
    }

    @Override
    public void onCounterSelected(long id) {
        Intent intent = new Intent(this, CounterDetailsActivity.class);
        intent.putExtra(Counter.EXTRA_ACTION, Counter.ACTION_VIEW);
        intent.putExtra(Counter.EXTRA_ID, id);
        startActivity(intent);
    }

    /**
     * Called when a tab that is already selected is chosen again by the
     * user. Some applications may use this action to return to the top
     * level of a category.
     *
     * @param tab The tab that was reselected.
     * @param ft  Unused, always {@code null}. Begin your own transaction by
     *            calling {@link android.support.v4.app.FragmentActivity#getSupportFragmentManager()}.
     */
    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Called when a tab enters the selected state.
     *
     * @param tab The tab that was selected
     * @param ft  Unused, always {@code null}. Begin your own transaction by
     *            calling {@link android.support.v4.app.FragmentActivity#getSupportFragmentManager()}.
     */
    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Called when a tab exits the selected state.
     *
     * @param tab The tab that was unselected
     * @param ft  Unused, always {@code null}. Begin your own transaction by
     *            calling {@link android.support.v4.app.FragmentActivity#getSupportFragmentManager()}.
     */
    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}