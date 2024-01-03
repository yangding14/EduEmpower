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

public class UpdatePhoneNumberFragment extends Fragment {

    EditText phone;
    TextView username;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_update_phone_number, container, false);
        username = view.findViewById(R.id.userName6);
        phone = view.findViewById(R.id.EVEditPhoneNumber);

        Button btn = view.findViewById(R.id.btnConfirmPhoneNumber);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_username = username.getText().toString();
                String txt_phone = phone.getText().toString();
                if(! txt_phone.isEmpty()){
                    updatePhone(txt_username, txt_phone);
                }
                navigateToAccountFragment();
            }
        });

        return view;
    }

    private void updatePhone(String txtUsername, String txtPhone) {
        HashMap map = new HashMap();
        map.put("Phone", txtPhone);
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