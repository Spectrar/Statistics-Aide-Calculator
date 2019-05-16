package com.fracturedscale.statisticsaidecalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class ValueLists extends AppCompatActivity implements View.OnKeyListener{
    private LinearLayout l1;
    private LinearLayout l2;
    private LinearLayout l3;
    private LinearLayout l4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value_lists);

        //adds action bar for back button in top left
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        l1 = findViewById(R.id.l1);
        l2 = findViewById(R.id.l2);
        l3 = findViewById(R.id.l3);
        l4 = findViewById(R.id.l4);

        int[] newLists = {2,2,2,2};
        makeRows(newLists);

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
        //Toast.makeText(ValueLists.this, temp, Toast.LENGTH_SHORT).show();
        if (i==66){
            Toast.makeText(ValueLists.this, "Next "+ view.getTag(), Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    private void makeRows(int [] rows){

        for(int i=0; i<4; i++) {
            for(int j=0; j<rows[i]; j++) {
                EditText temp = new EditText(this);
                temp.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                temp.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                temp.setEms(10);
                temp.setTag("l"+(i+1)+"r"+(j+1));
//                View v;
//                EditText temp = findViewById(
//                        this.getResources().getIdentifier("l"+(i+1)+"r"+(j+1),"id", this.getPackageName()));
                temp.setOnKeyListener(this);
                switch ("l"+(i+1)){
                    case "l1":
                        l1.addView(temp);
                        break;
                    case "l2":
                        l2.addView(temp);
                        break;
                    case "l3":
                        l3.addView(temp);
                        break;
                    case "l4":
                        l4.addView(temp);
                        break;
                }
            }
        }
    }
}
