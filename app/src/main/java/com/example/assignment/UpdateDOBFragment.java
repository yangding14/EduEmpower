package com.example.assignment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.widget.Button;

public class UpdateDOBFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_update_d_o_b, container, false);
        Button btn = view.findViewById(R.id.btnConfirmDOB);
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