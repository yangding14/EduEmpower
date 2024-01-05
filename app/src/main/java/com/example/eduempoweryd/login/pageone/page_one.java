package com.example.mad2.pageone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mad2.R;
import com.example.mad2.databinding.PageOneBinding;
import com.example.mad2.databinding.PageTwoBinding;
import com.example.mad2.pagetwo.PageTwoVM;
import com.example.mad2.pagetwo.page_two;

public class page_one extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PageOneBinding binding = DataBindingUtil.setContentView(this, R.layout.page_one);
        PageOneVM viewModel = new PageOneVM();
        binding.setPageOneVM(viewModel);
        binding.setLifecycleOwner(this);

        Button b1;

        b1 = findViewById(R.id.btnNext);
        b1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(page_one.this, page_two.class);
                        startActivity(i);
                    }
                }
        );

    }

}
