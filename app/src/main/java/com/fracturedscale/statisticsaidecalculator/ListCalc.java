package com.fracturedscale.statisticsaidecalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class ListCalc extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_calc);

        //adds action bar for back button in top left
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
}
