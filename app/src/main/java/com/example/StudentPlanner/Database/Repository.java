package com.example.StudentPlanner.Database;

import android.app.Application;

import com.example.StudentPlanner.DAO.AssessmentsDAO;
import com.example.StudentPlanner.DAO.CoursesDAO;
import com.example.StudentPlanner.DAO.TermsDAO;
import com.example.StudentPlanner.Entity.Assessments;
import com.example.StudentPlanner.Entity.Courses;
import com.example.StudentPlanner.Entity.Terms;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Repository {
    private AssessmentsDAO mAssessmentDAO;
    private CoursesDAO mCoursesDAO;
    private TermsDAO mTermsDAO;
    private List<Assessments> mAllAssessments;
    private List<Courses> mAllCourses;
    private List<Terms> mAllTerms;

    private static int NUMBER_OF_THREADS=4;
    static final ExecutorService databaseExecutor= Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public Repository(Application application){
        ScheduleDatabaseBuilder db=ScheduleDatabaseBuilder.getDatabase(application);
        mAssessmentDAO = db.AssessmentsDAO();
        mCoursesDAO = db.CoursesDAO();
        mTermsDAO = db.TermsDAO();
    }

    public void insert(Terms terms){
        databaseExecutor.execute(()->{
            mTermsDAO.insert(terms);
        });

        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    public List<Terms>getAllTerms(){
        databaseExecutor.execute(()->{
            mAllTerms= mTermsDAO.getAllTerms();
        });
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return mAllTerms;
    }
    public void update(Terms terms){
        databaseExecutor.execute(()->{
            mTermsDAO.Update(terms);
        });

        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    public void delete(Terms terms){
        databaseExecutor.execute(()->{
            mTermsDAO.delete(terms);
        });

        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    //Courses

    public void insert(Courses courses){
        databaseExecutor.execute(()->{
            mCoursesDAO.insert(courses);
        });

        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    public List<Courses>getAllCourses(){
        databaseExecutor.execute(()->{
            mAllCourses= mCoursesDAO.getAllCourses();
        });
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return mAllCourses;
    }
    public void update(Courses courses){
        databaseExecutor.execute(()->{
            mCoursesDAO.Update(courses);
        });

        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    public void delete(Courses courses){
        databaseExecutor.execute(()->{
            mCoursesDAO.delete(courses);
        });

        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    //Assessments

    public void insert(Assessments assessments){
        databaseExecutor.execute(()->{
            mAssessmentDAO.insert(assessments);
        });

        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    public List<Assessments>getAllAssessments(){
        databaseExecutor.execute(()->{
            mAllAssessments= mAssessmentDAO.getAllAssessments();
        });
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return mAllAssessments;
    }
    public void update(Assessments assessments){
        databaseExecutor.execute(()->{
            mAssessmentDAO.Update(assessments);
        });

        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    public void delete(Assessments assessments){
        databaseExecutor.execute(()->{
            mAssessmentDAO.delete(assessments);
        });

        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }


}
