package com.example.StudentPlanner.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.StudentPlanner.Database.Repository;
import com.example.StudentPlanner.Entity.Courses;
import com.example.StudentPlanner.Entity.Terms;
import com.example.StudentPlanner.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DetailedTermActivity extends AppCompatActivity {
    EditText termName;
    EditText termStarting;
    EditText termEnding;
    String name;
    String start;
    String end;
    int termID;
    Repository repo;
    List<Courses> filteredCourses = new ArrayList<>();
    List<Courses> coursesCheck = new ArrayList<>();


    DatePickerDialog.OnDateSetListener startingDate;
    DatePickerDialog.OnDateSetListener endingDate;
    final Calendar calendar = Calendar.getInstance();
    String dateFormat = "MM/dd/yy";
    SimpleDateFormat SDFormat = new SimpleDateFormat(dateFormat, Locale.US);

    Button delete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_term);
        termName = findViewById(R.id.assessmentName);
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
        termStarting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date date;
                String info = termStarting.getText().toString();

                new DatePickerDialog(DetailedTermActivity.this, startingDate, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

        termEnding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date date;
                String info = termEnding.getText().toString();

                new DatePickerDialog(DetailedTermActivity.this, endingDate, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

        startingDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendar.set(Calendar.YEAR, i);
                calendar.set(Calendar.MONTH, i1);
                calendar.set(Calendar.DAY_OF_MONTH, i2);
                termStarting.setText(SDFormat.format(calendar.getTime()));
            }
        };

        endingDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendar.set(Calendar.YEAR, i);
                calendar.set(Calendar.MONTH, i1);
                calendar.set(Calendar.DAY_OF_MONTH, i2);
                termEnding.setText(SDFormat.format(calendar.getTime()));
            }
        };


        RecyclerView recyclerView = findViewById(R.id.CoursesFilteredRecycler);
        Repository repo = new Repository(getApplication());
        List<Courses> courses = repo.getAllCourses();
        for (Courses course:
                courses) {
            if (course.getTermAffiliation()==0||course.getTermAffiliation()==termID){
                filteredCourses.add(course);
            }
        }
        if (termID == 0){
            int   newID = 1;
            if (repo.getAllTerms().size()>0){
                newID = repo.getAllTerms().get(repo.getAllTerms().size()-1).getTermID()+1;
            }
            final FilteredCourseAdapter adapter = new FilteredCourseAdapter(this,newID,repo);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter.setCourses(filteredCourses);
        } else {
            final FilteredCourseAdapter adapter = new FilteredCourseAdapter(this,termID,repo);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter.setCourses(filteredCourses);
        }

    }

    public void saveButton(View view) {
        String error = null;
        try {
            Terms term;
            
            if (termName.getText().toString().isEmpty()){
                error = "Term title field can't be left blank.";
                throw new NumberFormatException();
            }
            if (termStarting.getText().toString().isEmpty()){
                error = "Term starting date field can't be left blank.";
                throw new NumberFormatException();
            }
            if (termEnding.getText().toString().isEmpty()){
                error = "Term ending date field can't be left blank.";
                throw new NumberFormatException();
            }
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
        } catch (NumberFormatException E){
            Toast.makeText(DetailedTermActivity.this, error, Toast.LENGTH_LONG).show();

        }

    }

    public void deleteButton(View view) {
        Terms term;
        List<Courses> courses = repo.getAllCourses();
        coursesCheck.clear();


        if (termID !=0){
            for (Courses course:
                    courses) {
                if (course.getTermAffiliation()==termID){
                    coursesCheck.add(course);
                }
            }
            if (coursesCheck.size()>0){
                Toast.makeText(DetailedTermActivity.this, "Courses must be removed from the term before deleting.", Toast.LENGTH_LONG).show();
            }
            else {
        term = new Terms(termID,termName.getText().toString(), termStarting.getText().toString(),termEnding.getText().toString());
        repo.delete(term);
                Toast.makeText(DetailedTermActivity.this, "Term successfully deleted!", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(DetailedTermActivity.this, TermActivity.class);
                startActivity(intent);
            }
        }

        if (termID ==0){
            Intent intent = new Intent(DetailedTermActivity.this, TermActivity.class);
            startActivity(intent);
        }

    }
}