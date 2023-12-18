package com.example.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class InEditAdapter2 extends RecyclerView.Adapter<InEditAdapter2.MyViewHolder> {
    Context context;
    ArrayList<CourseChapterModel> arrayList = new ArrayList<>();

    public InEditAdapter2(Context context, ArrayList<CourseChapterModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public InEditAdapter2.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.in_edit_movable, parent, false);
        return new InEditAdapter2.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InEditAdapter2.MyViewHolder holder, int position) {
        holder.title.setText(arrayList.get(position).title);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.inEditTitle2);
        }
    }
}
