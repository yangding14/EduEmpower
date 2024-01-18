package com.example.eduempoweryd.chapter;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eduempoweryd.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link pdfpreview_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class pdfpreview_fragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    ArrayList<editchapterlist> ChapterarrayList;
    int position;

    public pdfpreview_fragment(int position , ArrayList<editchapterlist> ChapterArrayList){
        this.position = position;
        this.ChapterarrayList = ChapterArrayList;
    }

    public pdfpreview_fragment() {
        // Required empty public constructor
    }


    public static pdfpreview_fragment newInstance(String param1, String param2) {
        pdfpreview_fragment fragment = new pdfpreview_fragment();
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
        return inflater.inflate(R.layout.videoview_fragment_pdfpreview, container, false);
    }
}