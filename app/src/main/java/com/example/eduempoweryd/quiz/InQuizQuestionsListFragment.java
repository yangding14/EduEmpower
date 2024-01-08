package com.example.eduempoweryd.quiz;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eduempoweryd.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class InQuizQuestionsListFragment extends Fragment {
    private static List<Question> questions = new ArrayList<>();
    private QuestionAdapter questionAdapter;

    private Button btnAddQuestion, btnSaveChangesSaveQuiz;

    // Get a reference to the Firebase database
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference dbRef = db.getReference("quizzes").child("quiz1").child("questions");

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.quiz_fragment_in_quiz_questions_list, container, false);

        // Set up recycler view for questions list
        RecyclerView recyclerView = view.findViewById(R.id.rvQuestionsList);
        populateQuestions();
        // Create an instance of the QuestionAdapter
        questionAdapter = new QuestionAdapter(this.getContext(), questions);

        // Set the OnItemClickListener for the adapter
        questionAdapter.setOnItemClickListener(new QuestionAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // Handle item click here
                Log.i("QuestionAdapter", "Clicked on Question");
                switchFragment(questions.get(position).getQuestionId(), questions.get(position).getQuestion(), questions.get(position).getOptions());
            }
        });

        // Set button for adding questions
        btnAddQuestion = view.findViewById(R.id.btnAddQuestion);
        btnAddQuestion.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.i("InQuizQuestionsList", "Pressed Add Question button");
                switchFragment(null, null, null);
            }
        });

        btnSaveChangesSaveQuiz = view.findViewById(R.id.btnSaveChangesSaveQuiz);
        btnSaveChangesSaveQuiz.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Get the FragmentManager
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                fragmentManager.popBackStack();
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(questionAdapter);
        // Inflate the layout for this fragment
        return view;
    }

    public void populateQuestions() {
        Log.d("InQuizQuestionsList", "Populating questions");
        // calling add value event listener method
        // for getting the values from the database.
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                questions.clear(); // Clear existing questions

                for (DataSnapshot questionSnapshot : snapshot.getChildren()) {
                    // Get the values from the snapshot
                    String questionId = questionSnapshot.getKey();
                    String questionText = questionSnapshot.child("question").getValue(String.class);

                    // Get the options, which is a list of strings
                    List<String> options = new ArrayList<>();
                    for (DataSnapshot optionSnapshot : questionSnapshot.child("options").getChildren()) {
                        String option = optionSnapshot.getValue(String.class);
                        options.add(option);
                    }

                    // Create a Question object and add it to the questions list
                    Question question = new Question();
                    question.setQuestionId(questionId);
                    question.setQuestion(questionText);
                    question.setOptions(options);

                    questions.add(question);
                }

                questionAdapter.notifyDataSetChanged();

                Log.d("InQuizQuestionsList", "Questions: " + questions.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // calling on cancelled method when we receive
                // any error or we are not able to get the data.
                Toast.makeText(getActivity(), "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void switchFragment(String questionId, String question, List<String> options) {
        Bundle bundle = new Bundle();
        bundle.putString("questionId", questionId);
        bundle.putString("question", question);
        bundle.putStringArrayList("options", (ArrayList<String>) options);

        // Create an instance of InQuizAddQuestionFragment
        InQuizAddQuestionFragment fragAddQues = new InQuizAddQuestionFragment();
        fragAddQues.setArguments(bundle);

        // Get the FragmentManager
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();

        // Begin the transaction
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentInQuizContainerView, fragAddQues)
                .addToBackStack(null)
                .commit();
    }

    // Interface to handle the callback when questions are populated
    public interface OnQuestionsPopulatedListener {
        void onQuestionsPopulated();
    }
}
