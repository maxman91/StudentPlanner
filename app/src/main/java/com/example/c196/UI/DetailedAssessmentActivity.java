package com.example.c196.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.c196.Database.Repository;
import com.example.c196.Entity.Assessments;
import com.example.c196.Entity.Terms;
import com.example.c196.R;

public class DetailedAssessmentActivity extends AppCompatActivity {
    RadioButton performance;
    RadioButton objective;
    EditText assessmentName;
    EditText startDate;
    EditText endDate;
    CheckBox startAlert;
    CheckBox endAlert;
    Repository repo;

    int assessmentID;
    Button delete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_assessment);
        performance = findViewById(R.id.radioPerformance);
        objective = findViewById(R.id.radioObjective);
        assessmentName = findViewById(R.id.assessmentName);
        startDate = findViewById(R.id.assessmentStart);
        endDate = findViewById(R.id.assessmentEnd);
        startAlert = findViewById(R.id.AssessmentStartAlert);
        endAlert = findViewById(R.id.AssessmentEndAlert);
        delete = findViewById(R.id.assessmentDelete);
        repo = new Repository(getApplication());
        assessmentID = 0; //default test

        if (assessmentID ==0) {
            delete.setText("Cancel");
        }
    }

    public void onPerformance(View view) {
        objective.setChecked(false);
    }

    public void onObjective(View view) {
        performance.setChecked(false);
    }

    public void onAssessmentSave(View view) {
        Assessments assessment;
        String type = "";
        if (performance.isChecked()){
            type = "Performance";
        }
        if (objective.isChecked()){
            type = "Objective";
        }
        int   newID = 1;
        if (assessmentID == 0){
            if (repo.getAllAssessments().size()>0){
                newID = repo.getAllAssessments().get(repo.getAllAssessments().size()-1).getAssessmentID()+1;
            }

            assessment = new Assessments(newID,assessmentName.getText().toString(),type,
                    startDate.getText().toString(),endDate.getText().toString(),startAlert.isChecked(),
                    endAlert.isChecked(),0);
            repo.insert(assessment);
        }
        else {
            assessment = new Assessments(assessmentID,assessmentName.getText().toString(),type,
                    startDate.getText().toString(),endDate.getText().toString(),startAlert.isChecked(),
                    endAlert.isChecked(),0);
            repo.update(assessment);
        }

        Intent intent = new Intent(DetailedAssessmentActivity.this, AssessmentActivity.class);
        startActivity(intent);

    }
}