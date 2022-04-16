package com.example.c196.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.c196.Database.Repository;
import com.example.c196.Entity.Courses;
import com.example.c196.Entity.Terms;
import com.example.c196.R;

import java.util.HashMap;
import java.util.List;

public class TermActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        RecyclerView recyclerView = findViewById(R.id.recycleTerms);
        Repository repo = new Repository(getApplication());
        List<Terms> terms = repo.getAllTerms();
        List<Courses> courses = repo.getAllCourses();
        final TermAdapter adapter = new TermAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setTerms(terms);

        HashMap<Integer, Integer> termMap = new HashMap<Integer, Integer>();

        for (Terms term:
            terms) {
            termMap.put(term.getTermID(),term.getTermID());
        }

        for (Courses course:
             courses) {
            if (!termMap.containsValue(course.getTermAffiliation())){
                Courses courseFix = new Courses(course.getCourseID(), course.getCourseName(), 0, course.getStatus(),
                        course.getStart(), course.getFinish(), course.getInstructorName(),
                        course.getInstructorEmail(), course.getInstructorPhoneNumber(), course.getNote(),
                        course.isStartAlert(), course.isEndAlert());
                repo.update(courseFix);

            }

        }







    }

    public void enterDetailedTerm(View view) {
        Intent intent = new Intent(TermActivity.this, DetailedTermActivity.class);
        startActivity(intent);
    }
}