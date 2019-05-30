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

        Type collectionType = new TypeToken<ArrayList<Double>>() {
        }.getType();
        if (!myPref.getString("List1", "").equals("")) {
            ValueLists.l1List = new Gson().fromJson(myPref.getString("List1", "").toString(), collectionType);
        } else {
            ValueLists.l1List = new ArrayList<Double>() {
                private static final long serialVersionUID = 1L;

                @Override
                public String toString() {
                    return "List 1";
                }
            };
        }
        if (!myPref.getString("List2", "").equals("")) {
            ValueLists.l2List = new Gson().fromJson(myPref.getString("List2", ""), collectionType);
        } else {
            ValueLists.l2List = new ArrayList<Double>() {
                private static final long serialVersionUID = 1L;

                @Override
                public String toString() {
                    return "List 2";
                }
            };
        }
        if (!myPref.getString("List3", "").equals("")) {
            ValueLists.l3List = new Gson().fromJson(myPref.getString("List3", ""), collectionType);
        } else {
            ValueLists.l3List = new ArrayList<Double>() {
                private static final long serialVersionUID = 1L;

                @Override
                public String toString() {
                    return "List 3";
                }
            };
        }
        if (!myPref.getString("List4", "").equals("")) {
            ValueLists.l4List = new Gson().fromJson(myPref.getString("List4", ""), collectionType);
        } else {
            ValueLists.l4List = new ArrayList<Double>() {
                private static final long serialVersionUID = 1L;

                @Override
                public String toString() {
                    return "List 4";
                }
            };
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
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

    }
}
