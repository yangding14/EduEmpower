package com.example.eduempoweryd.course;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
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
import android.widget.TextView;

import com.example.eduempoweryd.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private PieChart pieChart;

    ArrayList<progress_chapterlist> progressChapterlists = new ArrayList<>();
    int [] images= {R.drawable.checked, R.drawable.checked, R.drawable.checked, R.drawable.checked, R.drawable.checked,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};

    int[] image = {R.drawable.checked, R.drawable.pending};
    int x=0,y=0;

    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }


    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        return inflater.inflate(R.layout.course_fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pieChart = view.findViewById(R.id.pieChart);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Chapters");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                progressChapterlists.clear(); // Clear existing data before adding new data
                int i = 0;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String name = snapshot.child("name").getValue(String.class);
                    String position = snapshot.child("position").getValue(String.class);
                    String key = snapshot.getKey();
                    String filetype = snapshot.child("file").getValue(String.class);

                    if(images[i]!=0){
                        progressChapterlists.add(new progress_chapterlist(position, name, image[0] , key));
                        x++;
                    }else {progressChapterlists.add(new progress_chapterlist(position, name, image[1] , key));
                        y++;
                    }

                    i++;
                }
                showStatistics(x,y);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle the error
            }
        });

        TextView userName = view.findViewById(R.id.textView2);
        SharedPreferences preferences = getActivity().getSharedPreferences("system", MODE_PRIVATE);
        String uid = preferences.getString("uid", "");

        //Retrieve data for ET
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Students");
        reference.child(uid).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    DataSnapshot snapshot = task.getResult();
                    String username = String.valueOf(snapshot.child("Username").getValue());
                    String str ="Hi " + username + " \uD83D\uDC4B";
                    userName.setText(str);
                }
            }
        });

        ImageButton button1 = view.findViewById(R.id.imageButton5);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new st_track_progress_Fragment(progressChapterlists);
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frameLayout, fragment).commit();
            }
        });
        ImageButton button2 = view.findViewById(R.id.imageButton6);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new current_progress_Fragment();
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