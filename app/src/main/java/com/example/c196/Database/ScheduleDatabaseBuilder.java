package com.example.c196.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.c196.DAO.AssessmentsDAO;
import com.example.c196.DAO.CoursesDAO;
import com.example.c196.DAO.TermsDAO;
import com.example.c196.Entity.Assessments;
import com.example.c196.Entity.Courses;
import com.example.c196.Entity.Terms;

@Database(entities = {Assessments.class, Courses.class, Terms.class}, version = 1, exportSchema = false)
public abstract class ScheduleDatabaseBuilder extends RoomDatabase {
    public abstract CoursesDAO CoursesDAO();
    public abstract AssessmentsDAO AssessmentsDAO();
    public abstract TermsDAO TermsDAO();

    private static volatile ScheduleDatabaseBuilder INSTANCE;

    static ScheduleDatabaseBuilder getDatabase(final Context context){
        if (INSTANCE==null){
            synchronized (ScheduleDatabaseBuilder.class){
                if (INSTANCE==null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ScheduleDatabaseBuilder.class,"myScheduleDatabase.db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
