package com.example.assignment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SearchView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AccountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccountFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public AccountFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AccountFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AccountFragment newInstance(String param1, String param2) {
        AccountFragment fragment = new AccountFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){

        //Button CS
        ImageButton cs_button = view.findViewById(R.id.btn_acc_support);

        cs_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                ((MainActivity)getActivity()).enterCustomerSupport(view);
            }
        });

        // Button Change Username
        ImageButton btnChangeName = view.findViewById(R.id.btn_acc_username);

        btnChangeName.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Fragment fragment = new UpdateNameFragment();
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                BottomNavigationView btm = getActivity().findViewById(R.id.bottomNavigationView);
                btm.setVisibility(View.INVISIBLE);
                ft.replace(R.id.frameLayout, fragment).commit();
            }
        });

        ImageButton btnChangeEmail = view.findViewById(R.id.btn_acc_email);
        btnChangeEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new UpdateEmailFragment();
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                BottomNavigationView btm = getActivity().findViewById(R.id.bottomNavigationView);
                btm.setVisibility(View.INVISIBLE);
                ft.replace(R.id.frameLayout, fragment).commit();
            }
        });

        ImageButton btnChangeDOB = view.findViewById(R.id.btn_acc_dob);
        btnChangeDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new UpdateDOBFragment();
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                BottomNavigationView btm = getActivity().findViewById(R.id.bottomNavigationView);
                btm.setVisibility(View.INVISIBLE);
                ft.replace(R.id.frameLayout, fragment).commit();
            }
        });

        ImageButton btnChangePassword = view.findViewById(R.id.btn_acc_change_password);
        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new UpdatePasswordFragment();
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                BottomNavigationView btm = getActivity().findViewById(R.id.bottomNavigationView);
                btm.setVisibility(View.INVISIBLE);
                ft.replace(R.id.frameLayout, fragment).commit();
            }
        });

        ImageButton btnChangePhoneNumber = view.findViewById(R.id.btn_acc_phone);
        btnChangePhoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new UpdatePhoneNumberFragment();
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                BottomNavigationView btm = getActivity().findViewById(R.id.bottomNavigationView);
                btm.setVisibility(View.INVISIBLE);
                ft.replace(R.id.frameLayout, fragment).commit();
            }
        });
    }
}