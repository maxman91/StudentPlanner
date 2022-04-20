package com.example.c196.UI;

import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.c196.Database.Repository;
import com.example.c196.Entity.Assessments;
import com.example.c196.Entity.Terms;
import com.example.c196.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DetailedAssessmentActivity extends AppCompatActivity {
    RadioButton performance;
    RadioButton objective;
    EditText assessmentName;
    EditText startDate;
    EditText endDate;

    int assessmentID;
    String name;
    String start;
    String end;
    String type;
    boolean endWaring;
    boolean startWarning;


    CheckBox startAlert;
    CheckBox endAlert;
    Repository repo;

    DatePickerDialog.OnDateSetListener startingDate;
    DatePickerDialog.OnDateSetListener endingDate;
    final Calendar calendar = Calendar.getInstance();
    String dateFormat = "MM/dd/yy";
    SimpleDateFormat SDFormat = new SimpleDateFormat(dateFormat, Locale.US);

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
        assessmentID = getIntent().getIntExtra("id", 0);

        if (assessmentID !=0){
        name = getIntent().getStringExtra("name");
        start = getIntent().getStringExtra("start");
        end = getIntent().getStringExtra("end");
        type = getIntent().getStringExtra("type");
        startWarning = getIntent().getBooleanExtra("startAlert",false);
        endWaring = getIntent().getBooleanExtra("endAlert",false);

        startAlert.setChecked(startWarning);
        endAlert.setChecked(endWaring);


        assessmentName.setText(name);
        startDate.setText(start);
        endDate.setText(end);

        if (type.equals("Objective")){
            objective.setChecked(true);
        }
        else if (type.equals("Performance")){
            performance.setChecked(true);
        }
        }




        if (assessmentID ==0) {
            delete.setText("Cancel");
        }

        startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date date;
                String info = startDate.getText().toString();

                new DatePickerDialog(DetailedAssessmentActivity.this, startingDate, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

        endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date date;
                String info = endDate.getText().toString();

                new DatePickerDialog(DetailedAssessmentActivity.this, endingDate, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

        startingDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendar.set(Calendar.YEAR, i);
                calendar.set(Calendar.MONTH, i1);
                calendar.set(Calendar.DAY_OF_MONTH, i2);
                startDate.setText(SDFormat.format(calendar.getTime()));
            }
        };

        endingDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendar.set(Calendar.YEAR, i);
                calendar.set(Calendar.MONTH, i1);
                calendar.set(Calendar.DAY_OF_MONTH, i2);
                endDate.setText(SDFormat.format(calendar.getTime()));
            }
        };

    }

    public void onPerformance(View view) {
        objective.setChecked(false);
    }

    public void onObjective(View view) {
        performance.setChecked(false);
    }

    public void onAssessmentSave(View view) {
        String error = null;
        try {
        if (assessmentName.getText().toString().isEmpty()){
            error = "Assessment title field can't be left blank.";
            throw new NumberFormatException();
        }
            if (startDate.getText().toString().isEmpty()){
                error = "Assessment start date field can't be left blank.";
                throw new NumberFormatException();
            }
            if (endDate.getText().toString().isEmpty()){
                error = "Assessment end date field can't be left blank.";
                throw new NumberFormatException();
            }

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

        if (startAlert.isChecked()){
            String startTimeString = startDate.getText().toString();
            Date startTimeObject = null;
            try {
                startTimeObject = SDFormat.parse(startTimeString);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Long trigger=startTimeObject.getTime();
            Intent IntentStart = new Intent(DetailedAssessmentActivity.this, receiver.class);
            IntentStart.putExtra("key","Assessment: "+assessmentName.getText().toString()+" begins today.");
            PendingIntent sender=PendingIntent.getBroadcast(DetailedAssessmentActivity.this,MainActivity.Alert++,IntentStart,0);
            AlarmManager alarmManager=(AlarmManager) getSystemService(Context.ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP,trigger,sender);
        }
        if (endAlert.isChecked()){
            String startTimeString = endDate.getText().toString();
            Date startTimeObject = null;
            try {
                startTimeObject = SDFormat.parse(startTimeString);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Long trigger=startTimeObject.getTime();
            Intent IntentStart = new Intent(DetailedAssessmentActivity.this, receiver.class);
            IntentStart.putExtra("key","Assessment: "+assessmentName.getText().toString()+" ends today.");
            PendingIntent sender=PendingIntent.getBroadcast(DetailedAssessmentActivity.this,MainActivity.Alert++,IntentStart,0);
            AlarmManager alarmManager=(AlarmManager) getSystemService(Context.ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP,trigger,sender);
        }

        } catch (NumberFormatException E){
            Toast.makeText(DetailedAssessmentActivity.this, error, Toast.LENGTH_LONG).show();

        }

    }

    public void onAssessmentDelete(View view) {
        Assessments assessment;
        if (assessmentID != 0){
            assessment = new Assessments(assessmentID,assessmentName.getText().toString(),type,
                    startDate.getText().toString(),endDate.getText().toString(),startAlert.isChecked(),
                    endAlert.isChecked(),0);
            repo.delete(assessment);

        }

        Intent intent = new Intent(DetailedAssessmentActivity.this, AssessmentActivity.class);
        startActivity(intent);

    }
}