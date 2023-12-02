package com.example.mad2.pageone;

import android.os.Bundle;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.mad2.R;

public class PageOneFragment extends Fragment {

    public PageOneFragment() {
        super(R.layout.page_one);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        // Note: There is no direct equivalent for synthetic properties in Java
//        View buttonNext = view.findViewById(R.id.btnNext);
//        buttonNext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Navigation.findNavController(view)
//                        .navigate(R.id.action_pageOneFragment_to_pageTwoFragment);
//            }
//        });
    }
}