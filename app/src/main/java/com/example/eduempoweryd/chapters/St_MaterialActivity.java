package com.example.eduempoweryd.chapters;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.eduempoweryd.R;

import java.util.ArrayList;

public class St_MaterialActivity extends AppCompatActivity {

    ArrayList<CourseChapterModel> courseChapterModelArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chapter_activity_st_material);

        RecyclerView recyclerView = findViewById(R.id.chapterList);
        setUpCourseChapter();
        InPreviewAdapter adapter = new InPreviewAdapter(this, courseChapterModelArrayList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void setUpCourseChapter(){
        String[] indices = getResources().getStringArray(R.array.index);
        String[] titles = getResources().getStringArray(R.array.chemistry_chapter);

        for(int i=0; i<titles.length; i++){
            courseChapterModelArrayList.add(new CourseChapterModel(indices[i], titles[i]));
        }
    }
}