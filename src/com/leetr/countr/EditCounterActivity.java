package com.leetr.countr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.deesastudio.countr.R;
import com.leetr.countr.model.Counter;

public class EditCounterActivity extends Activity {
    private EditText mCounterTitleEdit;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.edit_counter);

        initUiComponents();
    }

    private void initUiComponents() {
        mCounterTitleEdit = (EditText) findViewById(R.id.counterTitleEditText);

        Button applyBtn = (Button) findViewById(R.id.applyButton);
        applyBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mCounterTitleEdit.getText().length() > 0) {
                    finishWithResult();
                }
            }
        });

        Button cancelBtn = (Button) findViewById(R.id.cancelButton);
        cancelBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void finishWithResult() {
        Intent dataIntent = new Intent();
        dataIntent.putExtra(Counter.KEY_TITLE, mCounterTitleEdit.getText()
                .toString());
        setResult(Activity.RESULT_OK, dataIntent);
        finish();
    }
}
