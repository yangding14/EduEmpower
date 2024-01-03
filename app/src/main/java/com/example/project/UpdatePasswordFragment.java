package com.example.project;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class UpdatePasswordFragment extends Fragment {

    EditText password;
    TextView username;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_update_password, container, false);
        password = view.findViewById(R.id.EVEditConfirmPassword);
        username = view.findViewById(R.id.userName4);

        Button btn = view.findViewById(R.id.btnConfirmPassword);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_password = password.getText().toString();
                String txt_username = username.getText().toString();
                if(!txt_password.isEmpty()){
                    updatePassword(txt_username, txt_password);
                }
                navigateToAccountFragment();
            }
        });

        return view;
    }

    private void updatePassword(String txtUsername, String txtPassword) {
        HashMap map = new HashMap();
        map.put("Password", txtPassword);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Students");
        ref.child(txtUsername).updateChildren(map);
    }

    public void navigateToAccountFragment(){
        Fragment fragment = new AccountFragment();
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        BottomNavigationView btm = getActivity().findViewById(R.id.bottomNavigationView);
        btm.setVisibility(View.VISIBLE);
        ft.replace(R.id.frameLayout, fragment).commit();
    }
}