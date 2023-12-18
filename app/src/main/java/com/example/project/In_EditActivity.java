package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import java.util.ArrayList;

public class In_EditActivity extends AppCompatActivity {
    ArrayList<CourseChapterModel> courseChapterModelArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_edit);



        RecyclerView recyclerView = findViewById(R.id.inEditRecycleView);
        setUpCourseChapter();
        InEditAdapter adapter = new InEditAdapter(this, courseChapterModelArrayList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



    }



    public void setUpCourseChapter(){
        String[] titles = getResources().getStringArray(R.array.chemistry_chapter);
        String[] indices = getResources().getStringArray(R.array.index);

        for (int i=0; i<titles.length; i++){
            courseChapterModelArrayList.add(new CourseChapterModel(indices[i], titles[i]));
        }
    }
}