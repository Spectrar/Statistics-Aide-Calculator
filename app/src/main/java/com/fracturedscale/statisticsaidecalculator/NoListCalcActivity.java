package com.fracturedscale.statisticsaidecalculator;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.text.StaticLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class NoListCalcActivity extends AppCompatActivity implements View.OnClickListener{

    StatsHelper sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_list_calc);

        //adds action bar for back button in top left
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sh = new StatsHelper();

        instantiateButtons();
    }

    private void instantiateButtons() {
        Button normalCDF = findViewById(R.id.nCDF);
        normalCDF.setOnClickListener(this);
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
    public void onClick(final View view) {
        final Dialog dialog = new Dialog(this);
        Button calcButton = null;
        Button cancelButton = null;
        switch(view.getId()) {
            case R.id.nCDF:
                dialog.setContentView(R.layout.normalcdfpopup);
                calcButton = (Button)dialog.findViewById(R.id.cdfcalculate);
                cancelButton = (Button)dialog.findViewById(R.id.cdfcancel);
                break;
        }


        dialog.setTitle("Save New Number");
        dialog.setCancelable(true);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        //lp.height = WindowManager.LayoutParams.MATCH_PARENT;

        dialog.getWindow().setAttributes(lp);
        dialog.show();

        calcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                    TextView temp;
                    switch(view.getId()){
                        case R.id.nCDF:
                            temp = findViewById(R.id.nCDFResults);
                            //temp.setText(String.valueOf(sh.normalCDF(0.0,3.0,0.0,1.0))+".499");
                            temp.setText(String.valueOf(sh.normalCDF(Double.valueOf(((EditText) dialog.findViewById(R.id.cdflower)).getText().toString())
                                    ,Double.valueOf(((EditText) dialog.findViewById(R.id.cdfupper)).getText().toString())
                                    ,Double.valueOf(((EditText) dialog.findViewById(R.id.cdfmean)).getText().toString())
                                    ,Double.valueOf(((EditText) dialog.findViewById(R.id.cdfsd)).getText().toString()))));
                            dialog.dismiss();
                            break;
                    }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                dialog.dismiss();
            }
        });

    }



}
