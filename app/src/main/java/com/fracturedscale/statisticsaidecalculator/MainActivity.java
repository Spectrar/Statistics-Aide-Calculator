package com.fracturedscale.statisticsaidecalculator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    final static String MYPREFS = "MyPreferences_001";
    private SharedPreferences myPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myPref = getSharedPreferences(MainActivity.MYPREFS, 0);

        Button singleListBtn = findViewById(R.id.singleList);
        singleListBtn.setOnClickListener(this);

        Button editListsBtn = findViewById(R.id.editListsBtn);
        editListsBtn.setOnClickListener(this);

        Button noListBtn = findViewById(R.id.noList);
        noListBtn.setOnClickListener(this);


        ValueLists.l1List=loadArray("List 1");
        ValueLists.l2List=loadArray("List 2");
        ValueLists.l3List=loadArray("List 3");
        ValueLists.l4List=loadArray("List 4");
    }

    private ArrayList<Double> loadArray(final String listName){
        ArrayList<Double> temp = new ArrayList<Double>() {

            @Override
            public String toString() {
                return listName;
            }
        };

        Type collectionType = new TypeToken<ArrayList<Double>>() {
        }.getType();
        if (!myPref.getString(listName, "").equals("")) {
            temp.addAll((ArrayList) new Gson().fromJson(myPref.getString(listName, ""), collectionType));
            return temp;
        } else {
            return temp;
        }
    }

    @Override
    public void onClick(View view) {
        Intent i;
        switch (view.getId()) {
            case R.id.singleList:
                i = new Intent(MainActivity.this, ListCalc.class);
                startActivity(i);
                break;
            case R.id.editListsBtn:
                i = new Intent(MainActivity.this, ValueLists.class);
                startActivity(i);
                break;
            case R.id.noList:
                i = new Intent(MainActivity.this, NoListCalcActivity.class);
                startActivity(i);
                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

    }
}
