package com.example.eduempoweryd.course;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class UpdatePasswordFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_update_password, container, false);

        Button btn = view.findViewById(R.id.btnConfirmPassword);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new AccountFragment();
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                BottomNavigationView btm = getActivity().findViewById(R.id.bottomNavigationView);
                btm.setVisibility(View.VISIBLE);
                ft.replace(R.id.frameLayout, fragment).commit();
            }
        });

        return view;
    }
}