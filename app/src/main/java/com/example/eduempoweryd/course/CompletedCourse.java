package com.example.eduempoweryd.course;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CompletedCourse extends AppCompatActivity {

    String cc_courseName;
    String cc_courseDesc;
    int cc_courseImage;

    public CompletedCourse(String cc_courseName, String cc_courseDesc, int cc_courseImage) {
        this.cc_courseName = cc_courseName;
        this.cc_courseDesc = cc_courseDesc;
        this.cc_courseImage = cc_courseImage;
    }

    public String getCc_courseName() {
        return cc_courseName;
    }

    public String getCc_courseDesc() {
        return cc_courseDesc;
    }

    public int getCc_courseImage() {
        return cc_courseImage;
    }

}