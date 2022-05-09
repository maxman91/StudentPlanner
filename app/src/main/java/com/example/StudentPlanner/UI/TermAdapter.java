package com.example.StudentPlanner.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.StudentPlanner.Entity.Terms;
import com.example.StudentPlanner.R;

import java.util.List;

public class TermAdapter extends RecyclerView.Adapter<TermAdapter.TermViewHolder> {
    class TermViewHolder extends RecyclerView.ViewHolder{
        private final TextView TermItemView;
        private TermViewHolder(View itemView){
            super(itemView);
            TermItemView = itemView.findViewById(R.id.textView7);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=getAdapterPosition();
                    final Terms current = mTerms.get(position);
                    Intent intent = new Intent(context, DetailedTermActivity.class);
                    intent.putExtra("id",current.getTermID());
                    intent.putExtra("name",current.getTermName());
                    intent.putExtra("start",current.getStartDate());
                    intent.putExtra("end",current.getEndDate());
                    context.startActivity(intent);
                }
            });
        }
    }
    private List<Terms> mTerms;
    private final Context context;
    private final LayoutInflater mInflater;
    public TermAdapter(Context context){
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }
    @NonNull
    @Override
    public TermAdapter.TermViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView =mInflater.inflate(R.layout.term_list,parent,false);
        return new TermViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TermAdapter.TermViewHolder holder, int position) {
        if (mTerms != null){
            Terms current = mTerms.get(position);
            String name = current.getTermName();
            holder.TermItemView.setText(name);
        }
        else {
            holder.TermItemView.setText("No Term Selected!");
        }
    }

    public void setTerms(List<Terms> terms){
        mTerms = terms;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mTerms != null){
            return mTerms.size();
        } else return 0;
    }
}