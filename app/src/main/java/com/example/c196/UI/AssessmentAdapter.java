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
import com.example.c196.R;

import java.util.List;

public class AssessmentAdapter extends RecyclerView.Adapter<AssessmentAdapter.AssessmentViewHolder>{
    class AssessmentViewHolder extends RecyclerView.ViewHolder{
        private final TextView AssessmentItemView;
        private AssessmentViewHolder(View itemView){
            super(itemView);
            AssessmentItemView = itemView.findViewById(R.id.assessmentTextView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=getAdapterPosition();
                    final Assessments current = mAssessments.get(position);
                    Intent intent = new Intent(context, DetailedAssessmentActivity.class);
                    intent.putExtra("id",current.getAssessmentID());
                    intent.putExtra("name",current.getAssessmentName());
                    intent.putExtra("start",current.getStart());
                    intent.putExtra("end",current.getEnd());
                    intent.putExtra("type",current.getType());
                    intent.putExtra("courseID",current.getCourseAffiliate());
                    intent.putExtra("startAlert",current.isStartAlert());
                    intent.putExtra("endAlert",current.isEndAlert());

                    context.startActivity(intent);
                }
            });
        }
    }

    private List<Assessments> mAssessments;
    private final Context context;
    private final LayoutInflater mInflater;
    public AssessmentAdapter(Context context){
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }


    @NonNull
    @Override
    public AssessmentAdapter.AssessmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView =mInflater.inflate(R.layout.assessment_list,parent,false);
        return new AssessmentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AssessmentAdapter.AssessmentViewHolder holder, int position) {
        if (mAssessments != null){
            Assessments current = mAssessments.get(position);
            String name = current.getAssessmentName();
            holder.AssessmentItemView.setText(name);
        }
        else {
            holder.AssessmentItemView.setText("No Assessment Selected!");
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
}
