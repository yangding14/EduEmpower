package com.example.eduempoweryd.course;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.SearchView;

import com.bumptech.glide.Glide;
import com.example.eduempoweryd.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CoursesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CoursesFragment extends Fragment {

    LinearLayout completedCourseContainer;
    List<Course> courses = new ArrayList<>();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // View
    private SearchView searchView;


    //Discover Course Variables
    ArrayList<DiscoverCourse> discoverCoursesArray = new ArrayList<>();
    DiscoverCourse discoverCourse;
    int[] courseImages = {R.drawable.web, R.drawable.web, R.drawable.web, R.drawable.web};

    //Ongoing Course Variables
    ArrayList<OngoingCourse> ongoingCourseArrayList = new ArrayList<>();
    OngoingCourse ongoingCourse;
    int[] oc_courseImages = {R.drawable.oc_accounting, R.drawable.oc_biology, R.drawable.oc_accounting, R.drawable.oc_biology, R.drawable.oc_accounting};

    //Completed Course Variables
    ArrayList<CompletedCourse> completedCourseArrayList = new ArrayList<>();
    CompletedCourse completedCourse;
    String[] cc_courseImages = new String[]{""};
    private DiscoverCourseAdapter discoverCourseAdapter;
    private OngoingCourseAdapter ongoingCourseAdapter;
    private CompletedCourseAdapter completedCourseAdapter;
    public CoursesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CoursesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CoursesFragment newInstance(String param1, String param2) {
        CoursesFragment fragment = new CoursesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.course_fragment_courses, container, false);

        Button button = view.findViewById(R.id.BtnCompletedCourse);
        Button button2 = view.findViewById(R.id.BtnOngoingCourses);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openViewCompletedCourse();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openViewOngoingCourse();
            }
        });

        discoverCourseAdapter = new DiscoverCourseAdapter(getContext(), discoverCoursesArray);
        ongoingCourseAdapter = new OngoingCourseAdapter(getContext(), ongoingCourseArrayList);
        completedCourseAdapter = new CompletedCourseAdapter(getContext(), completedCourseArrayList);
        return view;
    }

    public void openViewCompletedCourse() {
        Intent intent = new Intent(getActivity(), SeeAllCompletedCourses.class);
        startActivity(intent);
    }

    public void openViewOngoingCourse() {
        Intent intent = new Intent(getActivity(), SeeAllOngoingCourses.class);
        startActivity(intent);
    }

    public void openCourse() {
        Intent intent = new Intent(getActivity(), StCourseViewActivity.class);
        startActivity(intent);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Recycle View for Discover Courses
        RecyclerView recyclerView = view.findViewById(R.id.discoverCourseRecycleView);
        setUpDiscoverCourseData();
        //DiscoverCourseAdapter discoverCourseAdapter = new DiscoverCourseAdapter(this.getContext(), discoverCoursesArray);
        recyclerView.setAdapter(discoverCourseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));

        // Recycle View for Ongoing Courses
        RecyclerView recyclerView1 = view.findViewById(R.id.ongoingCourseRecycleView);
        setUpOngoingCourseData();
        // OngoingCourseAdapter ongoingCourseAdapter = new OngoingCourseAdapter(this.getContext(), ongoingCourseArrayList);
        recyclerView1.setAdapter(ongoingCourseAdapter);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));



        // Get the SearchView
        searchView = view.findViewById(R.id.searchView);

        // Set the search query hint
        searchView.setQueryHint("Search courses");

        // Set an OnQueryTextListener for the SearchView
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Filter your RecyclerView adapter based on the newText
                discoverCourseAdapter.getFilter().filter(newText);
                ongoingCourseAdapter.getFilter().filter(newText);
                completedCourseAdapter.getFilter().filter(newText);
                return true;
            }
        });

        TextView courseTitleTextView = view.findViewById(R.id.cc_courseName);


        /// Reference to the Firebase "Image" section where instructor data is stored
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("Course");

        // Attach a ValueEventListener to retrieve data from Firebase
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Log.d("StQuizQuestionsList", "storing data.");

                    courses.add(new Course(dataSnapshot.getKey(), dataSnapshot.child("courseTitle").getValue(String.class), dataSnapshot.child("courseDesc").getValue(String.class), dataSnapshot.child("category").getValue(String.class), dataSnapshot.child("uri").getValue(String.class)));
                    Log.d("StQuizQuestionsList", "Success to get data.");
                    Log.d("StQuizQuestionsList", "Value is: " + dataSnapshot.getValue());
                }

                Log.d("StQuizQuestionsList", "courses size: " + courses.size());
                for (Course course : courses) {
                    LinearLayout linearLayout = getView().findViewById(R.id.completedCourseContainer);
                    View courseView = getLayoutInflater().inflate(R.layout.course_completed_course_single, linearLayout, false);

                    ImageView imageView = courseView.findViewById(R.id.image);
                    // Use Glide to load the image from the URI
                    Glide.with(getActivity())
                            .load(course.getUri())
                            .into(imageView);

                    TextView courseTitleTextView = courseView.findViewById(R.id.title);
                    courseTitleTextView.setText(course.getCourseTitle());

                    TextView courseDescTextView = courseView.findViewById(R.id.description);
                    courseDescTextView.setText(course.getCourseDesc());

                    // Set OnClickListener for each courseView
                    courseView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // Handle the click event for the courseView
                            // For example, you can open a new activity with details of the selected course
                            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("system", MODE_PRIVATE);
                            sharedPreferences.edit().putString("courseId", course.getCourseId()).apply();

                            Intent intent = new Intent(getActivity(), StCourseViewActivity.class);
                            startActivity(intent);
                        }
                    });

                    linearLayout.addView(courseView);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        ImageView courseImageView = view.findViewById(R.id.CompletedCourseImage);

        // Reference to the Firebase "Image" section where instructor data is stored
        DatabaseReference db = FirebaseDatabase.getInstance().getReference("Image");

        // Attach a ValueEventListener to retrieve data from Firebase
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Check if data exists
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        // Retrieve image
                        String imageUrl = snapshot.child("imageUrl").getValue(String.class); // Retrieve image URL

                        // Load the image using Glide or Picasso (you may need to add the corresponding libraries)
//                        if (imageUrl != null && !imageUrl.isEmpty()) {
//                            Glide.with(CoursesFragment.this)
//                                    .load(imageUrl)
//                                    .into(courseImageView);
//                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }



    public void setUpDiscoverCourseData(){
        String[] courseName = getResources().getStringArray(R.array.courses);
        String[] courseDesc = getResources().getStringArray(R.array.course_desc);

        for (int i=0; i<courseDesc.length; i++){
            discoverCoursesArray.add(new DiscoverCourse(courseName[i], courseDesc[i], courseImages[i]));
        }
    }

    public void setUpOngoingCourseData(){
        String[] oc_courseNames = getResources().getStringArray(R.array.oc_courseName);
        String[] oc_courseQtys = getResources().getStringArray(R.array.oc_courseQty);
        for (int i=0; i<oc_courseNames.length; i++){
            ongoingCourseArrayList.add(new OngoingCourse(oc_courseNames[i], oc_courseQtys[i], oc_courseImages[i]));
        }
    }

}