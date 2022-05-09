package com.example.StudentPlanner.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.StudentPlanner.Entity.Assessments;

import java.util.List;
@Dao
public interface AssessmentsDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Assessments course);

    @Update
    void Update(Assessments course);

    @Delete
    void delete(Assessments course);

    @Query("SELECT * FROM Assessments ORDER BY assessmentID ASC")
    List<Assessments> getAllAssessments();
}
