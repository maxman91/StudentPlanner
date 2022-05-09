package com.example.StudentPlanner.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.StudentPlanner.Database.Repository;
import com.example.StudentPlanner.Entity.Assessments;
import com.example.StudentPlanner.Entity.Courses;
import com.example.StudentPlanner.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

    DatePickerDialog.OnDateSetListener startingDate;
    DatePickerDialog.OnDateSetListener endingDate;
    final Calendar calendar = Calendar.getInstance();

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

    String dateFormat = "MM/dd/yy";
    SimpleDateFormat SDFormat = new SimpleDateFormat(dateFormat, Locale.US);

    List<Assessments> filteredAssessments = new ArrayList<>();



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



        courseStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date date;
                String info = courseStartDate.getText().toString();

                new DatePickerDialog(DetailedCourseActivity.this, startingDate, calendar
                .get(Calendar.YEAR), calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

        courseEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date date;
                String info = courseEndDate.getText().toString();

                new DatePickerDialog(DetailedCourseActivity.this, endingDate, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });



        startingDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendar.set(Calendar.YEAR, i);
                calendar.set(Calendar.MONTH, i1);
                calendar.set(Calendar.DAY_OF_MONTH, i2);
                courseStartDate.setText(SDFormat.format(calendar.getTime()));
            }
        };

        endingDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendar.set(Calendar.YEAR, i);
                calendar.set(Calendar.MONTH, i1);
                calendar.set(Calendar.DAY_OF_MONTH, i2);
                courseEndDate.setText(SDFormat.format(calendar.getTime()));
            }
        };

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
        RecyclerView recyclerView = findViewById(R.id.AssessmentFilteredRecycler);
        Repository repo = new Repository(getApplication());
        List<Assessments> assessments = repo.getAllAssessments();
        for (Assessments assessment:
                assessments) {
            if (assessment.getCourseAffiliate()==0||assessment.getCourseAffiliate()==courseID){
                filteredAssessments.add(assessment);
            }
        }
        if (courseID == 0){
            int   newID = 1;
            if (repo.getAllCourses().size()>0){
                newID = repo.getAllCourses().get(repo.getAllCourses().size()-1).getCourseID()+1;
            }
            final FilteredAssessmentAdapter adapter = new FilteredAssessmentAdapter(this,newID,repo);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter.setAssessments(filteredAssessments);
        } else {
            final FilteredAssessmentAdapter adapter = new FilteredAssessmentAdapter(this,courseID,repo);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter.setAssessments(filteredAssessments);
        }


    }

    public void onCourseSave(View view) {
        String error = null;
        try {


        Courses courses;

        if (courseName.getText().toString().isEmpty()){
            error = "Course title field can't be left blank.";
            throw new NumberFormatException();
        }
            if (courseStartDate.getText().toString().isEmpty()){
                error = "Course starting date field can't be left blank.";
                throw new NumberFormatException();
            }
            if (courseEndDate.getText().toString().isEmpty()){
                error = "Course ending date field can't be left blank.";
                throw new NumberFormatException();
            }
            if (courseStatus.getText().toString().isEmpty()){
                error = "Course status field can't be left blank.";
                throw new NumberFormatException();
            }

            if (instructorName.getText().toString().isEmpty()){
                error = "Course instructor name field can't be left blank.";
                throw new NumberFormatException();
            }
            if (instructorEmail.getText().toString().isEmpty()){
                error = "Course instructor email field can't be left blank.";
                throw new NumberFormatException();
            }
            if (instructorPhoneNumber.getText().toString().isEmpty()){
                error = "Course instructor phone number field can't be left blank.";
                throw new NumberFormatException();
            }

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

        if (startAlert.isChecked()){
        String startTimeString = courseStartDate.getText().toString();
        Date startTimeObject = null;
        try {
             startTimeObject = SDFormat.parse(startTimeString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Long trigger=startTimeObject.getTime();
        Intent IntentStart = new Intent(DetailedCourseActivity.this, receiver.class);
        IntentStart.putExtra("key","Course: "+courseName.getText().toString()+" begins today.");
        PendingIntent sender=PendingIntent.getBroadcast(DetailedCourseActivity.this,MainActivity.Alert++,IntentStart,0);
        AlarmManager alarmManager=(AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP,trigger,sender);
        }
        if (endAlert.isChecked()){
            String startTimeString = courseEndDate.getText().toString();
            Date startTimeObject = null;
            try {
                startTimeObject = SDFormat.parse(startTimeString);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Long trigger=startTimeObject.getTime();
            Intent IntentStart = new Intent(DetailedCourseActivity.this, receiver.class);
            IntentStart.putExtra("key","Course: "+courseName.getText().toString()+" ends today.");
            PendingIntent sender=PendingIntent.getBroadcast(DetailedCourseActivity.this,MainActivity.Alert++,IntentStart,0);
            AlarmManager alarmManager=(AlarmManager) getSystemService(Context.ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP,trigger,sender);
        }


        Intent intent = new Intent(DetailedCourseActivity.this, CourseActivity.class);
        startActivity(intent);
        }   catch (NumberFormatException E){
        Toast.makeText(DetailedCourseActivity.this, error, Toast.LENGTH_LONG).show();

    }
    }

    public void onCourseDelete(View view) {
        Courses courses;
        if (courseID != 0){
            courses = new Courses(courseID,courseName.getText().toString(),0,courseStatus.getText().toString(),
                    courseStartDate.getText().toString(),courseEndDate.getText().toString(),instructorName.getText().toString(),
                    instructorEmail.getText().toString(),instructorPhoneNumber.getText().toString(),courseNote.getText().toString(),startAlert.isChecked(),
                    endAlert.isChecked());
            repo.delete(courses);
            Toast.makeText(DetailedCourseActivity.this, "Course successfully deleted!", Toast.LENGTH_LONG).show();

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