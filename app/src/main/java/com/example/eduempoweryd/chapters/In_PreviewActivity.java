package com.example.eduempoweryd.chapters;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.eduempoweryd.R;

import java.util.ArrayList;

public class In_PreviewActivity extends AppCompatActivity {

    ArrayList<CourseChapterModel> courseChapterModelArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chapter_activity_in_preview);

        RecyclerView recyclerView = findViewById(R.id.inPreviewRecycleView);
        setUpCourseChapter();
        InPreviewAdapter adapter = new InPreviewAdapter(this, courseChapterModelArrayList);
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