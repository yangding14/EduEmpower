package com.example.eduempoweryd.chapters;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.eduempoweryd.R;

import java.util.ArrayList;

public class current_progress_Fragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ArrayList<progress_chapterlist> progressChapterlists = new ArrayList<>();
    int[] images = {R.drawable.checked, R.drawable.checked, R.drawable.checked, R.drawable.checked, R.drawable.pending,R.drawable.pending};

    public current_progress_Fragment() {
        // Required empty public constructor
    }

    public static current_progress_Fragment newInstance(String param1, String param2) {
        current_progress_Fragment fragment = new current_progress_Fragment();
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
        return inflater.inflate(R.layout.chapter_fragment_current_progress_, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerview = view.findViewById(R.id.Progress_RecycleView);
        setUpProgressChapterLists();
        progress_chapteradapter progress_chapteradapter = new progress_chapteradapter(this.getContext() ,  progressChapterlists );
        recyclerview.setAdapter(progress_chapteradapter);
        recyclerview.setLayoutManager(new LinearLayoutManager(this.getContext()));

        ImageButton backbutton = view.findViewById(R.id.btn_cs_back);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new HomeFragment();
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frameLayout, fragment).commit();
            }
        });
    }

    public void setUpProgressChapterLists(){
        String[] position = getResources().getStringArray(R.array.index);
        String[] chemistry_chapter = getResources().getStringArray(R.array.chemistry_chapter);

        for (int i=0; i<position.length; i++){
            progressChapterlists.add(new progress_chapterlist(position[i], chemistry_chapter[i], images[i]));
        }
    }
}