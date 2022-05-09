package com.example.StudentPlanner.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Assessments")
public class Assessments {
    @PrimaryKey(autoGenerate = true)
    private int assessmentID;
    private int courseAffiliate;
    private String assessmentName;
    private String type;
    private String start;
    private String end;
    private boolean startAlert;
    private boolean endAlert;

    @Override
    public String toString() {
        return "Assessments{" +
                "assessmentID=" + assessmentID +
                ", assessmentName='" + assessmentName + '\'' +
                ", type='" + type + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", startAlert=" + startAlert +
                ", endAlert=" + endAlert +
                '}';
    }

    public int getAssessmentID() {
        return assessmentID;
    }

    public void setAssessmentID(int assessmentID) {
        this.assessmentID = assessmentID;
    }

    public String getAssessmentName() {
        return assessmentName;
    }

    public void setAssessmentName(String assessmentName) {
        this.assessmentName = assessmentName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
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

    public int getCourseAffiliate() {
        return courseAffiliate;
    }

    public void setCourseAffiliate(int courseAffiliate) {
        this.courseAffiliate = courseAffiliate;
    }

    public Assessments(int assessmentID, String assessmentName, String type, String start, String end, boolean startAlert, boolean endAlert, int courseAffiliate) {
        this.assessmentID = assessmentID;
        this.assessmentName = assessmentName;
        this.type = type;
        this.start = start;
        this.end = end;
        this.startAlert = startAlert;
        this.endAlert = endAlert;
        this.courseAffiliate = courseAffiliate;
    }
}
