package com.example.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DiscoverCourseAdapter extends RecyclerView.Adapter<DiscoverCourseAdapter.MyViewHolder> {

    //Variables
    Context context;
    ArrayList<DiscoverCourse> discoverCoursesArray;
    public DiscoverCourseAdapter(Context context, ArrayList<DiscoverCourse> discoverCoursesArray) {
        this.context = context;
        this.discoverCoursesArray = discoverCoursesArray;
    }

    @NonNull
    @Override
    public DiscoverCourseAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.discover_course_layout, parent, false);
        return new DiscoverCourseAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiscoverCourseAdapter.MyViewHolder holder, int position) {
        holder.courseName.setText(discoverCoursesArray.get(position).getCourseName());
        holder.courseDesc.setText(discoverCoursesArray.get(position).getCourseDesc());
        holder.courseImage.setImageResource(discoverCoursesArray.get(position).getCourseImage());
    }

    @Override
    public int getItemCount() {
        return discoverCoursesArray.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView courseName;
        TextView courseDesc;
        ImageView courseImage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            courseName = itemView.findViewById(R.id.courseName);
            courseDesc = itemView.findViewById(R.id.courseDesc);
            courseImage = itemView.findViewById(R.id.courseImage);
        }
    }
}
