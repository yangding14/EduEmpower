package com.example.eduempoweryd.videoview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.eduempoweryd.R;
import com.example.eduempoweryd.databinding.VideoviewActivityMainBinding;
import com.google.firebase.FirebaseApp;

public class MainActivity extends AppCompatActivity {
    VideoviewActivityMainBinding binding;

    // TODO: if instructor navigate to video fragment, if student navigate to student video fragment

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        binding = VideoviewActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SharedPreferences preferences = getSharedPreferences("system", MODE_PRIVATE);
        String role = preferences.getString("role", "null");

        if ("instructor".equals(role)) {
            replaceFragment(new VideoFragment());
        } else if ("student".equals(role)) {
            replaceFragment(new StudentVideoFragment());
        } else {
            // Handle the case where the role is not recognized or handle it according to your logic.
        }
    }

    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }

}