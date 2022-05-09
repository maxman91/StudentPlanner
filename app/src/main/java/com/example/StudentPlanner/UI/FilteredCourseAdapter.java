package com.example.StudentPlanner.UI;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.StudentPlanner.Database.Repository;
import com.example.StudentPlanner.Entity.Courses;
import com.example.StudentPlanner.R;

import java.util.List;

public class FilteredCourseAdapter extends RecyclerView.Adapter<FilteredCourseAdapter.FilteredCourseViewHolder>{
    private List<Courses> mCourses;
    private final Context context;
    private final LayoutInflater mInflater;
    private int termID;
    Repository repo;
    public FilteredCourseAdapter(Context context,int termID,Repository repo){
        mInflater = LayoutInflater.from(context);
        this.context = context;
        this.termID = termID;
        this.repo = repo;
    }

    @NonNull
    @Override
    public FilteredCourseAdapter.FilteredCourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView =mInflater.inflate(R.layout.course_filtered_list,parent,false);
        return new FilteredCourseAdapter.FilteredCourseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FilteredCourseAdapter.FilteredCourseViewHolder holder, int position) {
        if (mCourses != null){
            Courses current = mCourses.get(position);
            String name = current.getCourseName();
            holder.checkBox.setText(name);
            if (current.getTermAffiliation() !=0 ){
                holder.checkBox.setChecked(true);
            }
        }
        else {
            holder.checkBox.setText("No Course Selected!");
        }
    }
    public void setCourses(List<Courses> courses){
        mCourses = courses;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mCourses != null){
            return mCourses.size();
        } else return 0;
    }

    public class FilteredCourseViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;
        public FilteredCourseViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.courseListFiltered);
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Courses courses;

                    int position=getAdapterPosition();
                    final Courses current = mCourses.get(position);
                    if (checkBox.isChecked()){
                        courses = new Courses(current.getCourseID(),current.getCourseName(),termID,current.getStatus(),
                                current.getStart(),current.getFinish(),current.getInstructorName(),
                                current.getInstructorEmail(),current.getInstructorPhoneNumber(),current.getNote(),
                                current.isStartAlert(), current.isEndAlert());
                    }

                    else {
                        courses = new Courses(current.getCourseID(),current.getCourseName(),0,current.getStatus(),
                                current.getStart(),current.getFinish(),current.getInstructorName(),
                                current.getInstructorEmail(),current.getInstructorPhoneNumber(),current.getNote(),
                                current.isStartAlert(), current.isEndAlert());
                    }
                    repo.update(courses);


                }
            });
        }
    }
}
