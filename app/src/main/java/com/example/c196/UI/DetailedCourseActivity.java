package com.example.c196.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

    String name;
    String start;
    String end;
    String note;
    String status;
    String iName;
    String iEmail;
    String iPhoneNumber;

    Button delete;

    boolean startWarning;
    boolean endWaring;



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
        instructorPhoneNumber = findViewById(R.id.instructorPhone);
        delete = findViewById(R.id.deleteCourse);
        courseID = getIntent().getIntExtra("id", 0);

        repo = new Repository(getApplication());
        if (courseID ==0){
            delete.setText("Cancel");
        }
        if (courseID != 0){
            name = getIntent().getStringExtra("name");
            start = getIntent().getStringExtra("start");
            end = getIntent().getStringExtra("end");
            note = getIntent().getStringExtra("note");
            status = getIntent().getStringExtra("status");
            iName = getIntent().getStringExtra("instructorName");
            iPhoneNumber = getIntent().getStringExtra("phone");
            iEmail = getIntent().getStringExtra("email");
            startWarning = getIntent().getBooleanExtra("startAlert",false);
            endWaring = getIntent().getBooleanExtra("endAlert",false);

            startAlert.setChecked(startWarning);
            endAlert.setChecked(endWaring);

            courseName.setText(name);
            courseStatus.setText(status);
            courseStartDate.setText(start);
            courseEndDate.setText(end);
            courseNote.setText(note);
            instructorName.setText(iName);
            instructorEmail.setText(iEmail);
            instructorPhoneNumber.setText(iPhoneNumber);
        }


    }

    public void onCourseSave(View view) {
        Courses courses;


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

    public void onCourseDelete(View view) {
        Courses courses;
        if (courseID != 0){
            courses = new Courses(courseID,courseName.getText().toString(),0,courseStatus.getText().toString(),
                    courseStartDate.getText().toString(),courseEndDate.getText().toString(),instructorName.getText().toString(),
                    instructorEmail.getText().toString(),instructorPhoneNumber.getText().toString(),courseNote.getText().toString(),startAlert.isChecked(),
                    endAlert.isChecked());
            repo.delete(courses);

        }

        Intent intent = new Intent(DetailedCourseActivity.this, CourseActivity.class);
        startActivity(intent);
    }

    public void onShareNote(View view) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT,courseNote.getText().toString());
        sendIntent.putExtra(Intent.EXTRA_TITLE,courseName.getText().toString()+" Note");
        sendIntent.setType("text/plain");
        Intent shareIntent = Intent.createChooser(sendIntent,null);
        startActivity(shareIntent);
    }
}