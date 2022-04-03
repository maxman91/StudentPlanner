package com.example.c196.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.c196.R;

public class AssessmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment);
    }
    public void enterDetailedAssessment(View view) {
        Intent intent = new Intent(AssessmentActivity.this, DetailedAssessmentActivity.class);
        startActivity(intent);
    }
}