package com.example.StudentPlanner.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.StudentPlanner.Database.Repository;
import com.example.StudentPlanner.Entity.Terms;
import com.example.StudentPlanner.R;

public class MainActivity extends AppCompatActivity {

    public static int Alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void enterCourses(View view) {
        Intent intent = new Intent(MainActivity.this, CourseActivity.class);
        startActivity(intent);

    }

    public void enterAssessments(View view) {
        Intent intent = new Intent(MainActivity.this, AssessmentActivity.class);
        startActivity(intent);
    }

    public void enterTerms(View view) {
        Intent intent = new Intent(MainActivity.this, TermActivity.class);
        startActivity(intent);
        Repository repo = new Repository(getApplication());
        Terms term = new Terms(1,"","","");
        //repo.insert(term);
        //repo.delete(term);
    }
}