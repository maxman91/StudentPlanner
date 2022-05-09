package com.example.StudentPlanner.UI;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.StudentPlanner.Database.Repository;
import com.example.StudentPlanner.Entity.Assessments;
import com.example.StudentPlanner.R;

import java.util.List;

public class FilteredAssessmentAdapter extends RecyclerView.Adapter<FilteredAssessmentAdapter.FilteredAssessmentViewHolder>{
    private List<Assessments> mAssessments;
    private final Context context;
    private final LayoutInflater mInflater;
    private int courseID;
    Repository repo;
    public FilteredAssessmentAdapter(Context context,int courseID,Repository repo){
        mInflater = LayoutInflater.from(context);
        this.context = context;
        this.courseID = courseID;
        this.repo = repo;
    }

    @NonNull
    @Override
    public FilteredAssessmentAdapter.FilteredAssessmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView =mInflater.inflate(R.layout.assessment_filtered_list,parent,false);
        return new FilteredAssessmentAdapter.FilteredAssessmentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FilteredAssessmentAdapter.FilteredAssessmentViewHolder holder, int position) {
        if (mAssessments != null){
            Assessments current = mAssessments.get(position);
            String name = current.getAssessmentName();
            holder.checkBox.setText(name);
            if (current.getCourseAffiliate() !=0 ){
                holder.checkBox.setChecked(true);
            }
        }
        else {
            holder.checkBox.setText("No Assessment Selected!");
        }
    }
    public void setAssessments(List<Assessments> assessments){
        mAssessments = assessments;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mAssessments != null){
            return mAssessments.size();
        } else return 0;
    }

    public class FilteredAssessmentViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;
        public FilteredAssessmentViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.assessmentFilteredList);
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Assessments assessments;

                    int position=getAdapterPosition();
                    final Assessments current = mAssessments.get(position);
                    if (checkBox.isChecked()){
                        assessments = new Assessments(current.getAssessmentID(),current.getAssessmentName()
                                , current.getType(), current.getStart(),current.getEnd()
                                ,current.isStartAlert(), current.isEndAlert(),
                                courseID);
                    }

                    else {
                        assessments = new Assessments(current.getAssessmentID(),current.getAssessmentName()
                                , current.getType(), current.getStart(),current.getEnd()
                                ,current.isStartAlert(), current.isEndAlert(),
                                0);
                    }
                    repo.update(assessments);


                }
            });
        }
    }
}
