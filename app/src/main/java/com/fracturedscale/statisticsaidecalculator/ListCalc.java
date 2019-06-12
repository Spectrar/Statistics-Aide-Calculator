package com.fracturedscale.statisticsaidecalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
        if(dataListSelector.getSelectedItem().equals("Select Values List")){
            Toast.makeText(this,"You must Select a values list", Toast.LENGTH_SHORT).show();
        }else{
            if(freqListSelector.getSelectedItem().toString().equals("Select Frequency List")){
                ArrayList<Double> temp = (ArrayList<Double>) dataListSelector.getSelectedItem();
                myCalc=new DataDrivenStatsHelper(temp.toArray(new Double[temp.size()]));
            }else{
                ArrayList<Double> temp = (ArrayList<Double>) dataListSelector.getSelectedItem();
                ArrayList<Double> temp2 = (ArrayList<Double>) freqListSelector.getSelectedItem();
                HashMap<Double, Integer> map= new HashMap<>();
                for(int i = 0; i<temp.size(); i++){
                    if(temp.get(i) == null || temp2.get(i)==null){
                        continue;
                    }
                    map.put(temp.get(i),temp2.get(i).intValue());
                }
                myCalc= new DataDrivenStatsHelper(map);
            }
        populateViews(myCalc);

        }
    }

    private void populateViews(DataDrivenStatsHelper myCalc) {
        String padding = "  ";
        ArrayList<String> temp = new ArrayList<>();
        String rFStr="";
        for(Object key : myCalc.relativeFrequency().keySet()){
            temp.add(((Double) key)+" : "+round((double) myCalc.relativeFrequency().get(key))+"\n");
        }
        Collections.sort(temp);
        for(String s: temp){
            rFStr=rFStr+s;
        }
        TextView rF = findViewById(R.id.relativeFrequencyResult);
        rF.setText(rFStr);

        temp = new ArrayList<>();
        String fStr="";
        for(Object key : myCalc.singleFrequency().keySet()){
            temp.add(((Double) key)+" : "+round((double) myCalc.singleFrequency().get(key))+"\n");
        }
        Collections.sort(temp);
        for(String s: temp){
            fStr=fStr+s;
        }
        TextView f = findViewById(R.id.frequencyResult);
        f.setText(fStr);

        TextView mode = findViewById(R.id.modeResult);
        mode.setText(padding+myCalc.theMode().toString());

        TextView mean = findViewById(R.id.meanResult);
        mean.setText(padding+Double.toString(round(myCalc.theMean())));

        TextView samSd = findViewById(R.id.samSdResult);
        samSd.setText(padding+Double.toString(round(myCalc.theSampleStandardDeviation())));//TODO add population SD

        TextView popSd = findViewById(R.id.popSdResult);
        popSd.setText(padding+Double.toString(round(myCalc.thePopulationStandardDeviation())));

        TextView range = findViewById(R.id.rangeResult);
        range.setText(padding+Double.toString(round(myCalc.theRange())));

        TextView z = findViewById(R.id.zScoreResult);
        EditText zI = findViewById(R.id.zScoreInput);
        z.setText(padding+Double.toString(round(myCalc.theZScore(Double.valueOf(zI.getText().toString())))));

        TextView q1 = findViewById(R.id.q1Result);
        q1.setText(padding+Double.toString(round(myCalc.theQ1())));

        TextView q3 = findViewById(R.id.q3Result);
        q3.setText(padding+Double.toString(round(myCalc.theQ3())));

        TextView lF = findViewById(R.id.lowerFenceResult);
        lF.setText(padding+Double.toString(round(myCalc.lowerFence())));

        TextView uF = findViewById(R.id.upperFenceResult);
        uF.setText(padding+Double.toString(round(myCalc.upperFence())));

        TextView m = findViewById(R.id.medianResult);
        m.setText(padding+Double.toString(round(myCalc.theMedian())));

        TextView sum = findViewById(R.id.sumResult);
        sum.setText(padding+Double.toString(round(myCalc.theSum())));

        TextView sum2 = findViewById(R.id.sum2Result);
        sum2.setText(padding+Double.toString(round(myCalc.theSumSquared())));

        TextView count = findViewById(R.id.countResult);
        count.setText(padding+Double.toString(round(myCalc.theCount())));

        TextView min = findViewById(R.id.minResult);
        min.setText(padding+Double.toString(round(myCalc.theMin())));

        TextView max = findViewById(R.id.maxResult);
        max.setText(padding+Double.toString(round(myCalc.theMax())));

        TextView lR = findViewById(R.id.linRegResult);
        lR.setText(padding+myCalc.regressionLineEquation());
    }

    private static double round(double value) {
        if (3 < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(3, RoundingMode.HALF_UP);
        return bd.doubleValue();
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
