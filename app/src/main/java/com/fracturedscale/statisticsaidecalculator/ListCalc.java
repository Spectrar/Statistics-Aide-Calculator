package com.fracturedscale.statisticsaidecalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ListCalc extends AppCompatActivity implements View.OnClickListener{

    private Spinner dataListSelector;
    private Spinner freqListSelector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_calc);

        //adds action bar for back button in top left
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button calcBtn = findViewById(R.id.calcBtn);
        calcBtn.setOnClickListener(this);

        dataListSelector = findViewById(R.id.dataSpinner);
        ArrayList temp1 = new ArrayList();
        temp1.add("Select Values List");
        temp1.add(ValueLists.l1List);
        temp1.add(ValueLists.l2List);
        temp1.add(ValueLists.l3List);
        temp1.add(ValueLists.l4List);
        ArrayAdapter <String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, temp1);
        dataListSelector.setAdapter(dataAdapter);

        freqListSelector = findViewById(R.id.freqSpinner);
        ArrayList temp2 = new ArrayList();
        temp2.add("Select Frequency List");
        temp2.add(ValueLists.l1List);
        temp2.add(ValueLists.l2List);
        temp2.add(ValueLists.l3List);
        temp2.add(ValueLists.l4List);
        ArrayAdapter <String> freqAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, temp2);
        freqListSelector.setAdapter(freqAdapter);
    }

    @Override
    public void onClick(View view) {
        calculate();

    }

    private void calculate(){
        DataDrivenStatsHelper myCalc;
        if(dataListSelector.getSelectedItem().toString().equals("Select Values List")){
            Toast.makeText(this,"You must Select a values list", Toast.LENGTH_SHORT);
        }else{
            if(freqListSelector.getSelectedItem().toString().equals("Select Frequency List")){
                ArrayList<Double> temp = (ArrayList<Double>) dataListSelector.getSelectedItem();
                myCalc=new DataDrivenStatsHelper(temp.toArray(new Double[temp.size()]));
            }else{
                ArrayList<Double> temp = (ArrayList<Double>) dataListSelector.getSelectedItem();
                ArrayList<Double> temp2 = (ArrayList<Double>) freqListSelector.getSelectedItem();
                HashMap<Double, Integer> map= new HashMap<>();
                for(int i = 0; i<temp.size(); i++){
                    if(temp.get(i).equals(null) || temp2.get(i).equals(null)){
                        continue;
                    }
                    map.put(temp.get(i),temp2.get(i).intValue());
                }
                myCalc= new DataDrivenStatsHelper(map);
            }


        }
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
