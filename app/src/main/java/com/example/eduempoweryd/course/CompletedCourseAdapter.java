package com.example.eduempoweryd.course;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CompletedCourseAdapter extends RecyclerView.Adapter<CompletedCourseAdapter.MyViewHolder> {
    Context context;
    ArrayList<CompletedCourse> completedCourseArrayList;
    ArrayList<CompletedCourse> originalList;

    public CompletedCourseAdapter(Context context, ArrayList<CompletedCourse> completedCourseArrayList) {
        this.context = context;
        this.completedCourseArrayList = completedCourseArrayList;
        this.originalList=completedCourseArrayList;
    }

    @NonNull
    @Override
    public CompletedCourseAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.completed_course_layout, parent, false);
        return new CompletedCourseAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CompletedCourseAdapter.MyViewHolder holder, int position) {
        holder.cc_courseName.setText(completedCourseArrayList.get(position).getCc_courseName());
        holder.cc_courseDesc.setText(completedCourseArrayList.get(position).getCc_courseDesc());
        holder.cc_courseImage.setImageResource(completedCourseArrayList.get(position).cc_courseImage);
    }

    @Override
    public int getItemCount() {
        return completedCourseArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView cc_courseName;
        TextView cc_courseDesc;
        ImageView cc_courseImage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            cc_courseName = itemView.findViewById(R.id.cc_courseName);
            cc_courseDesc = itemView.findViewById(R.id.cc_courseDesc);
            cc_courseImage = itemView.findViewById(R.id.CompletedCourseImage);
        }
    }

    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String query = charSequence.toString().toLowerCase();

                ArrayList<CompletedCourse> filteredList = new ArrayList<>();
                for (CompletedCourse item : originalList) {
                    // Implement your filtering logic here
                    // For example, check if item's name or description contains the query
                    if (item.getCc_courseName().toLowerCase().contains(query)||item.getCc_courseDesc().toLowerCase().contains(query)){
                        filteredList.add(item);
                    }
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                completedCourseArrayList.clear();
                completedCourseArrayList.addAll((ArrayList<CompletedCourse>) filterResults.values);
                notifyDataSetChanged();
            }
        };

    }

}
