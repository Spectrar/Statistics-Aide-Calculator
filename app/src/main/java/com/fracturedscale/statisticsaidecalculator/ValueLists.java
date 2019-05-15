package com.fracturedscale.statisticsaidecalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class ValueLists extends AppCompatActivity implements View.OnKeyListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value_lists);

        //adds action bar for back button in top left
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        LinearLayout l1 = findViewById(R.id.l1);
        l1.setOnKeyListener(this);
        EditText e5 = findViewById(R.id.editText6);
        e5.setOnKeyListener(this);

    }

    /**
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //when back button is pressed, navigate to main activity
        this.finish();
        return true;
    }

    /**
     *
     * @param menu
     * @return
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        String temp = Integer.toString(i);
        Toast.makeText(ValueLists.this, temp, Toast.LENGTH_SHORT).show();
        if (i==66){
            //Toast.makeText(ValueLists.this, "Next", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}
