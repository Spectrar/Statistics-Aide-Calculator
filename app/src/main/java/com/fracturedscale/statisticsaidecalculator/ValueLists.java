package com.fracturedscale.statisticsaidecalculator;

import android.content.SharedPreferences;
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

import java.util.ArrayList;

import static com.fracturedscale.statisticsaidecalculator.MainActivity.MYPREFS;

public class ValueLists extends AppCompatActivity implements View.OnClickListener{
    private LinearLayout l1;
    private LinearLayout l2;
    private LinearLayout l3;
    private LinearLayout l4;
    public static ArrayList l1List;
    public static ArrayList l2List;
    public static ArrayList l3List;
    public static ArrayList l4List;
    private SharedPreferences myPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value_lists);

        //adds action bar for back button in top left
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        myPref = getSharedPreferences(MYPREFS, 0);

        l1 = findViewById(R.id.l1);
        l2 = findViewById(R.id.l2);
        l3 = findViewById(R.id.l3);
        l4 = findViewById(R.id.l4);

        l1List = new ArrayList();
        l2List = new ArrayList();
        l3List = new ArrayList();
        l4List = new ArrayList();

        loadLists();

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
    public void onClick(View view) {
        //String temptext = Integer.toString(i);
        //Toast.makeText(ValueLists.this, temptext, Toast.LENGTH_SHORT).show();

        {//if (i==66){
            EditText temp = new EditText(this);
            int ii = Character.getNumericValue(view.getTag().toString().charAt(1));
            int j = Character.getNumericValue(view.getTag().toString().charAt(3));
            EditText temp2 = new EditText(this);

            switch ("l"+(ii)){
                case "l1":

                    if(l1List.size()-1==j){
                        makeEditText(ii,j+1,temp);
                        l1.addView(temp);
                        l1List.add("");
                    }else if(l1List.size()-2==j){
                        makeEditText(ii,j+2,temp);
                        l1.addView(temp);
                        l1List.add("");
                    }
                    temp2=l1.findViewWithTag("l"+(ii)+"r"+(j+1));
                    break;
                case "l2":

                    if(l2List.size()-1==j){
                        makeEditText(ii,j+1,temp);
                        l2.addView(temp);
                        l2List.add("");
                    }else if(l2List.size()-2==j){
                        makeEditText(ii,j+2,temp);
                        l2.addView(temp);
                        l2List.add("");
                    }
                    temp2=l2.findViewWithTag("l"+(ii)+"r"+(j+1));
                    break;
                case "l3":

                    if(l3List.size()-1==j){
                        makeEditText(ii,j+1,temp);
                        l3.addView(temp);
                        l3List.add("");
                    }else if(l3List.size()-2==j){
                        makeEditText(ii,j+2,temp);
                        l3.addView(temp);
                        l3List.add("");
                    }
                    temp2=l3.findViewWithTag("l"+(ii)+"r"+(j+1));
                    break;
                case "l4":

                    if(l4List.size()-1==j){
                        makeEditText(ii,j+1,temp);
                        l4.addView(temp);
                        l4List.add("");
                    }else if(l4List.size()-2==j){
                        makeEditText(ii,j+2,temp);
                        l4.addView(temp);
                        l4List.add("");
                    }
                    temp2=l4.findViewWithTag("l"+(ii)+"r"+(j+1));
                    break;
            }
            //view.clearFocus();


            temp2.requestFocus();


            Toast.makeText(ValueLists.this, view.getTag().toString(), Toast.LENGTH_SHORT).show();
        }

    }

    private void loadLists() {
        int[] lists= new int[4];
        for(int i = 1; i<5; i++){

            switch("l"+i){
                case "l1":
                    if(l1List.isEmpty() || l1List.size()==0){
                        lists[i-1]=2;
                        l1List.add("");
                        l1List.add("");
                    }else{
                        lists[i-1]=l1List.size();
                    }
                    break;
                case "l2":
                    if(l2List.isEmpty() || l2List.size()==0){
                        lists[i-1]=2;
                        l2List.add("");
                        l2List.add("");
                    }else{
                        lists[i-1]=l2List.size();
                    }
                    break;
                case "l3":
                    if(l3List.isEmpty() || l3List.size()==0){
                        lists[i-1]=2;
                        l3List.add("");
                        l3List.add("");
                    }else{
                        lists[i-1]=l3List.size();
                    }
                    break;
                case "l4":
                    if(l4List.isEmpty() || l4List.size()==0){
                        lists[i-1]=2;
                        l4List.add("");
                        l4List.add("");
                    }else{
                        lists[i-1]=l4List.size();
                    }
                    break;
            }
        }


        makeRows(lists);
    }

    private void makeRows(int [] rows){

        for(int i=0; i<4; i++) {
            for(int j=0; j<rows[i]; j++) {
                EditText temp = new EditText(this);
                makeEditText(i, j, temp);
                switch ("l"+(i)){
                    case "l1":
                        l1.addView(temp);
                        if(l1List.size()-1>=j){
                            temp.setText(l1List.get(j).toString());
                        }
                        break;
                    case "l2":
                        l2.addView(temp);
                        if(l2List.size()-1>=j){
                            temp.setText(l2List.get(j).toString());
                        }
                        break;
                    case "l3":
                        l3.addView(temp);
                        if(l3List.size()-1>=j){
                            temp.setText(l3List.get(j).toString());
                        }
                        break;
                    case "l4":
                        l4.addView(temp);
                        if(l4List.size()-1>=j){
                            temp.setText(l4List.get(j).toString());
                        }
                        break;
                }
            }
        }
    }

    private void makeEditText(int i, int j, EditText temp) {
        temp.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        temp.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        temp.setEms(10);
        temp.setTag("l"+(i)+"r"+(j));
//                View v;//
//                EditText temp = findViewById(
//                        this.getResources().getIdentifier("l"+(i+1)+"r"+(j+1),"id", this.getPackageName()));
        temp.setOnClickListener(this);
    }
}
