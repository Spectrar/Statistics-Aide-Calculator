package com.fracturedscale.statisticsaidecalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button singleListBtn = findViewById(R.id.singleList);
        singleListBtn.setOnClickListener(this);

        Button editListsBtn = findViewById(R.id.editListsBtn);
        editListsBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent i;
        switch(view.getId()){
            case R.id.singleList:
                i = new Intent(MainActivity.this,SingleList.class);
                startActivity(i);
                break;
            case R.id.editListsBtn:
                i = new Intent(MainActivity.this,ValueLists.class);
                startActivity(i);
                break;
        }
    }
}
