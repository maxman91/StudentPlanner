package com.example.StudentPlanner.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.StudentPlanner.Entity.Terms;

import java.util.List;

@Dao
public interface TermsDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Terms course);

    @Update
    void Update(Terms course);

    @Delete
    void delete(Terms course);

    @Query("SELECT * FROM Terms ORDER BY termID ASC")
    List<Terms> getAllTerms();
}
