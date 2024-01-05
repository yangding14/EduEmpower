package com.example.mad2.landingpage;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import com.example.mad2.R;
import com.example.mad2.databinding.LandingPageBinding; // Import the correct binding class
import com.example.mad2.pageone.page_one;

public class landing_page extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LandingPageBinding binding = DataBindingUtil.setContentView(this, R.layout.landing_page);
        LandingPageVM viewModel = new LandingPageVM();
        binding.setLandingPageVM(viewModel);
        binding.setLifecycleOwner(this);

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            startActivity(new Intent(this, page_one.class));
        }, 2000); // 2000 milliseconds = 2 seconds
        // Any additional setup or logic specific to your landing page can be added here
    }
}