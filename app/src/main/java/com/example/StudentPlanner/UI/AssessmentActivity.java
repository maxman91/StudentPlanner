package com.example.StudentPlanner.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.StudentPlanner.Database.Repository;
import com.example.StudentPlanner.Entity.Assessments;
import com.example.StudentPlanner.R;

import java.util.List;

public class AssessmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        RecyclerView recyclerView = findViewById(R.id.assessmentRecycler);
        Repository repo = new Repository(getApplication());
        List<Assessments> assessments = repo.getAllAssessments();
        final AssessmentAdapter adapter = new AssessmentAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setAssessments(assessments);

    }
    public void enterDetailedAssessment(View view) {
        Intent intent = new Intent(AssessmentActivity.this, DetailedAssessmentActivity.class);
        startActivity(intent);
    }
}