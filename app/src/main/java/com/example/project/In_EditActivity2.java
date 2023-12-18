package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class In_EditActivity2 extends AppCompatActivity {

    ArrayList<CourseChapterModel> arrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_edit2);

        setUpCourseTitle();
        RecyclerView recyclerView = findViewById(R.id.inEditRecycleView2);
        InEditAdapter2 adapter2 = new InEditAdapter2(this, arrayList);
        recyclerView.setAdapter(adapter2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }

    public void setUpCourseTitle(){
        String[] titles = getResources().getStringArray(R.array.chemistry_chapter);
        String[] indices = getResources().getStringArray(R.array.index);

        for(int i=0; i<titles.length; i++){
            arrayList.add(new CourseChapterModel(indices[i], titles[i]));
        }
    }
}