package com.example.eduempoweryd.course;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eduempoweryd.R;

import java.util.ArrayList;

public class progress_chapteradapter extends RecyclerView.Adapter<progress_chapteradapter.MyViewHolder> {
    VideoView videoView;
    Context context;
    ArrayList<progress_chapterlist> progressChapterlists;


    public progress_chapteradapter() {

    }

    public progress_chapteradapter(Context context, ArrayList<progress_chapterlist> progressChapterlists) {
        this.context = context;
        this.progressChapterlists = progressChapterlists;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.course_progress_chapterlist_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tvName.setText(progressChapterlists.get(position).getName());
        holder.tvNo.setText(progressChapterlists.get(position).getPosition());
        holder.imageButton.setImageResource(progressChapterlists.get(position).getImage());
    }

    public int getItemCount() {
        return progressChapterlists.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageButton;
        TextView tvName, tvNo;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageButton = itemView.findViewById(R.id.chaptercheck);
            tvName = itemView.findViewById(R.id.chaptername);
            tvNo = itemView.findViewById(R.id.chapterno);

            };
        }
    }
