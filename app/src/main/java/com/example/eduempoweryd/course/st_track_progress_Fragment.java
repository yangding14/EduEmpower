package com.example.eduempoweryd.course;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.eduempoweryd.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class st_track_progress_Fragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private PieChart pieChart;
    int[] image = {R.drawable.checked, R.drawable.pending};


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ArrayList<progress_chapterlist> progressChapterlists = new ArrayList<>();

    public st_track_progress_Fragment() {
        // Required empty public constructor
    }

    public st_track_progress_Fragment(ArrayList<progress_chapterlist> progressChapterlists) {
        this.progressChapterlists = progressChapterlists;
    }
    public static st_track_progress_Fragment newInstance(String param1, String param2) {
        st_track_progress_Fragment fragment = new st_track_progress_Fragment();
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
        return inflater.inflate(R.layout.course_fragment_st_track_progress_, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pieChart = view.findViewById(R.id.pieChart);
        int x=0,y=0 ;
        for(int i = 0 ; i < progressChapterlists.size() ; i ++ ){
            if(progressChapterlists.get(i).getImage()==image[0]){
                x++ ;
            }else{y++;}
        }

        showStatistics(x,y);

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

    private void showStatistics(int done , int not_done) {
        pieChart.setVisibility(View.VISIBLE);

        pieChart.getDescription().setEnabled(false);
        pieChart.setDrawHoleEnabled(true);

        List<PieEntry> entries = new ArrayList<>();

        entries.add(new PieEntry(done, ""));
        entries.add(new PieEntry(not_done, ""));

        PieDataSet set = new PieDataSet(entries, "");
        set.setColors(new int[] {
                ContextCompat.getColor(requireContext(), R.color.done),
                ContextCompat.getColor(requireContext(), R.color.not_done)
        });

        int percentage = done * 100 /(done+not_done);
        pieChart.setCenterText(String.valueOf(percentage)+"%");



        PieData data = new PieData(set);
        pieChart.setData(data);
        pieChart.invalidate(); // Refresh the pie chart

        // Optional: Add animation
        pieChart.animateXY(1400, 1400);
    }
}