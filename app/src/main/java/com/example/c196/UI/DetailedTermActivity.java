package com.example.c196.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.c196.Database.Repository;
import com.example.c196.Entity.Terms;
import com.example.c196.R;

public class DetailedTermActivity extends AppCompatActivity {
    EditText termName;
    EditText termStarting;
    EditText termEnding;
    String name;
    String start;
    String end;
    int termID;
    Repository repo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_term);
        termName = findViewById(R.id.termNameID);
        termStarting = findViewById(R.id.termStart);
        termEnding = findViewById(R.id.termEnd);
        name = getIntent().getStringExtra("name");
        termID = getIntent().getIntExtra("id", 0);
        start = getIntent().getStringExtra("start");
        end = getIntent().getStringExtra("end");
        termName.setText(name);
        termStarting.setText(start);
        termEnding.setText(end);

        repo = new Repository(getApplication());



    }

    public void saveButton(View view) {
        Terms term;
        if (termID == 0){
            int newID = repo.getAllTerms().size() + 1;
            term = new Terms(newID,termName.getText().toString(), termStarting.getText().toString(),termEnding.getText().toString());
            repo.insert(term);
        }
        else {
            term = new Terms(termID,termName.getText().toString(), termStarting.getText().toString(),termEnding.getText().toString());
            repo.update(term);
        }
    }
}