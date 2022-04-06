package com.example.c196.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.example.c196.R;

public class DetailedTermActivity extends AppCompatActivity {
    EditText termName;
    EditText termStarting;
    EditText termEnding;
    String name;
    String start;
    String end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_term);
        termName = findViewById(R.id.termNameID);

        termStarting = findViewById(R.id.termStart);
        termEnding = findViewById(R.id.termEnd);
        name = getIntent().getStringExtra("name");
        start = getIntent().getStringExtra("start");
        end = getIntent().getStringExtra("end");
        termName.setText(name);
        termStarting.setText(start);
        termEnding.setText(end);


    }
}