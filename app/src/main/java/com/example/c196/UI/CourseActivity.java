package com.example.c196.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.c196.Database.Repository;
import com.example.c196.Entity.Assessments;
import com.example.c196.Entity.Courses;
import com.example.c196.Entity.Terms;
import com.example.c196.R;

import java.util.HashMap;
import java.util.List;

public class CourseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        RecyclerView recyclerView = findViewById(R.id.courseRecyle);
        Repository repo = new Repository(getApplication());
        List<Courses> courses = repo.getAllCourses();
        final CourseAdapter adapter = new CourseAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setCourses(courses);

        HashMap<Integer, Integer> courseMap = new HashMap<Integer, Integer>();

        for (Courses course :
                courses) {
            courseMap.put(course.getCourseID(), course.getCourseID());
        }
        List<Assessments> assessments = repo.getAllAssessments();
        for (Assessments assessment :
                assessments) {
            if (!courseMap.containsValue(assessment.getCourseAffiliate())) {
                Assessments assessmentFix = new Assessments(assessment.getAssessmentID(), assessment.getAssessmentName()
                        , assessment.getType(), assessment.getStart(), assessment.getEnd()
                        , assessment.isStartAlert(), assessment.isEndAlert(),
                        0);
                repo.update(assessmentFix);

            }

        }
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_course, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void enterDetailedCourse(View view) {
        Intent intent = new Intent(CourseActivity.this, DetailedCourseActivity.class);
        startActivity(intent);
    }
}