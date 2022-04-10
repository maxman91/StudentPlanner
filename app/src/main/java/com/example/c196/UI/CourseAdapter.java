package com.example.c196.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c196.Entity.Assessments;
import com.example.c196.Entity.Courses;
import com.example.c196.Entity.Terms;
import com.example.c196.R;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder>{
    class CourseViewHolder extends RecyclerView.ViewHolder{
        private final TextView CourseItemView;
        private CourseViewHolder(View itemView){
            super(itemView);
            CourseItemView = itemView.findViewById(R.id.courseList);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=getAdapterPosition();
                    final Courses current = mCourses.get(position);
                    Intent intent = new Intent(context, DetailedCourseActivity.class);
                    intent.putExtra("id",current.getCourseID());
                    intent.putExtra("name",current.getCourseName());
                    intent.putExtra("start",current.getStart());
                    intent.putExtra("end",current.getFinish());
                    intent.putExtra("status",current.getStatus());
                    intent.putExtra("note",current.getNote());
                    intent.putExtra("phone",current.getInstructorPhoneNumber());
                    intent.putExtra("instructorName",current.getInstructorName());
                    intent.putExtra("email",current.getInstructorEmail());
                    intent.putExtra("startAlert",current.isStartAlert());
                    intent.putExtra("endAlert",current.isEndAlert());
                    context.startActivity(intent);
                }
            });
        }
    }
    private List<Courses> mCourses;
    private final Context context;
    private final LayoutInflater mInflater;
    public CourseAdapter(Context context){
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }
    @NonNull
    @Override
    public CourseAdapter.CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView =mInflater.inflate(R.layout.course_list,parent,false);
        return new CourseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseAdapter.CourseViewHolder holder, int position) {
        if (mCourses != null){
            Courses current = mCourses.get(position);
            String name = current.getCourseName();
            holder.CourseItemView.setText(name);
        }
        else {
            holder.CourseItemView.setText("No Course Selected!");
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
}
