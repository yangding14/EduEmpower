package com.example.eduempoweryd.chapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eduempoweryd.R;

import java.util.ArrayList;

public class InPreviewAdapter extends RecyclerView.Adapter<InPreviewAdapter.MyViewHolder> {

    Context context;
    ArrayList <CourseChapterModel> courseChapterModelArrayList = new ArrayList<>();

    public InPreviewAdapter(Context context, ArrayList<CourseChapterModel> courseChapterModelArrayList) {
        this.context = context;
        this.courseChapterModelArrayList = courseChapterModelArrayList;
    }

    @NonNull
    @Override
    public InPreviewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.chapter_in_preview_chapter, parent, false);
        return new InPreviewAdapter.MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull InPreviewAdapter.MyViewHolder holder, int position) {
        holder.index.setText(courseChapterModelArrayList.get(position).index);
        holder.title.setText(courseChapterModelArrayList.get(position).title);
    }

    @Override
    public int getItemCount() {
        return courseChapterModelArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView index;
        TextView title;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            index = itemView.findViewById(R.id.inPreviewIndex);
            title = itemView.findViewById(R.id.inPreviewTitle);
        }
    }
}
