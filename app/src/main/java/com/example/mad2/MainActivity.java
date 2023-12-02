package com.example.mad2;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;

import com.example.mad2.landingpage.landing_page;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create an Intent to launch the landing_page activity
        Intent intent = new Intent(this, landing_page.class);

        // Start the landing_page activity
        startActivity(intent);

    }
}
