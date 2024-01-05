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

public class DiscoverCourseAdapter extends RecyclerView.Adapter<DiscoverCourseAdapter.MyViewHolder> {

    //Variables
    Context context;
    ArrayList<DiscoverCourse> discoverCoursesArray;
    private ArrayList<DiscoverCourse> originalList; // Keep the original unfiltered list

    public DiscoverCourseAdapter(Context context, ArrayList<DiscoverCourse> discoverCoursesArray) {
        this.context = context;
        this.discoverCoursesArray = discoverCoursesArray;
        this.originalList = new ArrayList<>(discoverCoursesArray);
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

    public static class MyViewHolder extends RecyclerView.ViewHolder {

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

    //searching
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String query = charSequence.toString().toLowerCase();

                ArrayList<DiscoverCourse> filteredList = new ArrayList<>();
                for (DiscoverCourse item : originalList) {
                    // Implement your filtering logic here
                    // For example, check if item's name or description contains the query
                    if (item.getCourseName().toLowerCase().contains(query) ||
                            item.getCourseDesc().toLowerCase().contains(query)) {
                        filteredList.add(item);
                    }
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                discoverCoursesArray.clear();
                discoverCoursesArray.addAll((ArrayList<DiscoverCourse>) filterResults.values);
                notifyDataSetChanged();
            }
        };

    }
}
