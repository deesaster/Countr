package com.deesastudio.countr;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.deesastudio.countr.models.Counter;

public class CounterListActivity extends Activity {
    public static final String COUNTER_ID               = "CounterId";

    private static final int   NEW_COUNTER_REQUEST_CODE = 1;

    private static final int   CONTEXTMENU_DELETE       = 1;
    private static final int   CONTEXTMENU_EDIT         = 2;

    private DbHelper           mDbHelper;
    private ListView           mCounterListView;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.counter_list);

        mDbHelper = new DbHelper(this);
        mDbHelper.open();

        initUiComponents();
        populateList();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
            case NEW_COUNTER_REQUEST_CODE:
                if (data != null && data.hasExtra(Counter.KEY_TITLE)) {
                    Counter.add(mDbHelper,
                            data.getExtras().getString(Counter.KEY_TITLE));
                }
                populateList();
                break;
            }
        }
    }

    private void initUiComponents() {
        mCounterListView = (ListView) findViewById(R.id.listCounters);
        mCounterListView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                    int position, long id) {
                launchCounterDetailsForRow(id);
            }
        });

        mCounterListView
                .setOnCreateContextMenuListener(new OnCreateContextMenuListener() {

                    @Override
                    public void onCreateContextMenu(ContextMenu menu, View v,
                            ContextMenuInfo menuInfo) {

                        AdapterContextMenuInfo info = (AdapterContextMenuInfo) menuInfo;
                        Counter selectedCounter = Counter.getById(mDbHelper,
                                info.id);
                        menu.setHeaderTitle(selectedCounter.getTitle());

                        menu.add(0, CONTEXTMENU_DELETE, 0, R.string.delete);
                        menu.add(0, CONTEXTMENU_EDIT, 0, R.string.edit);
                    }
                });

        ImageView btnNewCounter = (ImageView) findViewById(R.id.btnAddNewCounter);
        btnNewCounter.setVisibility(View.VISIBLE);
        btnNewCounter.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent newCounterIntent = new Intent(CounterListActivity.this,
                        EditCounterActivity.class);
                startActivityForResult(newCounterIntent,
                        NEW_COUNTER_REQUEST_CODE);
            }
        });
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case CONTEXTMENU_DELETE:
            AdapterContextMenuInfo menuInfo = (AdapterContextMenuInfo) item
                    .getMenuInfo();
            Counter.delete(mDbHelper, menuInfo.id);
            populateList();
            break;
        default:
            return super.onContextItemSelected(item);
        }
        return true;
    }

    // private Cursor getCounterByRowId(long rowId) {
    // return mDbHelper.fetchCounter(rowId);
    // }

    private void populateList() {

        Cursor c = Counter.all(mDbHelper);
        startManagingCursor(c);

        String[] source = new String[] { Counter.KEY_TITLE };
        int[] dest = new int[] { R.id.textCounterTitle };

        SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(this,
                R.layout.listitem_counter, c, source, dest);

        mCounterListView.setAdapter(cursorAdapter);
    }

    private void launchCounterDetailsForRow(long rowId) {
        Intent detailsIntent = new Intent(this, CounterDetailsActivity.class);

        detailsIntent.putExtra(Counter.KEY_ID, rowId);
        startActivity(detailsIntent);
    }
}