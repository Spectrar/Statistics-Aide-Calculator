package com.fracturedscale.statisticsaidecalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class ListCalc extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_calc);

        //adds action bar for back button in top left
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Spinner dataListSelector = findViewById(R.id.dataSpinner);
        String[] temp1 = {"Values List","List 1","List 2","List 3","List 4"};
        ArrayAdapter <String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, temp1);
        dataListSelector.setAdapter(dataAdapter);

        Spinner freqListSelector = findViewById(R.id.freqSpinner);
        String[] temp2 = {"Frequency List","List 1","List 2","List 3","List 4"};
        ArrayAdapter <String> freqAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, temp2);
        freqListSelector.setAdapter(freqAdapter);
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
