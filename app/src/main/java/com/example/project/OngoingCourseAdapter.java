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

public class OngoingCourseAdapter extends RecyclerView.Adapter<OngoingCourseAdapter.MyViewHolder> {

    Context context;
    ArrayList<OngoingCourse> ongoingCourseArrayList;

    public OngoingCourseAdapter(Context context, ArrayList<OngoingCourse> ongoingCourseArrayList) {
        this.context = context;
        this.ongoingCourseArrayList = ongoingCourseArrayList;
    }

    @NonNull
    @Override
    public OngoingCourseAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.ongoing_course_layout, parent, false);
        return new OngoingCourseAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OngoingCourseAdapter.MyViewHolder holder, int position) {
        holder.oc_courseName.setText(ongoingCourseArrayList.get(position).getOc_courseName());
        holder.oc_courseQty.setText(ongoingCourseArrayList.get(position).getOc_courseQty());
        holder.oc_courseImage.setImageResource(ongoingCourseArrayList.get(position).getOc_courseImage());
    }

    @Override
    public int getItemCount() {
        return ongoingCourseArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        // View ID
        TextView oc_courseName;
        TextView oc_courseQty;
        ImageView oc_courseImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            oc_courseName = itemView.findViewById(R.id.oc_courseName);
            oc_courseQty = itemView.findViewById(R.id.oc_courseQty);
            oc_courseImage = itemView.findViewById(R.id.oc_courseImage);
        }
    }
}
