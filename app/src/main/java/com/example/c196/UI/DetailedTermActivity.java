package com.example.c196.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

    Button delete;


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

        delete = findViewById(R.id.termDelete);

        repo = new Repository(getApplication());

        if (termID == 0){
            delete.setText("Cancel");
        }



    }

    public void saveButton(View view) {
        Terms term;
        int   newID = 1;
        if (termID == 0){
            if (repo.getAllTerms().size()>0){
                newID = repo.getAllTerms().get(repo.getAllTerms().size()-1).getTermID()+1;
            }

            term = new Terms(newID,termName.getText().toString(), termStarting.getText().toString(),termEnding.getText().toString());
            repo.insert(term);
        }
        else {
            term = new Terms(termID,termName.getText().toString(), termStarting.getText().toString(),termEnding.getText().toString());
            repo.update(term);
        }

        Intent intent = new Intent(DetailedTermActivity.this, TermActivity.class);
        startActivity(intent);
    }

    public void deleteButton(View view) {
        Terms term;
        if (termID !=0){
        term = new Terms(termID,termName.getText().toString(), termStarting.getText().toString(),termEnding.getText().toString());
        repo.delete(term);}
        Intent intent = new Intent(DetailedTermActivity.this, TermActivity.class);
        startActivity(intent);

    }
}