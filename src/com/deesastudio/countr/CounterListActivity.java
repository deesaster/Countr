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
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class CounterListActivity extends Activity {
    public static final String COUNTER_ID = "CounterId";
    private static final int NEW_COUNTER_REQUEST_CODE = 1; 
    private static final int CONTEXTMENU_DELETEITEM = 1;
    
    private CounterDbHelper mDbHelper;
    private ListView mCounterListView;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.counter_list);
        
        mDbHelper = new CounterDbHelper(this);
        mDbHelper.open();
        
        initUiComponents();
        populateList();
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, 
            Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch(requestCode) {
            case NEW_COUNTER_REQUEST_CODE:
                if (data != null && data.hasExtra(Counter.KEY_COUNTER_TITLE)) {
                    mDbHelper.createCounter(data.getExtras().getString(Counter.KEY_COUNTER_TITLE));
                }
                populateList();
                break;
            }
        }
    }
    
    private void initUiComponents() {
        mCounterListView = (ListView)findViewById(R.id.listCounters);
        mCounterListView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position,
                    long id) {
                launchCounterDetailsForPosition(id);
            }
        });
        
        mCounterListView.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {
            
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v,
                    ContextMenuInfo menuInfo) {
                menu.setHeaderTitle("Test");
                menu.add(0, CONTEXTMENU_DELETEITEM,0,"Delete");
            }
        });
        
        Button btnNewCounter = (Button)findViewById(R.id.btnAddNewCounter);
        btnNewCounter.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                Intent newCounterIntent = new Intent(CounterListActivity.this, 
                        NewCounterActivity.class);
                startActivityForResult(newCounterIntent, NEW_COUNTER_REQUEST_CODE);
//                mDbHelper.createCounter("Text");
//                populateList();
            }
        });
    }
    
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch(item.getItemId()) {
        case CONTEXTMENU_DELETEITEM:
            AdapterContextMenuInfo menuInfo = 
                (AdapterContextMenuInfo)item.getMenuInfo();
            
            mDbHelper.deleteCounter(menuInfo.id);
            populateList();
            break;
        default:
            return super.onContextItemSelected(item);
        }
        return true;
    }
    
    private void populateList() {
        Cursor c = mDbHelper.fetchAllCounters();
        startManagingCursor(c);
        
        String[] source = new String[] {CounterDbHelper.KEY_TITLE};
        int[] dest = new int[] {R.id.textCounterTitle};
        
        SimpleCursorAdapter cursorAdapter = 
            new SimpleCursorAdapter(this, R.layout.listitem_counter,
                    c, source, dest);
        
        mCounterListView.setAdapter(cursorAdapter);
    }
    
    private void launchCounterDetailsForPosition(long rowId) {
        Intent detailsIntent = new Intent(this, CounterDetailsActivity.class);
        
        Cursor c = mDbHelper.fetchCounter(rowId);
        int counterId = c.getInt(0);
        
        detailsIntent.putExtra(COUNTER_ID, counterId);
        startActivity(detailsIntent);
    }
}