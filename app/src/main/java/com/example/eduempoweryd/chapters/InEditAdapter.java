package com.example.eduempoweryd.chapters;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eduempoweryd.R;

import java.util.ArrayList;

public class InEditAdapter extends RecyclerView.Adapter<InEditAdapter.MyViewHolder> {

    Context context;
    ArrayList <CourseChapterModel> courseChapterModelArrayList = new ArrayList<>();

    public InEditAdapter(Context context, ArrayList<CourseChapterModel> courseChapterModelArrayList) {
        this.context = context;
        this.courseChapterModelArrayList = courseChapterModelArrayList;
    }

    @NonNull
    @Override
    public InEditAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.chapter_in_edit_chapter, parent, false);
        return new InEditAdapter.MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull InEditAdapter.MyViewHolder holder, int position) {
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
        Spinner spinner;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            index = itemView.findViewById(R.id.inEditIndex);
            title = itemView.findViewById(R.id.inEditTitle);
            spinner = itemView.findViewById(R.id.spinner);

            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(itemView.getContext(), R.array.file_type, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (view instanceof TextView){
                        ((TextView) view).setTextSize(15);
                        ((TextView) view).setTextColor(Color.parseColor("#FFFFFF"));
                        ((TextView) view).setEllipsize(TextUtils.TruncateAt.END);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }

    }
}
