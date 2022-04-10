package com.example.c196.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.c196.Database.Repository;
import com.example.c196.Entity.Assessments;
import com.example.c196.Entity.Courses;
import com.example.c196.R;

public class DetailedCourseActivity extends AppCompatActivity {
    EditText courseName;
    EditText courseStartDate;
    EditText courseEndDate;
    EditText courseStatus;
    EditText courseNote;
    EditText instructorName;
    EditText instructorEmail;
    EditText instructorPhoneNumber;
    CheckBox startAlert;
    CheckBox endAlert;
    Repository repo;

    int courseID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_course);
        courseName = findViewById(R.id.courseTitle);
        courseStartDate = findViewById(R.id.courseStartDate);
        courseEndDate = findViewById(R.id.courseEndDate);
        courseStatus = findViewById(R.id.courseProgress);
        courseNote = findViewById(R.id.courseNote);
        startAlert = findViewById(R.id.courseStartDateAlert);
        endAlert = findViewById(R.id.courseEndDateAlert);
        instructorName = findViewById(R.id.instructorName);
        instructorEmail = findViewById(R.id.instructorEmail);
        instructorPhoneNumber = findViewById(R.id.instructorEmail);
        courseID = 0; //temp

        repo = new Repository(getApplication());

    }

    public void onCourseSave(View view) {
        Courses courses;
        String type = "";

        int   newID = 1;
        if (courseID == 0){
            if (repo.getAllCourses().size()>0){
                newID = repo.getAllCourses().get(repo.getAllCourses().size()-1).getCourseID()+1;
            }

            courses = new Courses(newID,courseName.getText().toString(),0,courseStatus.getText().toString(),
                    courseStartDate.getText().toString(),courseEndDate.getText().toString(),instructorName.getText().toString(),
                    instructorEmail.getText().toString(),instructorPhoneNumber.getText().toString(),courseNote.getText().toString(),startAlert.isChecked(),
                    endAlert.isChecked());
            repo.insert(courses);
        }
        else {
            courses = new Courses(courseID,courseName.getText().toString(),0,courseStatus.getText().toString(),
                    courseStartDate.getText().toString(),courseEndDate.getText().toString(),instructorName.getText().toString(),
                    instructorEmail.getText().toString(),instructorPhoneNumber.getText().toString(),courseNote.getText().toString(),startAlert.isChecked(),
                    endAlert.isChecked());
            repo.update(courses);
        }

        Intent intent = new Intent(DetailedCourseActivity.this, CourseActivity.class);
        startActivity(intent);
    }
}