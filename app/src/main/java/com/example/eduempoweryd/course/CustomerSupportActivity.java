package com.example.eduempoweryd.course;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.eduempoweryd.databinding.CourseActivityCustomerSupportBinding;

public class CustomerSupportActivity extends AppCompatActivity {

    private CourseActivityCustomerSupportBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = CourseActivityCustomerSupportBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnCsBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
    }




}