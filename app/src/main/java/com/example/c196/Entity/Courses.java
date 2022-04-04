package com.example.c196.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;



@Entity(tableName = "Courses")
public class Courses {
    public Courses(int courseID, String courseName, int termAffiliation, String status, String start, String finish, String instructorName, String instructorEmail, String instructorPhoneNumber, boolean startAlert, boolean endAlert) {
        this.courseID = courseID;
        CourseName = courseName;
        this.termAffiliation = termAffiliation;
        this.status = status;
        this.start = start;
        this.finish = finish;
        this.instructorName = instructorName;
        this.instructorEmail = instructorEmail;
        this.instructorPhoneNumber = instructorPhoneNumber;
        this.startAlert = startAlert;
        this.endAlert = endAlert;
    }

    @PrimaryKey(autoGenerate = true)
    private int courseID;
    private String CourseName;
    private int termAffiliation;
    private String status;
    private String start;
    private String finish;
    private String instructorName;
    private String instructorEmail;
    private String instructorPhoneNumber;
    private boolean startAlert;
    private boolean endAlert;

    public Courses() {

    }


    @Override
    public String toString() {
        return "Courses{" +
                "courseID=" + courseID +
                ", CourseName='" + CourseName + '\'' +
                ", termAffiliation=" + termAffiliation +
                ", status='" + status + '\'' +
                ", start=" + start +
                ", finish=" + finish +
                ", instructorName='" + instructorName + '\'' +
                ", instructorEmail='" + instructorEmail + '\'' +
                ", instructorPhoneNumber='" + instructorPhoneNumber + '\'' +
                ", startAlert=" + startAlert +
                ", endAlert=" + endAlert +
                '}';
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String courseName) {
        CourseName = courseName;
    }

    public int getTermAffiliation() {
        return termAffiliation;
    }

    public void setTermAffiliation(int termAffiliation) {
        this.termAffiliation = termAffiliation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getFinish() {
        return finish;
    }

    public void setFinish(String finish) {
        this.finish = finish;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public String getInstructorEmail() {
        return instructorEmail;
    }

    public void setInstructorEmail(String instructorEmail) {
        this.instructorEmail = instructorEmail;
    }

    public String getInstructorPhoneNumber() {
        return instructorPhoneNumber;
    }

    public void setInstructorPhoneNumber(String instructorPhoneNumber) {
        this.instructorPhoneNumber = instructorPhoneNumber;
    }

    public boolean isStartAlert() {
        return startAlert;
    }

    public void setStartAlert(boolean startAlert) {
        this.startAlert = startAlert;
    }

    public boolean isEndAlert() {
        return endAlert;
    }

    public void setEndAlert(boolean endAlert) {
        this.endAlert = endAlert;
    }


}
