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

import java.math.BigDecimal;
import java.math.RoundingMode;

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

        Button binomPDF = findViewById(R.id.bPDF);
        binomPDF.setOnClickListener(this);

        Button invNorm = findViewById(R.id.invNorm);
        invNorm.setOnClickListener(this);

        Button invNormCI = findViewById(R.id.invNormCI);
        invNormCI.setOnClickListener(this);

        Button invT = findViewById(R.id.invT);
        invT.setOnClickListener(this);

        Button invTCI = findViewById(R.id.invTCI);
        invTCI.setOnClickListener(this);
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
            case R.id.bPDF:
                dialog.setContentView(R.layout.binompdfpopup);
                calcButton = (Button)dialog.findViewById(R.id.pdfcalculate);
                cancelButton = (Button)dialog.findViewById(R.id.pdfcancel);
                break;
            case R.id.invNorm:
                dialog.setContentView(R.layout.invnormpopup);
                calcButton = (Button)dialog.findViewById(R.id.invncalculate);
                cancelButton = (Button)dialog.findViewById(R.id.invncancel);
//                TextView temp1 = findViewById(R.id.invNormResults);
//                temp1.setText(String.valueOf(sh.invNorm(.025,0,1)));
                break;
            case R.id.invNormCI:
                dialog.setContentView(R.layout.invnormcipopup);
                calcButton = (Button)dialog.findViewById(R.id.invnCIcalculate);
                cancelButton = (Button)dialog.findViewById(R.id.invnCIcancel);
                break;
            case R.id.invT:
                dialog.setContentView(R.layout.invtpopup);
                calcButton = (Button)dialog.findViewById(R.id.invtcalculate);
                cancelButton = (Button)dialog.findViewById(R.id.invtcancel);
//                TextView temp1 = findViewById(R.id.invNormResults);
//                temp1.setText(String.valueOf(sh.invNorm(.025,0,1)));
                break;
            case R.id.invTCI:
                dialog.setContentView(R.layout.invntcipopup);
                calcButton = (Button)dialog.findViewById(R.id.invtCIcalculate);
                cancelButton = (Button)dialog.findViewById(R.id.invtCIcancel);
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
                StringBuilder sb;
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
                        case R.id.bPDF:
                            temp = findViewById(R.id.bPDFResults);
                            temp.setText(String.valueOf(sh.binomPDF(Double.valueOf(((EditText) dialog.findViewById(R.id.pdfTrials)).getText().toString())
                                    ,Double.valueOf(((EditText) dialog.findViewById(R.id.pdfP)).getText().toString())
                                    ,Double.valueOf(((EditText) dialog.findViewById(R.id.pdfX)).getText().toString()))));
                            dialog.dismiss();
                            break;
                        case R.id.invNorm:
                            temp = findViewById(R.id.invNormResults);
                            temp.setText(String.valueOf(sh.invNorm(Double.valueOf(((EditText) dialog.findViewById(R.id.invnArea)).getText().toString())
                                    ,Double.valueOf(((EditText) dialog.findViewById(R.id.invnMean)).getText().toString())
                                    ,Double.valueOf(((EditText) dialog.findViewById(R.id.invnsd)).getText().toString()))));
                            dialog.dismiss();
                            break;
                        case R.id.invNormCI:
                            temp = findViewById(R.id.invNormCIResults);
                            double eN = (sh.invNormConfidenceInterval(Double.valueOf(((EditText) dialog.findViewById(R.id.invnCIInv)).getText().toString())
                                    ,Double.valueOf(((EditText) dialog.findViewById(R.id.invnCIP)).getText().toString())
                                    ,Double.valueOf(((EditText) dialog.findViewById(R.id.invnCIN)).getText().toString())));
                            sb = new StringBuilder();
                            sb.append("E= ");
                            sb.append(eN);
                            sb.append("\nInterval: ");
                            sb.append(round(Double.valueOf(((EditText) dialog.findViewById(R.id.invnCIP)).getText().toString())-eN));
                            sb.append(" <P< ");
                            sb.append(round(eN+Double.valueOf(((EditText) dialog.findViewById(R.id.invnCIP)).getText().toString())));
                            temp.setText(sb.toString());
                            dialog.dismiss();
                            break;
                        case R.id.invT:
                            temp = findViewById(R.id.invTResults);
                            temp.setText(String.valueOf(sh.invT(Double.valueOf(((EditText) dialog.findViewById(R.id.invtArea)).getText().toString())
                                    ,Double.valueOf(((EditText) dialog.findViewById(R.id.invtDF)).getText().toString()))));
                            dialog.dismiss();
                            break;
                        case R.id.invTCI:
                            temp = findViewById(R.id.invTCIResults);
                            double eT = (sh.invTConfidenceInterval(Double.valueOf(((EditText) dialog.findViewById(R.id.invtCIInv)).getText().toString())
                                    ,Double.valueOf(((EditText) dialog.findViewById(R.id.invtCISD)).getText().toString())
                                    ,Double.valueOf(((EditText) dialog.findViewById(R.id.invtCIN)).getText().toString())));
                            sb = new StringBuilder();
                            sb.append("E= ");
                            sb.append(eT);
                            sb.append("\nInterval: ");
                            sb.append(round(Double.valueOf(((EditText) dialog.findViewById(R.id.invtCIMean)).getText().toString())-eT));
                            sb.append(" <P< ");
                            sb.append(round(eT+Double.valueOf(((EditText) dialog.findViewById(R.id.invtCIMean)).getText().toString())));
                            temp.setText(sb.toString());
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

    private double round(double value) {
        if (3 < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(4, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }



}
